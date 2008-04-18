package org.kuali.student.common_test_tester.support;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class MyDaoImpl implements MyDao {
	
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public Long createValue(Value value) {
		entityManager.persist(value);
		return value.getId();
	}

	@Override
	public String findValue(Long id) {
		Value tv =  entityManager.find(Value.class, id);
		if(tv==null){
			return null;
		}
		return tv.getValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Value findValue(String value) {
		Query q = entityManager.createQuery("SELECT v FROM Value v WHERE v.value=:valueIn");
		q.setParameter("valueIn", value);
		for(Value tv : (List<Value>)q.getResultList()){
			return tv;
		}
		return null;
	}
	
}
