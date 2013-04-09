package org.kuali.student.r2.core.class1.search;

import org.apache.commons.lang.StringUtils;
import org.kuali.student.r2.common.class1.search.SearchServiceAbstractHardwiredImplBase;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.util.RichTextHelper;
import org.kuali.student.r2.common.util.date.DateFormatters;
import org.kuali.student.r2.core.class1.type.dto.TypeInfo;
import org.kuali.student.r2.core.search.dto.SearchRequestInfo;
import org.kuali.student.r2.core.search.dto.SearchResultInfo;
import org.kuali.student.r2.core.search.dto.SearchResultRowInfo;
import org.kuali.student.r2.core.search.util.SearchRequestHelper;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtaylor
 * Date: 3/11/13
 * Time: 12:56 PM
 *
 * This class is to be used to call Core specific DB searches.
 *
 */
public class CoreSearchServiceImpl extends SearchServiceAbstractHardwiredImplBase {

    @Resource
    private EntityManager entityManager;

    public static final TypeInfo SCH_AND_ROOM_SEARH_BY_ID_SEARCH_TYPE;

    public static final String SCH_AND_ROOM_SEARH_BY_ID_SEARCH_KEY = "kuali.search.type.core.searchForScheduleAndRoomById";

    public static final class SearchParameters {
        public static final String AO_ID = "id";
    }

    public static final class SearchResultColumns {
        public static final String SCHEDULE_ID = "scheduleId";
    }


    static {
        TypeInfo info = new TypeInfo();
        info.setKey(SCH_AND_ROOM_SEARH_BY_ID_SEARCH_KEY);
        info.setName("Schedule And Room Search");
        info.setDescr(new RichTextHelper().fromPlain("Return search results for both Schedules and Rooms for a particular AO"));

        try {
            info.setEffectiveDate(DateFormatters.MONTH_DAY_YEAR_DATE_FORMATTER.parse("01/01/2012"));
        } catch ( IllegalArgumentException ex) {
            throw new RuntimeException("bad code");
        }
        SCH_AND_ROOM_SEARH_BY_ID_SEARCH_TYPE = info;
    }


    /**
     * Get the search type that the sub class implements.
     */
    @Override
    public TypeInfo getSearchType() {
        return SCH_AND_ROOM_SEARH_BY_ID_SEARCH_TYPE;
    }


    @Override
    public SearchResultInfo search(SearchRequestInfo searchRequestInfo, ContextInfo contextInfo) throws MissingParameterException, OperationFailedException, PermissionDeniedException {

        // As this class expands, you can add multiple searches. Ie. right now there is only one search (so only one search key).
        if (StringUtils.equals(searchRequestInfo.getSearchKey(), SCH_AND_ROOM_SEARH_BY_ID_SEARCH_TYPE.getKey())) {
            return searchForScheduleAndRoomById(searchRequestInfo,contextInfo);
        } else{
            throw new OperationFailedException("Unsupported search type: " + searchRequestInfo.getSearchKey());
        }
    }

    /**
     *
     * @param searchRequestInfo   Contains an Activity Offering ID that we will use to find the scheduleId
     * @param contextInfo
     * @return
     * @throws org.kuali.student.r2.common.exceptions.MissingParameterException
     * @throws org.kuali.student.r2.common.exceptions.OperationFailedException
     * @throws org.kuali.student.r2.common.exceptions.PermissionDeniedException
     */
    protected SearchResultInfo searchForScheduleAndRoomById(SearchRequestInfo searchRequestInfo, ContextInfo contextInfo) throws MissingParameterException, OperationFailedException, PermissionDeniedException {
        SearchRequestHelper requestHelper = new SearchRequestHelper(searchRequestInfo);

        String aoId = requestHelper.getParamAsString(SearchParameters.AO_ID);

        if (aoId == null || aoId.isEmpty()){
            throw new RuntimeException("Activity Offering id is required");
        }

        List<String> results = entityManager.createNamedQuery("Lui.getScheduleIdByLuiId").setParameter("aoId", aoId).getResultList();


         //= getEntityManager().createQuery(query).getResultList();

        SearchResultInfo resultInfo = new SearchResultInfo();
        resultInfo.setTotalResults(results.size());
        resultInfo.setStartAt(0);

        for (String result : results) {
            SearchResultRowInfo row = new SearchResultRowInfo();
            resultInfo.getRows().add(row);
            int i=0;
            row.addCell("scheduleId",(String)result);
        }

        return resultInfo;

    }


    private static String commaString(List<String> items){
        StringBuilder sb = new StringBuilder();
        String delim = "";
        for (String str : items) {
            sb.append(delim).append("'" + str + "'");
            delim = ",";
        }
        return sb.toString();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}
