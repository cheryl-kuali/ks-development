
package org.kuali.student.brms.rulemanagement.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.1.2
 * Thu Jan 15 15:29:34 EST 2009
 * Generated source version: 2.1.2
 */

@XmlRootElement(name = "DependentObjectsExistException", namespace = "http://org.kuali.student/brms/exceptions")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DependentObjectsExistException", namespace = "http://org.kuali.student/brms/exceptions")

public class DependentObjectsExistExceptionBean {

    private java.lang.String message;

    public java.lang.String getMessage() {
        return this.message;
    }

    public void setMessage(java.lang.String newMessage)  {
        this.message = newMessage;
    }

}

