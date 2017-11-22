package pers.cc.crm.dao;

import org.apache.ibatis.annotations.Select;
import pers.cc.crm.entity.Role;

import java.util.List;

/**
 * Created by cc on 2016/11/27.
 */
public interface RoleDao {

	@Select("select * from sys_role")
	List<Role> getRoleName();

	@Select("select * from sys_role where roleId=#{roleId}")
	Role getRoleWithRoleId(String roleId);

	@Select("select * from sys_role where roleId=(select roleId from sys_user where userName=#{userName})")
	Role getRoleWithUserName(String userName);

}
