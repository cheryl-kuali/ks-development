-- insert kim responsibility for 'Resolve Exception' responsibility template
INSERT INTO KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,ACTV_IND,RSP_TMPL_ID,VER_NBR,DESC_TXT,OBJ_ID) 
  VALUES ('1000','KS-SYS','Resolve Exception','Y','2',0,'Responsibility for Kuali Student Exception Routing','5ADFE1V2441D6320E04AAAA189D85169');
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('1000','KualiStudentDocument','13','54','5G4F09744G28EF33E0404F8189AAAF24','1000',1);
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','BC27A267EF607417E0404F8189DAA0A9','1000','63','1',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('1000','A102F3FA08CF45CFAA404FBB89D831AA',1,'A','F','*','1','Y');


--COURSE PROPOSAL WORKFLOW--------------------------------  
  
--ROLES------------------------------
--Department admin reviewer
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KS-LUM' AND NM='Department Type'),'KS-LUM','DEPARTMENTADMINREVIEWER00000ROLE','900','Department Admin Reviewer',1);
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KS-LUM' AND NM='Division Type'),'KS-LUM','DIVISIONADMINREVIEWER0000000ROLE','901','Division Admin Reviewer',1);
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KS-LUM' AND NM='Division Type'),'KS-LUM','DIVISIONCOMMITTEEREVIEWER000ROLE','902','Division Committee Reviewer',1);
--INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
--  VALUES ('Y','1','KS-LUM','UNDERGRADADMINREVIEWER000000ROLE','903','Undergrad Admin Reviewer',1);
--INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
--  VALUES ('Y','1','KS-LUM','UNDERGRADCOMMITTEEREVIEWER00ROLE','904','Undergrad Committee Reviewer',1);
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KS-LUM' AND NM='College Type'),'KS-LUM','COLLEGEADMINREVIEWER00000000ROLE','905','College Admin Reviewer',1);
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KS-LUM' AND NM='College Type'),'KS-LUM','COLLEGECOMMITTEEREVIEWER0000ROLE','906','College Committee Reviewer',1);
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y','1','KS-LUM','SENATEADMINREVIEWER000000000ROLE','907','Senate Admin Reviewer',1);
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y','1','KS-LUM','SENATECOMMITTEEREVIEWER00000ROLE','908','Senate Committee Reviewer',1);
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y','1','KS-LUM','PUBLICATIONREVIEWER000000000ROLE','909','Publication Reviewer',1); 
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KS-SYS' AND NM='Derived Role: Org Admin Type'),'KS-LUM','OrgAdminReviewer000000000000ROLE','910','Org Admin Reviewer',1); 
INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KS-SYS' AND NM='Derived Role: Org Committee Type'),'KS-LUM','OrgCommitteeReviewer00000000ROLE','911','Org Committee Reviewer',1); 

INSERT INTO KRIM_ROLE_T (ACTV_IND,KIM_TYP_ID,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) 
  VALUES ('Y',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KS-LUM' AND NM='Department Type'),'KS-LUM','DEPARTMENTCOMITREVIEWER00000ROLE','917','Department Committee Reviewer',1);

  
--RESPONSIBILITIES-------------------
--DepartmentReview-----
INSERT INTO KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,ACTV_IND,RSP_TMPL_ID,VER_NBR,DESC_TXT,OBJ_ID) 
  VALUES ('300','KS-LUM','Review','Y','1',0,'Responsibility for Department Review','DepartmentReview0000000000000RSP');
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40001','CluCreditCourseParentDocument','13','7','DepartmentReview0000000RSPATTR01','300',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40002','Department Review','16','7','DepartmentReview0000000RSPATTR02','300',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40003','false','41','7','DepartmentReview0000000RSPATTR03','300',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40004','false','40','7','DepartmentReview0000000RSPATTR04','300',1);
--DepartmentReview admin kim
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','DepartmentReview0000000ROLERSP01','300','900','50001',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60001','DepartmentReview000ROLERSPACTN01',1,'A','F','*','50001','Y');
--DepartmentReview org admin kim derived
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','DepartmentReview0000000ROLERSP02','300','910','50002',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60002','DepartmentReview000ROLERSPACTN02',1,'A','F','*','50002','Y');
--DepartmentReview committee kim
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','DepartmentReview0000000ROLERSP03','300','917','50003',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60003','DepartmentReview000ROLERSPACTN03',1,'F','F','*','50003','Y');
--DepartmentReview org committee kim derived
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','DepartmentReview0000000ROLERSP04','300','911','50004',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60004','DepartmentReview000ROLERSPACTN04',1,'F','F','*','50004','Y'); 
  
  
--CollegeReview-----
INSERT INTO KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,ACTV_IND,RSP_TMPL_ID,VER_NBR,DESC_TXT,OBJ_ID) 
  VALUES ('301','KS-LUM','Review','Y','1',0,'Responsibility for College Review','CollegeReview0000000000000000RSP');
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40101','CluCreditCourseParentDocument','13','7','CollegeReview0000000000RSPATTR01','301',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40102','College Review','16','7','CollegeReview0000000000RSPATTR02','301',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40103','false','41','7','CollegeReview0000000000RSPATTR03','301',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40104','false','40','7','CollegeReview0000000000RSPATTR04','301',1);
--CollegeReview admin kim
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','CollegeReview0000000000ROLERSP01','301','905','50101',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60101','CollegeReview000000ROLERSPACTN01',1,'A','F','*','50101','Y');
--CollegeReview org admin kim derived
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','CollegeReview0000000000ROLERSP02','301','910','50102',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60102','CollegeReview000000ROLERSPACTN02',1,'A','F','*','50102','Y'); 
--CollegeReview committee kim
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','CollegeReview0000000000ROLERSP03','301','906','50103',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60103','CollegeReview000000ROLERSPACTN03',1,'F','F','*','50103','Y');
--CollegeReview org committee kim derived
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','CollegeReview0000000000ROLERSP04','301','911','50104',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60104','CollegeReview000000ROLERSPACTN04',1,'F','F','*','50104','Y'); 

--UndergradReview-----
--INSERT INTO KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,ACTV_IND,RSP_TMPL_ID,VER_NBR,DESC_TXT,OBJ_ID) 
--  VALUES ('302','KS-LUM','Review','Y','1',0,'Responsibility for Undergrad Affairs Review','UndergradReview00000000000000RSP');
--INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
--  VALUES ('40201','CluCreditCourseParentDocument','13','7','UndergradReview00000000RSPATTR01','302',1);
--INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
--  VALUES ('40202','Undergrad Affairs Review','16','7','UndergradReview00000000RSPATTR02','302',1);
--INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
--  VALUES ('40203','false','41','7','UndergradReview00000000RSPATTR03','302',1);
--INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
--  VALUES ('40204','false','40','7','UndergradReview00000000RSPATTR04','302',1);
--UndergradReview admin kim
--INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
--  VALUES ('Y','UndergradReview00000000ROLERSP01','302','903','50201',1);
--INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
--  VALUES ('60201','UndergradReview0000ROLERSPACTN01',1,'A','F','*','50201','Y');
--UndergradReview org admin kim derived
--INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
--  VALUES ('Y','UndergradReview00000000ROLERSP02','302','910','50202',1);
--INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
--  VALUES ('60202','UndergradReview0000ROLERSPACTN02',1,'A','F','*','50202','Y'); 
--UndergradReview committee kim
--INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
--  VALUES ('Y','UndergradReview00000000ROLERSP03','302','904','50203',1);
--INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
--  VALUES ('60203','UndergradReview0000ROLERSPACTN03',1,'F','F','*','50203','Y');
--UndergradReview org committee kim derived
--INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
--  VALUES ('Y','UndergradReview00000000ROLERSP04','302','911','50204',1);
--INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
--  VALUES ('60204','UndergradReview0000ROLERSPACTN04',1,'F','F','*','50204','Y');   

--DivisionReview-----
INSERT INTO KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,ACTV_IND,RSP_TMPL_ID,VER_NBR,DESC_TXT,OBJ_ID) 
  VALUES ('303','KS-LUM','Review','Y','1',0,'Responsibility for Division Review','DivisionReview000000000000000RSP');
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40301','CluCreditCourseParentDocument','13','7','DivisionReview000000000RSPATTR01','303',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40302','Division Review','16','7','DivisionReview000000000RSPATTR02','303',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40303','false','41','7','DivisionReview000000000RSPATTR03','303',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40304','false','40','7','DivisionReview000000000RSPATTR04','303',1);
--DivisionReview admin kim
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','DivisionReview000000000ROLERSP01','303','901','50301',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60301','DivisionReview00000ROLERSPACTN01',1,'A','F','*','50301','Y');
--DivisionReview org admin kim derived
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','DivisionReview000000000ROLERSP02','303','910','50302',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60302','DivisionReview00000ROLERSPACTN02',1,'A','F','*','50302','Y'); 
--DivisionReview committee kim
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','DivisionReview000000000ROLERSP03','303','902','50303',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60303','DivisionReview00000ROLERSPACTN03',1,'F','F','*','50303','Y');
--DivisionReview org committee kim derived
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','DivisionReview000000000ROLERSP04','303','911','50304',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60304','DivisionReview00000ROLERSPACTN04',1,'F','F','*','50304','Y');

--SenateReview-----
INSERT INTO KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,ACTV_IND,RSP_TMPL_ID,VER_NBR,DESC_TXT,OBJ_ID) 
  VALUES ('304','KS-LUM','Review','Y','1',0,'Responsibility for Senate Review','SenateReview00000000000000000RSP');
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40401','CluCreditCourseParentDocument','13','7','SenateReview00000000000RSPATTR01','304',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40402','Senate Review','16','7','SenateReview00000000000RSPATTR02','304',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40403','false','41','7','SenateReview00000000000RSPATTR03','304',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40404','false','40','7','SenateReview00000000000RSPATTR04','304',1);
--SenateReview admin kim
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','SenateReview00000000000ROLERSP01','304','907','50401',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60401','SenateReview0000000ROLERSPACTN01',1,'A','F','*','504301','Y');
--SenateReview org admin kim derived
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','SenateReview00000000000ROLERSP02','304','910','50402',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60402','SenateReview0000000ROLERSPACTN02',1,'A','F','*','50402','Y'); 
--SenateReview committee kim
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','SenateReview00000000000ROLERSP03','304','908','50403',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60403','SenateReview0000000ROLERSPACTN03',1,'F','F','*','50403','Y');
--SenateReview org committee kim derived
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','SenateReview00000000000ROLERSP04','304','911','50404',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60404','SenateReview0000000ROLERSPACTN04',1,'F','F','*','50404','Y');    

--PublicationReview-----
INSERT INTO KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,ACTV_IND,RSP_TMPL_ID,VER_NBR,DESC_TXT,OBJ_ID) 
  VALUES ('305','KS-LUM','Review','Y','1',0,'Responsibility for Publication Review','PublicationReview000000000000RSP');
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40501','CluCreditCourseParentDocument','13','7','PublicationReview000000RSPATTR01','305',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40502','Publication Review','16','7','PublicationReview000000RSPATTR02','305',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40503','false','41','7','PublicationReview000000RSPATTR03','305',1);
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,RSP_ID,VER_NBR) 
  VALUES ('40504','false','40','7','PublicationReview000000RSPATTR04','305',1);
--PublicationReview admin kim
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','PublicationReview000000ROLERSP01','305','909','50501',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60501','PublicationReview00ROLERSPACTN01',1,'A','F','*','50501','Y');
--PublicationReview org admin kim derived
INSERT INTO KRIM_ROLE_RSP_T (ACTV_IND,OBJ_ID,RSP_ID,ROLE_ID,ROLE_RSP_ID,VER_NBR) 
  VALUES ('Y','PublicationReview000000ROLERSP02','305','910','50502',1);
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,OBJ_ID,VER_NBR,ACTN_TYP_CD,ACTN_PLCY_CD,ROLE_MBR_ID,ROLE_RSP_ID,FRC_ACTN) 
  VALUES ('60502','PublicationReview00ROLERSPACTN02',1,'A','F','*','50502','Y');

--sample members
--INSERT INTO KRIM_ROLE_MBR_T (MBR_ID,MBR_TYP_CD,OBJ_ID,ROLE_ID,ROLE_MBR_ID,VER_NBR) 
--  VALUES ('fred','P','5BAA421E43857717E04AAF8189D821F7','901','1292',1);
--INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (VER_NBR,OBJ_ID,ATTR_DATA_ID,KIM_TYP_ID,ROLE_MBR_ID,KIM_ATTR_DEFN_ID,ATTR_VAL) 
--  VALUES (1,'5B4AA21CV385AA17E0404AA189DAA1F7','7','101','1292','101','Arts & Hum COC');