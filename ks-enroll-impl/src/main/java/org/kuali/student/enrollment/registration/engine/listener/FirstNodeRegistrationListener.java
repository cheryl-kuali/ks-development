package org.kuali.student.enrollment.registration.engine.listener;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.kuali.student.enrollment.registration.engine.service.CourseRegistrationConstants;

import javax.jms.MapMessage;

public class FirstNodeRegistrationListener extends BaseRegistrationListener {

    @Override
    protected void beforeProcessHook(String regReqId) throws Exception {
        MapMessage perfMap = new ActiveMQMapMessage();

        perfMap.setString(CourseRegistrationConstants.REGISTRATION_QUEUE_MESSAGE_REG_REQ_ID, regReqId);
        perfMap.setLong("startTime", System.currentTimeMillis());

        getJmsTemplate().convertAndSend(SimplePerformanceListener.QUEUE_NAME, perfMap);
    }
}
