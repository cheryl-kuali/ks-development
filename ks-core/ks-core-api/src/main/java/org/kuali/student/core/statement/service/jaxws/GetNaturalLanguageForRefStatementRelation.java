
package org.kuali.student.core.statement.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Wed May 12 12:54:47 PDT 2010
 * Generated source version: 2.2
 */

@XmlRootElement(name = "getNaturalLanguageForRefStatementRelation", namespace = "http://student.kuali.org/wsdl/statement")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNaturalLanguageForRefStatementRelation", namespace = "http://student.kuali.org/wsdl/statement", propOrder = {"refStatementRelationId","nlUsageTypeKey","language"})

public class GetNaturalLanguageForRefStatementRelation {

    @XmlElement(name = "refStatementRelationId")
    private java.lang.String refStatementRelationId;
    @XmlElement(name = "nlUsageTypeKey")
    private java.lang.String nlUsageTypeKey;
    @XmlElement(name = "language")
    private java.lang.String language;

    public java.lang.String getRefStatementRelationId() {
        return this.refStatementRelationId;
    }

    public void setRefStatementRelationId(java.lang.String newRefStatementRelationId)  {
        this.refStatementRelationId = newRefStatementRelationId;
    }

    public java.lang.String getNlUsageTypeKey() {
        return this.nlUsageTypeKey;
    }

    public void setNlUsageTypeKey(java.lang.String newNlUsageTypeKey)  {
        this.nlUsageTypeKey = newNlUsageTypeKey;
    }

    public java.lang.String getLanguage() {
        return this.language;
    }

    public void setLanguage(java.lang.String newLanguage)  {
        this.language = newLanguage;
    }

}

