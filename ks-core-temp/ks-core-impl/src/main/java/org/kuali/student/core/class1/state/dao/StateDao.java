package org.kuali.student.core.class1.state.dao;

import java.util.List;

import org.kuali.student.enrollment.dao.GenericEntityDao;
import org.kuali.student.core.class1.state.model.StateEntity;

public class StateDao extends GenericEntityDao<StateEntity>{
	public StateEntity getState(String stateKey){
		return (StateEntity)em.createQuery("from StateEntity se where se.id=:stateKey")
		.setParameter("stateKey", stateKey)
		.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<StateEntity> getStatesByProcess(String processKey){
		return  (List<StateEntity>)em.createQuery("from StateEntity se where se.processKey=:processKey")
		.setParameter("processKey", processKey)
		.getResultList();		
	}	
}
