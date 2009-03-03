package org.kuali.student.lum.lu.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.student.core.dao.impl.AbstractCrudDaoImpl;
import org.kuali.student.lum.lu.dao.LuDao;
import org.kuali.student.lum.lu.entity.Clu;
import org.kuali.student.lum.lu.entity.LuStatement;
import org.kuali.student.lum.lu.entity.Lui;
import org.kuali.student.lum.lu.entity.ReqComponent;

public class LuDaoImpl extends AbstractCrudDaoImpl implements LuDao{
	@PersistenceContext(unitName = "Lu")
	@Override
	public void setEm(EntityManager em) {
		super.setEm(em);
	}

	@Override
	public List<Clu> getClusByIdList(List<String> cluIdList) {
		Query query = em.createNamedQuery("Clu.findClusByIdList");
		query.setParameter("idList", cluIdList);
		@SuppressWarnings("unchecked")
		List<Clu> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<Clu> getClusByLuType(String luTypeKey, String luState) {
		Query query = em.createNamedQuery("Clu.getClusByLuType");
		query.setParameter("luTypeKey", luTypeKey);
		query.setParameter("luState", luState);
		@SuppressWarnings("unchecked")
		List<Clu> resultList = query.getResultList();
		return resultList;
	}

    @Override
    public List<LuStatement> getLuStatementsForLuStatementType(String luStatementTypeKey) {
        Query query = em.createNamedQuery("LuStatement.getLuStatementsForLuStatementType");
        query.setParameter("luStatementTypeKey", luStatementTypeKey);
        @SuppressWarnings("unchecked")
        List<LuStatement> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<ReqComponent> getReqComponentsByType(String reqComponentTypeKey) {
        Query query = em.createNamedQuery("ReqComponent.getReqComponentsByType");
        query.setParameter("reqComponentTypeKey", reqComponentTypeKey);
        @SuppressWarnings("unchecked")
        List<ReqComponent> resultList = query.getResultList();
        return resultList;
    }

	@Override
	public List<Lui> getLuisByIdList(List<String> luiIds) {
		Query query = em.createNamedQuery("Lui.getLuisByIdList");
        query.setParameter("luiIdList", luiIds);
        @SuppressWarnings("unchecked")
		List<Lui> luis = query.getResultList();
        return luis;
	}
}
