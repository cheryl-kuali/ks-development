/*
 * Copyright 2007 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.brms.repository.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.brms.client.modeldriven.brl.RuleModel;
import org.drools.brms.server.util.BRDRLPersistence;
import org.drools.brms.server.util.BRLPersistence;
import org.drools.brms.server.util.BRXMLPersistence;
import org.drools.compiler.PackageBuilder;
import org.drools.event.DebugWorkingMemoryEventListener;
import org.junit.Test;
import org.kuali.student.brms.repository.BRMSRepositoryTest;

public class TestClass 
{
	@Test
	public void testMain() throws Exception
	{
		main(null);
	}
	
	public static void main(String[] args ) throws Exception
	{
		RuleBase[] businessRules = new RuleBase[2];
		
		businessRules[0] = getRules1(); // Test dslr and dsl files
		businessRules[1] = getRules2(); // Test drl file
		//businessRules[2] = getRules3(); // Corrupt brl file
		
		for( int i=0; i<businessRules.length; i++ )
		{
			StatefulSession workingMemory = businessRules[i].newStatefulSession();
	
			workingMemory.addEventListener( new DebugWorkingMemoryEventListener() );
	
			Email email = new Email( "len@ubc.ca" );
	        Message message = new Message( false, "Invalid email" );
	        //Message message = test.new Message();
	
			//Let the rule engine know about the facts
			workingMemory.insert( email );
			workingMemory.insert( message );
			//workingMemory.insert( Calendar.getInstance() );
	
			//Let the rule engine do its stuff!!
			workingMemory.fireAllRules();
	
			System.out.println( "message = " + message.getMessage() );
			System.out.println( "average = " + email.getAverage() );
			workingMemory.dispose();
		}
	}
	
	private static RuleBase getRules1() throws Exception
	{
		InputStream drl = TestClass.class.getResourceAsStream( "/hello-email.dslr" );
		InputStream dsl = TestClass.class.getResourceAsStream( "/hello-drools.dsl" );
		// read in the source
		System.out.println("rules file: " + drl);
		Reader sourceDrl = new InputStreamReader(drl);
		Reader sourceDsl = new InputStreamReader(dsl);
		PackageBuilder builder = new PackageBuilder();

		// this will parse and compile
		builder.addPackageFromDrl( sourceDrl, sourceDsl );

		// deploy the rulebase
		RuleBase businessRules = RuleBaseFactory.newRuleBase();
		businessRules.addPackage(builder.getPackage());
		return businessRules;
	}

	private static RuleBase getRules2() throws Exception
	{
		InputStream drl = TestClass.class.getResourceAsStream( "/test.drl" );
		// read in the source
		System.out.println("rules file: " + drl);
		Reader sourceDrl = new InputStreamReader(drl);
		PackageBuilder builder = new PackageBuilder();

		// this will parse and compile
		builder.addPackageFromDrl( sourceDrl );

		// deploy the rulebase
		RuleBase businessRules = RuleBaseFactory.newRuleBase();
		businessRules.addPackage(builder.getPackage());
		return businessRules;
	}

	private static RuleBase getRules3() throws Exception
	{
		String packageName = "package org.kuali.student.test\n";
		String brl = loadRule( "/hello-drools.brl" );
		String packageImports = 
			"import java.util.regex.Pattern;\n" + 
			"import org.kuali.student.brms.repository.test.Email;\n" +
			"import org.kuali.student.brms.repository.test.Message;\n";

		BRLPersistence read = BRXMLPersistence.getInstance();
		BRLPersistence write = BRDRLPersistence.getInstance();
		RuleModel ruleModel = read.unmarshal( brl );
		String sourceDrl = write.marshal( ruleModel );
		
		sourceDrl = packageName + "\n" + packageImports + "\n" + sourceDrl;
		
		// read in the source
		System.out.println( "rules file:\n\n" + sourceDrl );
		PackageBuilder builder = new PackageBuilder();
		Reader drl = new StringReader( sourceDrl );

		// this will parse and compile
		builder.addPackageFromDrl( drl );

		// deploy the rulebase
		RuleBase businessRules = RuleBaseFactory.newRuleBase();
		businessRules.addPackage(builder.getPackage());
		return businessRules;
	}

	private static String loadRule( String file ) throws Exception
	{
        String filename = BRMSRepositoryTest.class.getResource( file ).getFile();
        System.out.println( "*****  filename = " +filename );

        String str = "";
        String drl = "";
        BufferedReader in = null;
		try
		{
			in = new BufferedReader( new FileReader( filename ) );
	        while ( ( str = in.readLine() ) != null ) 
	        {
	        	drl += str;
	        }
            System.out.println( drl );
		}
		finally
		{
	        if (in != null ) in.close();
		}
        return drl;
	}

}
