package org.kuali.student.enrollment.class1.lrc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.kuali.student.enrollment.class1.lrc.dao.ResultScaleDao;
import org.kuali.student.enrollment.class1.lrc.dao.ResultValueDao;
import org.kuali.student.enrollment.class1.lrc.dao.ResultValuesGroupDao;
import org.kuali.student.enrollment.class1.lrc.model.ResultScaleEntity;
import org.kuali.student.enrollment.class1.lrc.model.ResultValueEntity;
import org.kuali.student.enrollment.class1.lrc.model.ResultValuesGroupEntity;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.StatusInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.common.exceptions.AlreadyExistsException;
import org.kuali.student.r2.common.exceptions.DataValidationErrorException;
import org.kuali.student.r2.common.exceptions.DoesNotExistException;
import org.kuali.student.r2.common.exceptions.InvalidParameterException;
import org.kuali.student.r2.common.exceptions.MissingParameterException;
import org.kuali.student.r2.common.exceptions.OperationFailedException;
import org.kuali.student.r2.common.exceptions.PermissionDeniedException;
import org.kuali.student.r2.common.exceptions.VersionMismatchException;
import org.kuali.student.r2.core.state.service.StateService;
import org.kuali.student.r2.lum.lrc.dto.ResultScaleInfo;
import org.kuali.student.r2.lum.lrc.dto.ResultValueInfo;
import org.kuali.student.r2.lum.lrc.dto.ResultValuesGroupInfo;
import org.kuali.student.r2.lum.lrc.service.LRCService;
import org.springframework.transaction.annotation.Transactional;

@WebService(name = "LrcService", serviceName = "LrcService", portName = "LrcService", targetNamespace = "http://student.kuali.org/wsdl/lrc")
@Transactional(readOnly = true, noRollbackFor = {DoesNotExistException.class}, rollbackFor = {Throwable.class})
public class LRCServiceImpl implements LRCService {

    private ResultValuesGroupDao resultValuesGroupDao;
    private ResultValueDao resultValueDao;
    private ResultScaleDao resultScaleDao;
    private StateService stateService;

    @Override
    public ResultValuesGroupInfo getResultValuesGroup(
            @WebParam(name = "resultValuesGroupId") String resultValuesGroupId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        ResultValuesGroupEntity entity = resultValuesGroupDao.find(resultValuesGroupId);
        if (entity != null) {
            return entity.toDto();
        }
        return null;
    }

    @Override
    public List<ResultValuesGroupInfo> getResultValuesGroupsByKeys(
            @WebParam(name = "resultValuesGroupIds") List<String> resultValuesGroupIds,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<ResultValuesGroupEntity> entities = resultValuesGroupDao.findByIds(resultValuesGroupIds);
        List<ResultValuesGroupInfo> resultValuesGroupInfos = new ArrayList<ResultValuesGroupInfo>();
        for (ResultValuesGroupEntity entity : entities) {
            resultValuesGroupInfos.add(entity.toDto());
        }
        return resultValuesGroupInfos;
    }

    @Override
    public List<ResultValuesGroupInfo> getResultValuesGroupsByResultValue(
            @WebParam(name = "resultValueId") String resultValueId, @WebParam(name = "contextInfo") ContextInfo contextInfo)
            throws DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public List<String> getResultValuesGroupKeysByType(
            @WebParam(name = "resultValuesGroupTypeKey") String resultValuesGroupTypeKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public ResultValuesGroupInfo createResultValuesGroup(
            String resultScaleKey,
            String resultValuesGroupTypeKey,
            ResultValuesGroupInfo gradeValuesGroupInfo,
            ContextInfo contextInfo) throws AlreadyExistsException,
            DataValidationErrorException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        ResultValuesGroupEntity entity = resultValuesGroupDao.find(gradeValuesGroupInfo.getKey());
        if (entity != null) {
            throw new AlreadyExistsException(gradeValuesGroupInfo.getKey() + " already exists");
        }

        ResultValuesGroupEntity newEntity = new ResultValuesGroupEntity(gradeValuesGroupInfo);
        if (StringUtils.isNotBlank(gradeValuesGroupInfo.getStateKey())) {
            newEntity.setState(gradeValuesGroupInfo.getStateKey());
        }

        if (StringUtils.isNotBlank(gradeValuesGroupInfo.getTypeKey())) {
            newEntity.setType(gradeValuesGroupInfo.getTypeKey());
        }

//        if (gradeValuesGroupInfo.getResultValueKeys() != null) {
//            newEntity.setResultValues(resultValueDao.findByIds(gradeValuesGroupInfo.getResultValueKeys()));
//        }

        resultValuesGroupDao.persist(newEntity);

        return resultValuesGroupDao.find(newEntity.getId()).toDto();
    }

    @Override
    public ResultValuesGroupInfo updateResultValuesGroup(
            @WebParam(name = "resultValuesGroupId") String resultValuesGroupId,
            @WebParam(name = "resultValuesGroupInfo") ResultValuesGroupInfo gradeValuesGroupInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, VersionMismatchException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public StatusInfo deleteResultValuesGroup(@WebParam(name = "resultValuesGroupId") String resultValuesGroupId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public List<ValidationResultInfo> validateResultValuesGroup(
            @WebParam(name = "validationType") String validationType,
            @WebParam(name = "resultGroupInfo") ResultValuesGroupInfo gradeValuesGroupInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public ResultValueInfo getResultValue(@WebParam(name = "resultValueId") String resultValueId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        ResultValueEntity entity = resultValueDao.find(resultValueId);
        if (entity != null) {
            return entity.toDto();
        }
        return null;
    }

    @Override
    public List<ResultValueInfo> getResultValuesByKeys(@WebParam(name = "resultValueIds") List<String> resultValueIds,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        List<ResultValueEntity> entities = resultValueDao.findByIds(resultValueIds);
        List<ResultValueInfo> infos = new ArrayList<ResultValueInfo>();
        for (ResultValueEntity entity : entities) {
            infos.add(entity.toDto());
        }
        return infos;
    }

    @Override
    public List<ResultValueInfo> getResultValuesForResultValuesGroup(
            @WebParam(name = "resultValuesGroupId") String resultValuesGroupId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public ResultValueInfo createResultValue(String resultScaleKey, String resultValueTypeKey, ResultValueInfo resultValueInfo,
            ContextInfo contextInfo) throws AlreadyExistsException,
            DataValidationErrorException, DoesNotExistException, InvalidParameterException, MissingParameterException,
            OperationFailedException, PermissionDeniedException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public ResultValueInfo updateResultValue(@WebParam(name = "resultValueId") String resultValueId,
            @WebParam(name = "resultValueInfo") ResultValueInfo resultValueInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DataValidationErrorException,
            DoesNotExistException, InvalidParameterException, MissingParameterException, OperationFailedException,
            PermissionDeniedException, VersionMismatchException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public StatusInfo deleteResultValue(@WebParam(name = "resultValueId") String resultValueId,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public List<ValidationResultInfo> validateResultValue(@WebParam(name = "validationType") String validationType,
            @WebParam(name = "resultValueInfo") ResultValueInfo resultValueInfo,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    @Override
    public ResultScaleInfo getResultScale(@WebParam(name = "resultScaleKey") String resultScaleKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        ResultScaleEntity entity = resultScaleDao.find(resultScaleKey);
        if (entity != null) {
            return entity.toDto();
        }
        return null;
    }

    @Override
    public List<ResultValueInfo> getResultValuesForScale(@WebParam(name = "resultScaleKey") String resultScaleKey,
            @WebParam(name = "contextInfo") ContextInfo contextInfo) throws DoesNotExistException, InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        throw new UnsupportedOperationException("Method not implemented."); // TODO implement method
    }

    /*private StateEntity findState(String stateKey, ContextInfo contextInfo) throws InvalidParameterException,
            MissingParameterException, OperationFailedException, PermissionDeniedException {
        StateEntity state = null;
        try {
            StateInfo stInfo = stateService.getState(stateKey, contextInfo);
            if (stInfo != null) {
                state = new StateEntity(stInfo);
                return state;
            }
            else
                throw new OperationFailedException("The state does not exist. stateKey: " + stateKey);
        } catch (DoesNotExistException e) {
            throw new OperationFailedException("The state does not exist. stateKey: " + stateKey);
        }
    }*/

    public ResultValuesGroupDao getResultValuesGroupDao() {
        return resultValuesGroupDao;
    }

    public void setResultValuesGroupDao(ResultValuesGroupDao resultValuesGroupDao) {
        this.resultValuesGroupDao = resultValuesGroupDao;
    }

    public ResultValueDao getResultValueDao() {
        return resultValueDao;
    }

    public void setResultValueDao(ResultValueDao resultValueDao) {
        this.resultValueDao = resultValueDao;
    }

    public ResultScaleDao getResultScaleDao() {
        return resultScaleDao;
    }

    public void setResultScaleDao(ResultScaleDao resultScaleDao) {
        this.resultScaleDao = resultScaleDao;
    }

    public StateService getStateService() {
        return stateService;
    }

    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Override
    public ResultValuesGroupInfo getCreateFixedCreditResultValuesGroup(String creditValue,
            String scaleKey,
            ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultValuesGroupInfo getCreateMultipleCreditResultValuesGroup(List<String> creditValues,
            String scaleKey,
            ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultValuesGroupInfo getCreateRangeCreditResultValuesGroup(String creditValueMin,
            String creditValueMax,
            String creditValueIncrement,
            String scaleKey,
            ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultValueInfo getCreateResultValueForScale(String resultValue,
            String scaleKey,
            ContextInfo contextInfo)
            throws InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ResultValueInfo> getResultValuesForResultValuesGroups(List<String> resultValuesGroupKeys,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultScaleInfo createResultScale(String resultTypeKey, ResultScaleInfo resultScaleInfo,
            ContextInfo contextInfo)
            throws AlreadyExistsException,
            DataValidationErrorException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StatusInfo deleteResultScale(String resultScaleKey,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getResultScaleKeysByType(String resultScaleTypeKey,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ResultScaleInfo> getResultScalesByKeys(List<String> resultScaleKeys,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ResultValuesGroupInfo> getResultValuesGroupsByResultScale(String resultScaleKey,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ResultValuesGroupInfo> getResultValuesGroupsByResultScaleType(String resultScaleTypeKey,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultScaleInfo updateResultScale(String resultScaleKey,
            ResultScaleInfo resultScaleInfo,
            ContextInfo contextInfo)
            throws DataValidationErrorException,
            DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException,
            VersionMismatchException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ValidationResultInfo> validateResultScale(String validationType,
            ResultScaleInfo gradeScaleInfo,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultValueInfo getResultValueForScaleAndValue(String resultScaleKey,
            String value,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> getResultValueKeysByType(String resultValueTypeKey,
            ContextInfo contextInfo)
            throws DoesNotExistException,
            InvalidParameterException,
            MissingParameterException,
            OperationFailedException,
            PermissionDeniedException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
