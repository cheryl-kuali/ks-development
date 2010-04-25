package org.kuali.core.db.torque;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.torque.engine.database.model.TypeMap;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Element;

public class KualiTorqueSchemaDumpTask extends Task {
	Utils utils = new Utils();

	// some flags for making it easier to debug
	private final boolean processTables = true;
	private final boolean processViews = true;
	private final boolean processSequences = true;
	String encoding;

	/** Name of XML database schema produced. */
	protected File schemaXMLFile;

	/** JDBC URL. */
	protected String url;

	/** JDBC driver. */
	protected String driver;

	/** JDBC user name. */
	protected String username;

	/** JDBC password. */
	protected String password;

	/** DB schema to use. */
	protected String schema;

	/** DB schema to use. */
	protected String schemaXMLName;

	/** DOM document produced. */
	protected DocumentImpl doc;

	/** The document root element. */
	protected Element databaseNode;

	protected String targetDatabase;

	/** Hashtable of columns that have primary keys. */
	protected HashMap<String, String> primaryKeys;

	private XMLSerializer xmlSerializer;

	public String getSchema() {
		return schema;
	}

	public void setSchema(String dbSchema) {
		this.schema = dbSchema;
	}

	public void setUrl(String v) {
		url = v;
	}

	public void setDriver(String v) {
		driver = v;
	}

	public void setUsername(String v) {
		username = v;
	}

	public void setPassword(String v) {
		password = v;
	}

	public void setTargetDatabase(String dbType) {
		this.targetDatabase = dbType;
	}

	/**
	 * Default constructor.
	 * 
	 * @throws BuildException
	 */
	public void execute() throws BuildException {
		log("Impex - Schema Dump starting");
		log("Your DB settings are: ");
		log("Driver: " + driver);
		log("URL: " + url);
		log("Username: " + username);
		log("Schema: " + schema);
		log("Schema XML Name: " + schemaXMLName);
		log("Dumping to: " + schemaXMLFile.getAbsolutePath());

		DocumentTypeImpl docType = new DocumentTypeImpl(null, "database", null, ImpexDTDResolver.DTD_LOCATION);
		doc = new DocumentImpl(docType);
		doc.appendChild(doc.createComment(" Autogenerated by the maven-impex-plugin "));

		try {
			generateXML();
			if (getEncoding() == null) {
				log("Encoding: " + System.getProperty("file.encoding"));
			} else {
				log("Encoding: " + getEncoding());
			}
			xmlSerializer = new XMLSerializer(new PrintWriter(new FileOutputStream(schemaXMLFile)), new OutputFormat(Method.XML, getEncoding(), true));
			xmlSerializer.serialize(doc);
		} catch (Exception e) {
			throw new BuildException(e);
		}
		log("Impex - Schema Dump finished");
	}

	/**
	 * Generates an XML database schema from JDBC metadata.
	 * 
	 * @throws Exception
	 *             a generic exception.
	 */
	@SuppressWarnings("unchecked")
	public void generateXML() throws Exception {
		// Load the database Driver.
		Class.forName(driver);
		log("DB driver sucessfuly instantiated");

		Connection con = null;
		try {
			// Attempt to connect to a database.
			con = DriverManager.getConnection(url, username, password);
			log("DB connection established");

			log("Loading platform for " + getTargetDatabase());
			log("Getting table list...");
			Platform platform = PlatformFactory.getPlatformFor(targetDatabase);

			// Get the database Metadata.
			DatabaseMetaData dbMetaData = con.getMetaData();

			databaseNode = doc.createElement("database");
			databaseNode.setAttribute("name", schemaXMLName);
			// JHK added naming method
			databaseNode.setAttribute("defaultJavaNamingMethod", "nochange");

			if (processTables) {
				List<String> tableList = platform.getTableNames(dbMetaData, schema);
				log("Found " + tableList.size() + " tables");

				for (String curTable : tableList) {
					long start = System.currentTimeMillis();

					Element table = doc.createElement("table");
					table.setAttribute("name", curTable);

					// Add Columns.
					// TableMap tblMap = dbMap.getTable(curTable);

					List<List<Object>> columns = getColumns(dbMetaData, curTable);
					List<String> primKeys = platform.getPrimaryKeys(dbMetaData, schema, curTable);
					Map<String, Object[]> foreignKeys = getForeignKeys(dbMetaData, curTable);

					// Set the primary keys.
					primaryKeys = new HashMap<String, String>();

					for (int k = 0; k < primKeys.size(); k++) {
						String curPrimaryKey = (String) primKeys.get(k);
						primaryKeys.put(curPrimaryKey, curPrimaryKey);
					}

					for (int j = 0; j < columns.size(); j++) {
						List<Object> col = columns.get(j);
						String name = (String) col.get(0);
						Integer type = ((Integer) col.get(1));
						int size = ((Integer) col.get(2)).intValue();
						int scale = ((Integer) col.get(5)).intValue();

						// From DatabaseMetaData.java
						//
						// Indicates column might not allow NULL values. Huh?
						// Might? Boy, that's a definitive answer.
						/* int columnNoNulls = 0; */

						// Indicates column definitely allows NULL values.
						/* int columnNullable = 1; */

						// Indicates NULLABILITY of column is unknown.
						/* int columnNullableUnknown = 2; */

						Integer nullType = (Integer) col.get(3);
						String defValue = (String) col.get(4);

						Element column = doc.createElement("column");
						column.setAttribute("name", name);

						column.setAttribute("type", TypeMap.getTorqueType(type).getName());

						if (size > 0 && (type.intValue() == Types.CHAR || type.intValue() == Types.VARCHAR || type.intValue() == Types.LONGVARCHAR || type.intValue() == Types.DECIMAL || type.intValue() == Types.NUMERIC)) {
							column.setAttribute("size", String.valueOf(size));
						}

						if (scale > 0 && (type.intValue() == Types.DECIMAL || type.intValue() == Types.NUMERIC)) {
							column.setAttribute("scale", String.valueOf(scale));
						}

						if (primaryKeys.containsKey(name)) {
							column.setAttribute("primaryKey", "true");
							// JHK: protect MySQL from excessively long column in the PK
							// System.out.println( curTable + "." + name + " / " + size );
							if (column.getAttribute("size") != null && size > 765) {
								System.out.println("updating column " + curTable + "." + name + " length from " + size + " to 255");
								column.setAttribute("size", "255");
							}
						} else {
							if (nullType.intValue() == DatabaseMetaData.columnNoNulls) {
								column.setAttribute("required", "true");
							}
						}

						if (StringUtils.isNotEmpty(defValue)) {
							defValue = defValue.trim();
							// trim out parens & quotes out of def value.
							// makes sense for MSSQL. not sure about others.
							if (defValue.startsWith("(") && defValue.endsWith(")")) {
								defValue = defValue.substring(1, defValue.length() - 1);
							}

							if (defValue.startsWith("'") && defValue.endsWith("'")) {
								defValue = defValue.substring(1, defValue.length() - 1);
							}
							if (defValue.equals("NULL")) {
								defValue = "";
							}
							if (StringUtils.isNotEmpty(defValue)) {
								column.setAttribute("default", defValue);
							}
						}
						table.appendChild(column);
					}

					// Foreign keys for this table.
					for (String fkName : foreignKeys.keySet()) {
						Element fk = doc.createElement("foreign-key");
						fk.setAttribute("name", fkName);
						Object[] forKey = foreignKeys.get(fkName);
						String foreignKeyTable = (String) forKey[0];
						List<String[]> refs = (List<String[]>) forKey[1];
						fk.setAttribute("foreignTable", foreignKeyTable);
						String onDelete = (String) forKey[2];
						// gmcgrego - just adding onDelete if it's cascade so as not to affect kfs behavior
						if (onDelete == "cascade") {
							fk.setAttribute("onDelete", onDelete);
						}
						for (int m = 0; m < refs.size(); m++) {
							Element ref = doc.createElement("reference");
							String[] refData = (String[]) refs.get(m);
							ref.setAttribute("local", refData[0]);
							ref.setAttribute("foreign", refData[1]);
							fk.appendChild(ref);
						}
						table.appendChild(fk);
					}

					for (TableIndex idx : getIndexes(dbMetaData, curTable)) {
						String tagName = idx.unique ? "unique" : "index";
						Element index = doc.createElement(tagName);
						index.setAttribute("name", idx.name);
						for (String colName : idx.columns) {
							Element col = doc.createElement(tagName + "-column");
							col.setAttribute("name", colName);
							index.appendChild(col);
						}
						table.appendChild(index);
					}

					databaseNode.appendChild(table);
					long elapsed = System.currentTimeMillis() - start;
					log(utils.pad(curTable, elapsed));
				}
			}
			if (processViews) {
				List<String> viewNames = getViewNames(dbMetaData);
				for (String viewName : viewNames) {
					Element view = doc.createElement("view");
					view.setAttribute("name", viewName);
					/*
					 * <view name="" viewdefinition="" />
					 */
					String definition = platform.getViewDefinition(dbMetaData.getConnection(), schema, viewName);
					definition = definition.replaceAll("\0", "");
					view.setAttribute("viewdefinition", definition);
					databaseNode.appendChild(view);
				}
			}

			if (processSequences) {
				List<String> sequenceNames = getSequenceNames(dbMetaData);
				for (String sequenceName : sequenceNames) {
					Element sequence = doc.createElement("sequence");
					sequence.setAttribute("name", sequenceName);
					/*
					 * <view name="" nextval="" />
					 */
					Long nextVal = platform.getSequenceNextVal(dbMetaData.getConnection(), schema, sequenceName);
					sequence.setAttribute("nextval", nextVal.toString());

					databaseNode.appendChild(sequence);
				}
				doc.appendChild(databaseNode);
			}
		} finally {
			if (con != null) {
				con.close();
				con = null;
			}
		}
	}

	public List<String> getViewNames(DatabaseMetaData dbMeta) throws SQLException {
		log("Getting view list...");
		List<String> tables = new ArrayList<String>();
		ResultSet tableNames = null;
		// these are the entity types we want from the database
		String[] types = { "VIEW" }; // JHK: removed views from list
		try {
			tableNames = dbMeta.getTables(null, schema, null, types);
			while (tableNames.next()) {
				String name = tableNames.getString(3);
				tables.add(name);
			}
		} finally {
			if (tableNames != null) {
				tableNames.close();
			}
		}
		log("Found " + tables.size() + " views.");
		return tables;
	}

	public boolean isSequence(String sequenceName) {
		return sequenceName.toUpperCase().startsWith("SEQ_") || sequenceName.toUpperCase().startsWith("SEQUENCE_") || sequenceName.toUpperCase().endsWith("_SEQ") || sequenceName.toUpperCase().endsWith("_SEQUENCE") || sequenceName.toUpperCase().endsWith("_ID") || sequenceName.toUpperCase().endsWith("_S");
	}

	public List<String> getSequenceNames(DatabaseMetaData dbMeta) throws SQLException {
		log("Getting sequence list...");
		List<String> tables = new ArrayList<String>();
		ResultSet tableNames = null;
		// these are the entity types we want from the database
		String[] types = { "TABLE", "SEQUENCE" }; // JHK: removed views from list
		try {
			tableNames = dbMeta.getTables(null, schema, null, types);
			while (tableNames.next()) {
				String name = tableNames.getString(3);
				if (isSequence(name)) {
					tables.add(name);
				}
			}
		} finally {
			if (tableNames != null) {
				tableNames.close();
			}
		}
		log("Found " + tables.size() + " sequences.");
		return tables;
	}

	// for ( int i = 1; i <= tableNames.getMetaData().getColumnCount(); i++ ) {
	// System.out.print( tableNames.getMetaData().getColumnName( i ) + "," );
	// }
	// System.out.println();
	// for ( int i = 1; i <= tableNames.getMetaData().getColumnCount(); i++ ) {
	// System.out.print( tableNames.getString( i ) + "," );
	// }
	// System.out.println();

	/**
	 * Retrieves all the column names and types for a given table from JDBC metadata. It returns a List of Lists. Each
	 * element of the returned List is a List with:
	 * 
	 * element 0 => a String object for the column name. element 1 => an Integer object for the column type. element 2
	 * => size of the column. element 3 => null type.
	 * 
	 * @param dbMeta
	 *            JDBC metadata.
	 * @param tableName
	 *            Table from which to retrieve column information.
	 * @return The list of columns in <code>tableName</code>.
	 * @throws SQLException
	 */
	public List<List<Object>> getColumns(DatabaseMetaData dbMeta, String tableName) throws SQLException {
		List<List<Object>> columns = new ArrayList<List<Object>>();
		ResultSet columnSet = null;
		try {
			columnSet = dbMeta.getColumns(null, schema, tableName, null);
			while (columnSet.next()) {
				String name = columnSet.getString(4);
				Integer sqlType = new Integer(columnSet.getString(5));
				Integer size = new Integer(columnSet.getInt(7));
				Integer decimalDigits = new Integer(columnSet.getInt(9));
				Integer nullType = new Integer(columnSet.getInt(11));
				String defValue = columnSet.getString(13);

				List<Object> col = new ArrayList<Object>(6);
				col.add(name);
				col.add(sqlType);
				col.add(size);
				col.add(nullType);
				col.add(defValue);
				col.add(decimalDigits);
				columns.add(col);
			}
		} finally {
			if (columnSet != null) {
				columnSet.close();
			}
		}
		return columns;
	}

	/**
	 * Retrieves a list of foreign key columns for a given table.
	 * 
	 * @param dbMeta
	 *            JDBC metadata.
	 * @param tableName
	 *            Table from which to retrieve FK information.
	 * @return A list of foreign keys in <code>tableName</code>.
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object[]> getForeignKeys(DatabaseMetaData dbMeta, String tableName) throws SQLException {
		Map<String, Object[]> fks = new HashMap<String, Object[]>();
		ResultSet foreignKeys = null;
		try {
			foreignKeys = dbMeta.getImportedKeys(null, schema, tableName);
			while (foreignKeys.next()) {
				String refTableName = foreignKeys.getString(3);
				String fkName = foreignKeys.getString(12);
				int deleteRule = foreignKeys.getInt(11);
				String onDelete = "none";
				if (deleteRule == DatabaseMetaData.importedKeyCascade) {
					onDelete = "cascade";
				} else if (deleteRule == DatabaseMetaData.importedKeyRestrict) {
					onDelete = "restrict";
				} else if (deleteRule == DatabaseMetaData.importedKeySetNull) {
					onDelete = "setnull";
				}
				// if FK has no name - make it up (use tablename instead)
				if (fkName == null) {
					fkName = refTableName;
				}
				Object[] fk = (Object[]) fks.get(fkName);
				List<String[]> refs;
				if (fk == null) {
					fk = new Object[3];
					fk[0] = refTableName; // referenced table name
					refs = new ArrayList<String[]>();
					fk[1] = refs;
					fks.put(fkName, fk);
					fk[2] = onDelete;
				} else {
					refs = (ArrayList<String[]>) fk[1];
				}
				String[] ref = new String[2];
				ref[0] = foreignKeys.getString(8); // local column
				ref[1] = foreignKeys.getString(4); // foreign column
				refs.add(ref);
			}
		} catch (SQLException e) {
			// this seems to be happening in some db drivers (sybase)
			// when retrieving foreign keys from views.
			log("WARN: Could not read foreign keys for Table " + tableName + " : " + e.getMessage(), Project.MSG_WARN);
		} finally {
			if (foreignKeys != null) {
				foreignKeys.close();
			}
		}
		return fks;
	}

	public List<TableIndex> getIndexes(DatabaseMetaData dbMeta, String tableName) throws SQLException {
		List<TableIndex> indexes = new ArrayList<TableIndex>();
		ResultSet pkInfo = null;
		String pkName = null;
		// ArrayList<String> pkFields = new ArrayList<String>();
		ResultSet indexInfo = null;
		try {
			indexInfo = dbMeta.getIndexInfo(null, schema, tableName, false, false);
			// need to ensure that the PK is not returned as an index
			pkInfo = dbMeta.getPrimaryKeys(null, schema, tableName);
			if (pkInfo.next()) {
				pkName = pkInfo.getString("PK_NAME");
			}
			// Map<Integer,String> tempPk = new HashMap<Integer,String>();
			// while ( pkInfo.next() ) {
			// tempPk.put( pkInfo.getInt( "KEY_SEQ" ), pkInfo.getString( "COLUMN_NAME" ) );
			// }

			TableIndex currIndex = null;
			while (indexInfo.next()) {
				if (indexInfo.getString("INDEX_NAME") == null)
					continue;
				// System.out.println( "Row: " + indexInfo.getString( "INDEX_NAME" ) + "/" + indexInfo.getString(
				// "COLUMN_NAME" ) );
				if (currIndex == null || !indexInfo.getString("INDEX_NAME").equals(currIndex.name)) {
					currIndex = new TableIndex();
					currIndex.name = indexInfo.getString("INDEX_NAME");
					currIndex.unique = !indexInfo.getBoolean("NON_UNIQUE");
					// if has the same name as the PK, skip adding it to the index list
					if (pkName == null || !pkName.equals(currIndex.name)) {
						indexes.add(currIndex);
						// System.out.println( "Added " + currIndex.name + " to index list");
					} else {
						// System.out.println( "Skipping PK: " + currIndex.name );
					}
				}
				currIndex.columns.add(indexInfo.getString("COLUMN_NAME"));
			}

		} catch (SQLException e) {
			log("WARN: Could not read indexes for Table " + tableName + " : " + e.getMessage(), Project.MSG_WARN);
		} finally {
			if (indexInfo != null) {
				indexInfo.close();
			}
			if (pkInfo != null) {
				pkInfo.close();
			}
		}
		return indexes;
	}

	private static class TableIndex {
		public String name;
		public boolean unique;
		public List<String> columns = new ArrayList<String>();
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public DocumentImpl getDoc() {
		return doc;
	}

	public void setDoc(DocumentImpl doc) {
		this.doc = doc;
	}

	public Element getDatabaseNode() {
		return databaseNode;
	}

	public void setDatabaseNode(Element databaseNode) {
		this.databaseNode = databaseNode;
	}

	public HashMap<String, String> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(HashMap<String, String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public XMLSerializer getXmlSerializer() {
		return xmlSerializer;
	}

	public void setXmlSerializer(XMLSerializer xmlSerializer) {
		this.xmlSerializer = xmlSerializer;
	}

	public boolean isProcessTables() {
		return processTables;
	}

	public boolean isProcessViews() {
		return processViews;
	}

	public boolean isProcessSequences() {
		return processSequences;
	}

	public String getUrl() {
		return url;
	}

	public String getDriver() {
		return driver;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getTargetDatabase() {
		return targetDatabase;
	}

	public String getSchemaXMLName() {
		return schemaXMLName;
	}

	public void setSchemaXMLName(String schemaXMLName) {
		this.schemaXMLName = schemaXMLName;
	}

	public File getSchemaXMLFile() {
		return schemaXMLFile;
	}

	public void setSchemaXMLFile(File schemaXMLFile) {
		this.schemaXMLFile = schemaXMLFile;
	}
}
