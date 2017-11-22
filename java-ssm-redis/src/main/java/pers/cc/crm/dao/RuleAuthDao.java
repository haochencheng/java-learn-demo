package pers.cc.crm.dao;

import org.apache.ibatis.annotations.Select;
import pers.cc.crm.entity.RoleAuth;

import java.util.List;

/**
 * Created by cc on 2016/12/8.
 */
public interface RuleAuthDao {

    @Select("select * from sys_role_auth where roleId=#{roleId}")
    List<RoleAuth> getRoleAuthIds(String roleId);

}
