
package org.kuali.student.core.comment.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Fri Jun 05 15:33:47 EDT 2009
 * Generated source version: 2.2
 */

@XmlRootElement(name = "removeTagsResponse", namespace = "http://student.kuali.org/commentService")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeTagsResponse", namespace = "http://student.kuali.org/commentService")

public class RemoveTagsResponse {

    @XmlElement(name = "return")
    private org.kuali.student.core.comment.dto.StatusInfo _return;

    public org.kuali.student.core.comment.dto.StatusInfo getReturn() {
        return this._return;
    }

    public void setReturn(org.kuali.student.core.comment.dto.StatusInfo new_return)  {
        this._return = new_return;
    }

}

