/*
 * Copyright 2009 The Kuali Foundation Licensed under the Educational Community
 * License, Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.opensource.org/licenses/ecl1.php Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.kuali.student.cm.course.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.cm.course.form.CluInstructorInfoWrapper;
import org.kuali.student.cm.course.form.CourseJointInfoWrapper;
import org.kuali.student.cm.course.form.LearningObjectiveDialogWrapper;
import org.kuali.student.cm.course.form.OrganizationInfoWrapper;
import org.kuali.student.cm.course.form.ResultValuesGroupInfoWrapper;
import org.kuali.student.cm.course.infc.CourseProposal;
import org.kuali.student.r2.common.dto.IdEntityInfo;
import org.kuali.student.r2.core.comment.dto.CommentInfo;
import org.kuali.student.r2.core.comment.dto.DecisionInfo;
import org.kuali.student.r2.core.proposal.dto.ProposalInfo;
import org.kuali.student.r2.lum.course.dto.CourseInfo;

/**
 * Wrapper around {@link CourseInfo} and {@link ProposalInfo} classes for maintenance
 * document
 * 
 * @author OpenCollab/rSmart KRAD CM Conversion Alliance!
 */
@XmlType(name = "CoursePropsalInfo", propOrder = {"course",
        "proposal", "audit", "passFail", "instructorWrappers", "courseJointWrappers", "creditOptionWrappers",
        "finalExamStatus", "finalExamRationale", "commentInfos", "loDialogWrapper", "showAll", "userId", "decisions",
        "administeringOrganizations", "lastUpdated"})
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseProposalInfo extends IdEntityInfo implements CourseProposal, Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement
    private CourseInfo course;
    @XmlElement
    private ProposalInfo proposal;
    @XmlElement
    private boolean audit;
    @XmlElement
    private boolean passFail;
    @XmlElement
    private List<CluInstructorInfoWrapper> instructorWrappers;
    @XmlElement
    private List<CourseJointInfoWrapper> courseJointWrappers;
    @XmlElement
    private List<ResultValuesGroupInfoWrapper> creditOptionWrappers;
    @XmlElement
    private String finalExamStatus;
    @XmlElement
    private String finalExamRationale;
    @XmlElement
    private List<CommentInfo> commentInfos;
    @XmlElement
    private LearningObjectiveDialogWrapper loDialogWrapper;
    @XmlElement
    private Boolean showAll;
    @XmlElement
    private String userId;
    @XmlElement
    private List<DecisionInfo> decisions;
    @XmlElement
    private List<OrganizationInfoWrapper> administeringOrganizations;
    @XmlElement
    private String lastUpdated;

    public CourseProposalInfo(final CourseInfo course, final ProposalInfo proposal) {
        setProposal(proposal);
        setCourse(course);
    }
    
    @Override
    public ProposalInfo getProposal() {
        return proposal;
    }

    public void setProposal(final ProposalInfo proposal) {
        this.proposal = proposal;
    }

    @Override
    public CourseInfo getCourse() {
        return course;
    }

    public void setCourse(final CourseInfo course) {
        this.course = course;
    }

    /**
     * Gets the value of audit
     * 
     * @return the value of audit
     */
    public boolean isAudit() {
        return this.audit;
    }

    /**
     * Sets the value of audit
     * 
     * @param argAudit Value to assign to this.audit
     */
    public void setAudit(final boolean argAudit) {
        this.audit = argAudit;
    }

    /**
     * Gets the value of passFail
     * 
     * @return the value of passFail
     */
    public boolean isPassFail() {
        return this.passFail;
    }

    /**
     * Sets the value of passFail
     * 
     * @param argPassFail Value to assign to this.passFail
     */
    public void setPassFail(final boolean argPassFail) {
        this.passFail = argPassFail;
    }

    /**
     * Gets the value of finalExamStatus
     * 
     * @return the value of finalExamStatus
     */
    public String getFinalExamStatus() {
        return this.finalExamStatus;
    }

    /**
     * Sets the value of finalExamStatus
     * 
     * @param argFinalExamStatus Value to assign to this.finalExamStatus
     */
    public void setFinalExamStatus(final String argFinalExamStatus) {
        this.finalExamStatus = argFinalExamStatus;
    }

    /**
     * Gets the value of finalExamRationale
     * 
     * @return the value of finalExamRationale
     */
    public String getFinalExamRationale() {
        return this.finalExamRationale;
    }

    /**
     * Sets the value of finalExamRationale
     * 
     * @param argFinalExamRationale Value to assign to this.finalExamRationale
     */
    public void setFinalExamRationale(final String argFinalExamRationale) {
        this.finalExamRationale = argFinalExamRationale;
    }

    /**
     * Gets the value of loDialogWrapper
     * 
     * @return the value of loDialogWrapper
     */
    public LearningObjectiveDialogWrapper getLoDialogWrapper() {
        return this.loDialogWrapper;
    }

    /**
     * Sets the value of loDialogWrapper
     * 
     * @param argLoDialogWrapper Value to assign to this.loDialogWrapper
     */
    public void setLoDialogWrapper(final LearningObjectiveDialogWrapper argLoDialogWrapper) {
        this.loDialogWrapper = argLoDialogWrapper;
    }

    /**
     * Gets the value of showAll
     * 
     * @return the value of showAll
     */
    public Boolean getShowAll() {
        return this.showAll;
    }

    /**
     * Sets the value of showAll
     * 
     * @param argShowAll Value to assign to this.showAll
     */
    public void setShowAll(final Boolean argShowAll) {
        this.showAll = argShowAll;
    }

    /**
     * Gets the value of userId
     * 
     * @return the value of userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Sets the value of userId
     * 
     * @param argUserId Value to assign to this.userId
     */
    public void setUserId(final String argUserId) {
        this.userId = argUserId;
    }

    /**
     * Gets the value of lastUpdated
     * 
     * @return the value of lastUpdated
     */
    public String getLastUpdated() {
        return this.lastUpdated;
    }

    /**
     * Sets the value of lastUpdated
     * 
     * @param argLastUpdated Value to assign to this.lastUpdated
     */
    public void setLastUpdated(final String argLastUpdated) {
        this.lastUpdated = argLastUpdated;
    }

    /**
     * Gets the list of Instructor wrappers
     * 
     * @return the list of {@link CluInstructorInfoWrapper}
     */
    public List<CluInstructorInfoWrapper> getInstructorWrappers() {
        if (instructorWrappers == null) {
            instructorWrappers = new ArrayList<CluInstructorInfoWrapper>(0);
        }
        return instructorWrappers;
    }

    /**
     * Sets the list of Instructor wrappers
     * 
     * @param instructorWrappers List of {@link CluInstructorInfoWrapper}
     */
    public void setInstructorWrappers(List<CluInstructorInfoWrapper> instructorWrappers) {
        this.instructorWrappers = instructorWrappers;
    }

    /**
     * Gets the list of Course Joint wrappers
     * 
     * @return the list of {@link CourseJointInfoWrapper}
     */
    public List<CourseJointInfoWrapper> getCourseJointWrappers() {
        if (courseJointWrappers == null) {
            courseJointWrappers = new ArrayList<CourseJointInfoWrapper>(0);
        }
        return courseJointWrappers;
    }

    /**
     * Sets the list of Course Joint wrappers
     * 
     * @param courseJointWrappers List of {@link CourseJointInfoWrapper}
     */
    public void setCourseJointWrappers(List<CourseJointInfoWrapper> courseJointWrappers) {
        this.courseJointWrappers = courseJointWrappers;
    }

    /**
     * Gets the list of Credit Option wrappers
     * 
     * @return the list of {@link ResultValuesGroupInfoWrapper}
     */
    public List<ResultValuesGroupInfoWrapper> getCreditOptionWrappers() {
        if (creditOptionWrappers == null) {
            creditOptionWrappers = new ArrayList<ResultValuesGroupInfoWrapper>(0);
        }
        return creditOptionWrappers;
    }

    /**
     * Sets the list of Credit Option wrappers
     * 
     * @param creditOptionWrappers List of {@link ResultValuesGroupInfoWrapper}
     */
    public void setCreditOptionWrappers(List<ResultValuesGroupInfoWrapper> creditOptionWrappers) {
        this.creditOptionWrappers = creditOptionWrappers;
    }

    /**
     * Gets the list of Comments
     * 
     * @return the list of {@link CommentInfo}
     */
    public List<CommentInfo> getCommentInfos() {
        if (commentInfos == null) {
            commentInfos = new ArrayList<CommentInfo>(0);
        }
        return commentInfos;
    }

    /**
     * Sets the list of Comments
     * 
     * @param commentInfos List of {@link CommentInfo}
     */
    public void setCommentInfos(List<CommentInfo> commentInfos) {
        this.commentInfos = commentInfos;
    }

    /**
     * Gets the list of Decisions
     * 
     * @return the list of {@link DecisionInfo}
     */
    public List<DecisionInfo> getDecisions() {
        if (decisions == null) {
            decisions = new ArrayList<DecisionInfo>(0);
        }
        return decisions;
    }

    /**
     * Sets the list of Decisions
     * 
     * @param decisions List of {@link DecisionInfo}
     */
    public void setDecisions(List<DecisionInfo> decisions) {
        this.decisions = decisions;
    }

    /**
     * Gets the list of Administering Organizations
     * 
     * @return the list of {@link OrganizationInfoWrapper}
     */
    public List<OrganizationInfoWrapper> getAdministeringOrganizations() {
        if (administeringOrganizations == null) {
            administeringOrganizations = new ArrayList<OrganizationInfoWrapper>(0);
        }
        return administeringOrganizations;
    }

    /**
     * Sets the list of Administering Organizations
     * 
     * @param administeringOrganizations List of {@link OrganizationInfoWrapper}
     */
    public void setAdministeringOrganizations(List<OrganizationInfoWrapper> administeringOrganizations) {
        this.administeringOrganizations = administeringOrganizations;
    }
}
