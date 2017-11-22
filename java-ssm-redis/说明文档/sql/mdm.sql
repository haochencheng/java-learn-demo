prompt PL/SQL Developer import file
prompt Created on 2017年1月10日 by Administrator
set feedback off
set define off
prompt Creating CODE_RULES...
create table CODE_RULES
(
  extendfive      VARCHAR2(50),
  extendfour      VARCHAR2(50),
  extendthree     VARCHAR2(50),
  extendtwo       VARCHAR2(50),
  extendone       VARCHAR2(50),
  version         VARCHAR2(50),
  creator         VARCHAR2(50),
  remark          VARCHAR2(200),
  description     VARCHAR2(200),
  rulesalias      VARCHAR2(50),
  statusswitch    VARCHAR2(50),
  rulesstatus     VARCHAR2(50),
  sourceswitch    VARCHAR2(50),
  codesource      VARCHAR2(50),
  validateswitch  VARCHAR2(50),
  rulesseparator  VARCHAR2(50),
  rulesname       VARCHAR2(50),
  categorycode    VARCHAR2(50),
  rulesid         VARCHAR2(50) not null,
  updatetime      DATE,
  createtime      DATE,
  segmentquantity INTEGER,
  maxlength       INTEGER,
  minlength       INTEGER,
  formatswitch    VARCHAR2(50)
)
;
create unique index SYS_C0043451 on CODE_RULES (RULESID);
alter table CODE_RULES
  add primary key (RULESID);

prompt Creating CODE_RULES_CATEGORY...
create table CODE_RULES_CATEGORY
(
  extendthree         VARCHAR2(50),
  extendtwo           VARCHAR2(50),
  extendone           VARCHAR2(50),
  version             VARCHAR2(50),
  creator             VARCHAR2(50),
  remark              VARCHAR2(200),
  description         VARCHAR2(200),
  status              VARCHAR2(50),
  categorycode        VARCHAR2(50),
  categoryname        VARCHAR2(50),
  parentid            VARCHAR2(50),
  coderulescategoryid VARCHAR2(50) not null,
  updatetime          DATE,
  createtime          DATE,
  sortindex           INTEGER
)
;
create unique index SYS_C0043528 on CODE_RULES_CATEGORY (CODERULESCATEGORYID);
alter table CODE_RULES_CATEGORY
  add primary key (CODERULESCATEGORYID);

prompt Creating SYS_AUTH...
create table SYS_AUTH
(
  authid       VARCHAR2(50) default SYS_GUID() not null,
  authname     VARCHAR2(50) not null,
  authpath     VARCHAR2(100),
  parentid     VARCHAR2(50) not null,
  state        VARCHAR2(20) not null,
  iconclsid    VARCHAR2(50) not null,
  authdescribe VARCHAR2(200),
  expandone    VARCHAR2(50),
  expandtwo    VARCHAR2(50),
  expandthree  VARCHAR2(50)
)
;
alter table SYS_AUTH
  add constraint PID_AUTHID primary key (AUTHID);

prompt Creating SYS_ROLE...
create table SYS_ROLE
(
  roleid       VARCHAR2(50) default SYS_GUID() not null,
  rolename     VARCHAR2(50) not null,
  roledescribe VARCHAR2(100),
  expandone    VARCHAR2(50),
  expandtwo    VARCHAR2(50),
  expandthree  VARCHAR2(50)
)
;
alter table SYS_ROLE
  add constraint SYS_ROLE_PK primary key (ROLEID);

prompt Creating SYS_ROLE_AUTH...
create table SYS_ROLE_AUTH
(
  id               VARCHAR2(50) default sys_guid() not null,
  roleid           VARCHAR2(50) not null,
  authid           VARCHAR2(50) not null,
  expandone        VARCHAR2(50),
  expandtwo        VARCHAR2(50),
  expandthree      VARCHAR2(50),
  roleauthdescribe VARCHAR2(100)
)
;
alter table SYS_ROLE_AUTH
  add constraint ROLE_AUTH_ID primary key (ID);
alter table SYS_ROLE_AUTH
  add constraint PID_AUTH_ID foreign key (AUTHID)
  references SYS_AUTH (AUTHID);
alter table SYS_ROLE_AUTH
  add constraint PID_ROLE_ID foreign key (ROLEID)
  references SYS_ROLE (ROLEID);

prompt Creating SYS_USER...
create table SYS_USER
(
  id           VARCHAR2(50) default SYS_GUID() not null,
  username     VARCHAR2(50) not null,
  password     VARCHAR2(50) not null,
  roleid       VARCHAR2(50) not null,
  usertype     VARCHAR2(20),
  userdescribe VARCHAR2(20),
  expandone    VARCHAR2(20),
  expandtwo    VARCHAR2(20),
  expandthree  VARCHAR2(20)
)
;
alter table SYS_USER
  add primary key (ID);
alter table SYS_USER
  add constraint SYS_USER_FK1 foreign key (ROLEID)
  references SYS_ROLE (ROLEID);

prompt Disabling triggers for CODE_RULES...
alter table CODE_RULES disable all triggers;
prompt Disabling triggers for CODE_RULES_CATEGORY...
alter table CODE_RULES_CATEGORY disable all triggers;
prompt Disabling triggers for SYS_AUTH...
alter table SYS_AUTH disable all triggers;
prompt Disabling triggers for SYS_ROLE...
alter table SYS_ROLE disable all triggers;
prompt Disabling triggers for SYS_ROLE_AUTH...
alter table SYS_ROLE_AUTH disable all triggers;
prompt Disabling triggers for SYS_USER...
alter table SYS_USER disable all triggers;
prompt Disabling foreign key constraints for SYS_ROLE_AUTH...
alter table SYS_ROLE_AUTH disable constraint PID_AUTH_ID;
alter table SYS_ROLE_AUTH disable constraint PID_ROLE_ID;
prompt Disabling foreign key constraints for SYS_USER...
alter table SYS_USER disable constraint SYS_USER_FK1;
prompt Deleting SYS_USER...
delete from SYS_USER;
commit;
prompt Deleting SYS_ROLE_AUTH...
delete from SYS_ROLE_AUTH;
commit;
prompt Deleting SYS_ROLE...
delete from SYS_ROLE;
commit;
prompt Deleting SYS_AUTH...
delete from SYS_AUTH;
commit;
prompt Deleting CODE_RULES_CATEGORY...
delete from CODE_RULES_CATEGORY;
commit;
prompt Deleting CODE_RULES...
delete from CODE_RULES;
commit;
prompt Loading CODE_RULES...

prompt Loading CODE_RULES_CATEGORY...
insert into CODE_RULES_CATEGORY (extendthree, extendtwo, extendone, version, creator, remark, description, status, categorycode, categoryname, parentid, coderulescategoryid, updatetime, createtime, sortindex)
values (null, null, null, null, null, null, '小类', 'Enable', null, '测试数据小类', 'B67EBAA8B8D94D638CD6B686DC754ECF', 'B96A4F72F0F123djhijB02693441880', to_date('05-12-2016', 'dd-mm-yyyy'), to_date('05-12-2016', 'dd-mm-yyyy'), null);
insert into CODE_RULES_CATEGORY (extendthree, extendtwo, extendone, version, creator, remark, description, status, categorycode, categoryname, parentid, coderulescategoryid, updatetime, createtime, sortindex)
values (null, null, null, '1', 'admin', null, '啊', 'Enable', null, '测试数据大类', '0', '10C492D6F5BC4E10BE68B38853718234', to_date('24-11-2016', 'dd-mm-yyyy'), to_date('24-11-2016', 'dd-mm-yyyy'), null);
insert into CODE_RULES_CATEGORY (extendthree, extendtwo, extendone, version, creator, remark, description, status, categorycode, categoryname, parentid, coderulescategoryid, updatetime, createtime, sortindex)
values (null, null, null, '1', 'admin', null, '1', 'Enable', null, '测试数据中类', '10C492D6F5BC4E10BE68B38853718234', 'B67EBAA8B8D94D638CD6B686DC754ECF', to_date('24-11-2016', 'dd-mm-yyyy'), to_date('24-11-2016', 'dd-mm-yyyy'), null);
insert into CODE_RULES_CATEGORY (extendthree, extendtwo, extendone, version, creator, remark, description, status, categorycode, categoryname, parentid, coderulescategoryid, updatetime, createtime, sortindex)
values (null, null, null, '1.0', 'admin', null, '测试数据', 'Enable', null, '测试数据', '0', 'FISA4F72F0F44C2A88098FDSEIUE9F976', to_date('05-12-2016', 'dd-mm-yyyy'), to_date('05-12-2016', 'dd-mm-yyyy'), null);
commit;
prompt 4 records loaded
prompt Loading SYS_AUTH...
insert into SYS_AUTH (authid, authname, authpath, parentid, state, iconclsid, authdescribe, expandone, expandtwo, expandthree)
values ('2555665049C041FC86B78FCC73BC16D2', '不知道什么系统', null, '0', 'open', '1', null, null, null, null);
insert into SYS_AUTH (authid, authname, authpath, parentid, state, iconclsid, authdescribe, expandone, expandtwo, expandthree)
values ('F83DE15A8DB24318B8933D9181C1A887', '编码规则管理', null, '2555665049C041FC86B78FCC73BC16D2', 'close', '2', null, null, null, null);
insert into SYS_AUTH (authid, authname, authpath, parentid, state, iconclsid, authdescribe, expandone, expandtwo, expandthree)
values ('4A8F41500882458E987777F263ACB0FB', '新建编码规则', '/rule/add.html', 'F83DE15A8DB24318B8933D9181C1A887', 'open', '3', null, null, null, null);
insert into SYS_AUTH (authid, authname, authpath, parentid, state, iconclsid, authdescribe, expandone, expandtwo, expandthree)
values ('4407F2D0FDFD4B72924119CB8620E06C', '编码规则维护', '/rule/edit.html', 'F83DE15A8DB24318B8933D9181C1A887', 'close', '4', null, null, null, null);
insert into SYS_AUTH (authid, authname, authpath, parentid, state, iconclsid, authdescribe, expandone, expandtwo, expandthree)
values ('6770C899C5214EB99704772A9FD99C92', '测试一', null, '1', 'open', '1', null, null, null, null);
insert into SYS_AUTH (authid, authname, authpath, parentid, state, iconclsid, authdescribe, expandone, expandtwo, expandthree)
values ('6FBCA3E0D2FF4CE3924880B52E2094B5', '测试二', null, '1', 'open', '2', null, null, null, null);
commit;
prompt 6 records loaded
prompt Loading SYS_ROLE...
insert into SYS_ROLE (roleid, rolename, roledescribe, expandone, expandtwo, expandthree)
values ('A82A57D91F0E4CFA81535FA9CA343E60', '管理员', null, null, null, null);
insert into SYS_ROLE (roleid, rolename, roledescribe, expandone, expandtwo, expandthree)
values ('A77AAEB9AF3D4C0AAD64BEF9D99908BD', '员工', null, null, null, null);
commit;
prompt 2 records loaded
prompt Loading SYS_ROLE_AUTH...
insert into SYS_ROLE_AUTH (id, roleid, authid, expandone, expandtwo, expandthree, roleauthdescribe)
values ('92EB65A4B67C46AAB7BBFCA2F8057E49', 'A82A57D91F0E4CFA81535FA9CA343E60', '4407F2D0FDFD4B72924119CB8620E06C', null, null, null, null);
insert into SYS_ROLE_AUTH (id, roleid, authid, expandone, expandtwo, expandthree, roleauthdescribe)
values ('BB7C3D51EB6547C3872D7FEEF616E464', 'A82A57D91F0E4CFA81535FA9CA343E60', '4A8F41500882458E987777F263ACB0FB', null, null, null, null);
insert into SYS_ROLE_AUTH (id, roleid, authid, expandone, expandtwo, expandthree, roleauthdescribe)
values ('2AD07F29E20C4FA0B1103A11A8BE9387', 'A82A57D91F0E4CFA81535FA9CA343E60', 'F83DE15A8DB24318B8933D9181C1A887', null, null, null, null);
insert into SYS_ROLE_AUTH (id, roleid, authid, expandone, expandtwo, expandthree, roleauthdescribe)
values ('7FF00D1D2E9C420AAB128F18DD54675B', 'A82A57D91F0E4CFA81535FA9CA343E60', '6770C899C5214EB99704772A9FD99C92', null, null, null, null);
insert into SYS_ROLE_AUTH (id, roleid, authid, expandone, expandtwo, expandthree, roleauthdescribe)
values ('09FED994AE2143DFA865B61AAEB4B6DB', 'A82A57D91F0E4CFA81535FA9CA343E60', '6FBCA3E0D2FF4CE3924880B52E2094B5', null, null, null, null);
commit;
prompt 5 records loaded
prompt Loading SYS_USER...
insert into SYS_USER (id, username, password, roleid, usertype, userdescribe, expandone, expandtwo, expandthree)
values ('D9A54F6245B04EA1BDB88B43ADAD429F', 'ccc', 'f832b19b71fe500f66e45eefe1c4bc11', 'A82A57D91F0E4CFA81535FA9CA343E60', null, null, null, null, null);
insert into SYS_USER (id, username, password, roleid, usertype, userdescribe, expandone, expandtwo, expandthree)
values ('6EEBBEDC74C944B4B4FE78BCA47E2CEB', 'cc', 'f832b19b71fe500f66e45eefe1c4bc11', 'A82A57D91F0E4CFA81535FA9CA343E60', null, null, null, null, null);
insert into SYS_USER (id, username, password, roleid, usertype, userdescribe, expandone, expandtwo, expandthree)
values ('6DC43DF34382444B89F4883305BBE947', 'tom', '4ab5a02354bf263fd20b5ecb36fab431', 'A77AAEB9AF3D4C0AAD64BEF9D99908BD', null, null, null, null, null);
commit;
prompt 3 records loaded
prompt Enabling foreign key constraints for SYS_ROLE_AUTH...
alter table SYS_ROLE_AUTH enable constraint PID_AUTH_ID;
alter table SYS_ROLE_AUTH enable constraint PID_ROLE_ID;
prompt Enabling foreign key constraints for SYS_USER...
alter table SYS_USER enable constraint SYS_USER_FK1;
prompt Enabling triggers for CODE_RULES...
alter table CODE_RULES enable all triggers;
prompt Enabling triggers for CODE_RULES_CATEGORY...
alter table CODE_RULES_CATEGORY enable all triggers;
prompt Enabling triggers for SYS_AUTH...
alter table SYS_AUTH enable all triggers;
prompt Enabling triggers for SYS_ROLE...
alter table SYS_ROLE enable all triggers;
prompt Enabling triggers for SYS_ROLE_AUTH...
alter table SYS_ROLE_AUTH enable all triggers;
prompt Enabling triggers for SYS_USER...
alter table SYS_USER enable all triggers;
set feedback on
set define on
prompt Done.
