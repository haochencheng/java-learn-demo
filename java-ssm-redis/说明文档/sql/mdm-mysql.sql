/*
Navicat MySQL Data Transfer

Source Server         : cc
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : mdm

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-07-14 22:22:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for code_rules
-- ----------------------------
DROP TABLE IF EXISTS `code_rules`;
CREATE TABLE `code_rules` (
  `extendfive` varchar(50) DEFAULT NULL,
  `extendfour` varchar(50) DEFAULT NULL,
  `extendthree` varchar(50) DEFAULT NULL,
  `extendtwo` varchar(50) DEFAULT NULL,
  `extendone` varchar(50) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `rulesalias` varchar(50) DEFAULT NULL,
  `statusswitch` varchar(50) DEFAULT NULL,
  `rulesstatus` varchar(50) DEFAULT NULL,
  `sourceswitch` varchar(50) DEFAULT NULL,
  `codesource` varchar(50) DEFAULT NULL,
  `validateswitch` varchar(50) DEFAULT NULL,
  `rulesseparator` varchar(50) DEFAULT NULL,
  `rulesname` varchar(50) DEFAULT NULL,
  `categorycode` varchar(50) DEFAULT NULL,
  `rulesid` varchar(50) NOT NULL,
  `updatetime` date DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `segmentquantity` int(11) DEFAULT NULL,
  `maxlength` int(11) DEFAULT NULL,
  `minlength` int(11) DEFAULT NULL,
  `formatswitch` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rulesid`),
  UNIQUE KEY `SYS_C0043451` (`rulesid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_rules
-- ----------------------------

-- ----------------------------
-- Table structure for code_rules_category
-- ----------------------------
DROP TABLE IF EXISTS `code_rules_category`;
CREATE TABLE `code_rules_category` (
  `extendthree` varchar(50) DEFAULT NULL,
  `extendtwo` varchar(50) DEFAULT NULL,
  `extendone` varchar(50) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `categorycode` varchar(50) DEFAULT NULL,
  `categoryname` varchar(50) DEFAULT NULL,
  `parentid` varchar(50) DEFAULT NULL,
  `coderulescategoryid` varchar(50) NOT NULL,
  `updatetime` date DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `sortindex` int(11) DEFAULT NULL,
  PRIMARY KEY (`coderulescategoryid`),
  UNIQUE KEY `SYS_C0043528` (`coderulescategoryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of code_rules_category
-- ----------------------------
INSERT INTO `code_rules_category` VALUES (null, null, null, '1', 'admin', null, '啊', 'Enable', null, '测试数据大类', '0', '10C492D6F5BC4E10BE68B38853718234', null, null, null);
INSERT INTO `code_rules_category` VALUES (null, null, null, null, null, null, '小类', 'Enable', null, '测试数据小类', 'B67EBAA8B8D94D638CD6B686DC754ECF', 'B96A4F72F0F123djhijB02693441880', null, null, null);

-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth` (
  `authid` varchar(50) DEFAULT NULL,
  `authname` varchar(50) NOT NULL,
  `authpath` varchar(100) DEFAULT NULL,
  `parentid` varchar(50) NOT NULL,
  `state` varchar(20) NOT NULL,
  `iconclsid` varchar(50) NOT NULL,
  `authdescribe` varchar(200) DEFAULT NULL,
  `expandone` varchar(50) DEFAULT NULL,
  `expandtwo` varchar(50) DEFAULT NULL,
  `expandthree` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth` VALUES ('2555665049C041FC86B78FCC73BC16D2', '不知道什么系统', null, '0', 'open', '1', null, null, null, null);
INSERT INTO `sys_auth` VALUES ('F83DE15A8DB24318B8933D9181C1A887', '编码规则管理', null, '2555665049C041FC86B78FCC73BC16D2', 'close', '2', null, null, null, null);
INSERT INTO `sys_auth` VALUES ('4A8F41500882458E987777F263ACB0FB', '新建编码规则', '/rule/add.html', 'F83DE15A8DB24318B8933D9181C1A887', 'open', '3', null, null, null, null);
INSERT INTO `sys_auth` VALUES ('4407F2D0FDFD4B72924119CB8620E06C', '编码规则维护', '/rule/edit.html', 'F83DE15A8DB24318B8933D9181C1A887', 'close', '4', null, null, null, null);
INSERT INTO `sys_auth` VALUES ('6770C899C5214EB99704772A9FD99C92', '测试一', null, '1', 'open', '1', null, null, null, null);
INSERT INTO `sys_auth` VALUES ('6FBCA3E0D2FF4CE3924880B52E2094B5', '测试二', null, '1', 'open', '2', null, null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleid` varchar(50) DEFAULT NULL,
  `rolename` varchar(50) NOT NULL,
  `roledescribe` varchar(100) DEFAULT NULL,
  `expandone` varchar(50) DEFAULT NULL,
  `expandtwo` varchar(50) DEFAULT NULL,
  `expandthree` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('A82A57D91F0E4CFA81535FA9CA343E60', '管理员', null, null, null, null);
INSERT INTO `sys_role` VALUES ('A77AAEB9AF3D4C0AAD64BEF9D99908BD', '员工', null, null, null, null);

-- ----------------------------
-- Table structure for sys_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth` (
  `id` varchar(50) NOT NULL,
  `roleid` varchar(50) NOT NULL,
  `authid` varchar(50) NOT NULL,
  `expandone` varchar(50) DEFAULT NULL,
  `expandtwo` varchar(50) DEFAULT NULL,
  `expandthree` varchar(50) DEFAULT NULL,
  `roleauthdescribe` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES ('09FED994AE2143DFA865B61AAEB4B6DB', 'A82A57D91F0E4CFA81535FA9CA343E60', '6FBCA3E0D2FF4CE3924880B52E2094B5', null, null, null, null);
INSERT INTO `sys_role_auth` VALUES ('2AD07F29E20C4FA0B1103A11A8BE9387', 'A82A57D91F0E4CFA81535FA9CA343E60', 'F83DE15A8DB24318B8933D9181C1A887', null, null, null, null);
INSERT INTO `sys_role_auth` VALUES ('7FF00D1D2E9C420AAB128F18DD54675B', 'A82A57D91F0E4CFA81535FA9CA343E60', '6770C899C5214EB99704772A9FD99C92', null, null, null, null);
INSERT INTO `sys_role_auth` VALUES ('92EB65A4B67C46AAB7BBFCA2F8057E49', 'A82A57D91F0E4CFA81535FA9CA343E60', '4407F2D0FDFD4B72924119CB8620E06C', null, null, null, null);
INSERT INTO `sys_role_auth` VALUES ('BB7C3D51EB6547C3872D7FEEF616E464', 'A82A57D91F0E4CFA81535FA9CA343E60', '4A8F41500882458E987777F263ACB0FB', null, null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `roleid` varchar(50) NOT NULL,
  `usertype` varchar(20) DEFAULT NULL,
  `userdescribe` varchar(20) DEFAULT NULL,
  `expandone` varchar(20) DEFAULT NULL,
  `expandtwo` varchar(20) DEFAULT NULL,
  `expandthree` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('D9A54F6245B04EA1BDB88B43ADAD429F', 'ccc', 'f832b19b71fe500f66e45eefe1c4bc11', 'A82A57D91F0E4CFA81535FA9CA343E60', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('6EEBBEDC74C944B4B4FE78BCA47E2CEB', 'cc', 'f832b19b71fe500f66e45eefe1c4bc11', 'A82A57D91F0E4CFA81535FA9CA343E60', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('6DC43DF34382444B89F4883305BBE947', 'tom', 'f832b19b71fe500f66e45eefe1c4bc11', 'A77AAEB9AF3D4C0AAD64BEF9D99908BD', null, null, null, null, null);
SET FOREIGN_KEY_CHECKS=1;
