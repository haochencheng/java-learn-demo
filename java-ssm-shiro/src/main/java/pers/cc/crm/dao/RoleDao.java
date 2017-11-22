package pers.cc.crm.dao;

import org.apache.ibatis.annotations.Select;
import pers.cc.crm.entity.Role;

import java.util.List;

/**
 * Created by cc on 2016/11/27.
 */
public interface RoleDao {

    @Select("select * from t_role")
    List<Role> getRoleName();

    @Select("select * from t_role where roleId=#{roleId}")
    Role getRoleWithRoleId(Integer roleId);

    @Select("select * from t_role where roleId=(select roleId from t_user where userName=#{userName})")
    Role getRoleWithUserName(String userName);


}
