
package org.kuali.student.core.proposal.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.2
 * Wed Sep 02 14:48:07 EDT 2009
 * Generated source version: 2.2
 */

@XmlRootElement(name = "updateProposalDocRelation", namespace = "http://student.kuali.org/wsdl/proposal")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateProposalDocRelation", namespace = "http://student.kuali.org/wsdl/proposal", propOrder = {"proposalDocRelationId","proposalDocRelationInfo"})

public class UpdateProposalDocRelation {

    @XmlElement(name = "proposalDocRelationId")
    private java.lang.String proposalDocRelationId;
    @XmlElement(name = "proposalDocRelationInfo")
    private org.kuali.student.core.proposal.dto.ProposalDocRelationInfo proposalDocRelationInfo;

    public java.lang.String getProposalDocRelationId() {
        return this.proposalDocRelationId;
    }

    public void setProposalDocRelationId(java.lang.String newProposalDocRelationId)  {
        this.proposalDocRelationId = newProposalDocRelationId;
    }

    public org.kuali.student.core.proposal.dto.ProposalDocRelationInfo getProposalDocRelationInfo() {
        return this.proposalDocRelationInfo;
    }

    public void setProposalDocRelationInfo(org.kuali.student.core.proposal.dto.ProposalDocRelationInfo newProposalDocRelationInfo)  {
        this.proposalDocRelationInfo = newProposalDocRelationInfo;
    }

}

