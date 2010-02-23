TRUNCATE TABLE KSLU_LO_CATEGORY DROP STORAGE
/
INSERT INTO KSLU_LO_CATEGORY (EFF_DT,EXPIR_DT,ID,LO_CATEGORY_TYPE_ID,LO_REPO_ID,NAME,RT_DESCR_ID,STATE,VERSIONIND)
  VALUES (TO_DATE( '20080101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20100101000000', 'YYYYMMDDHH24MISS' ),'054CAA88-C21D-4496-8287-36A311A11D68','loCategoryType.subject','kuali.loRepository.key.singleUse','Test Category 2','RICHTEXT-9','active',1)
/
INSERT INTO KSLU_LO_CATEGORY (EFF_DT,EXPIR_DT,ID,LO_CATEGORY_TYPE_ID,LO_REPO_ID,NAME,RT_DESCR_ID,STATE,VERSIONIND)
  VALUES (TO_DATE( '20080101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20100101000000', 'YYYYMMDDHH24MISS' ),'162979A3-25B9-4921-BC8F-C861B2267A73','loCategoryType.skillarea','kuali.loRepository.key.singleUse','Test Category 3','RICHTEXT-9','active',1)
/
INSERT INTO KSLU_LO_CATEGORY (EFF_DT,EXPIR_DT,ID,LO_CATEGORY_TYPE_ID,LO_REPO_ID,NAME,RT_DESCR_ID,STATE,VERSIONIND)
  VALUES (TO_DATE( '20080101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20100101000000', 'YYYYMMDDHH24MISS' ),'550e8400-e29b-41d4-a716-446655440000','loCategoryType.skillarea','kuali.loRepository.key.singleUse','Perception','RICHTEXT-14','active',1)
/
INSERT INTO KSLU_LO_CATEGORY (EFF_DT,EXPIR_DT,ID,LO_CATEGORY_TYPE_ID,LO_REPO_ID,NAME,RT_DESCR_ID,STATE,VERSIONIND)
  VALUES (TO_DATE( '20080101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20100101000000', 'YYYYMMDDHH24MISS' ),'7114D2A4-F66D-4D3A-9D41-A7AA4299C797','loCategoryType.subject','kuali.loRepository.key.singleUse','Test Category 4','RICHTEXT-9','active',1)
/
INSERT INTO KSLU_LO_CATEGORY (EFF_DT,EXPIR_DT,ID,LO_CATEGORY_TYPE_ID,LO_REPO_ID,NAME,RT_DESCR_ID,STATE,VERSIONIND)
  VALUES (TO_DATE( '20080101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20100101000000', 'YYYYMMDDHH24MISS' ),'F2F02922-4E77-4144-AA07-8C2C956370DC','loCategoryType.skillarea','kuali.loRepository.key.singleUse','Empty Test Category','RICHTEXT-19','active',1)
/
