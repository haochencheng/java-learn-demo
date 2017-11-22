package pers.cc.crm.service;

import pers.cc.crm.entity.Role;

import java.util.List;

/**
 * Created by cc on 2016/11/27.
 */
public interface RoleService {

    public List<Role> getRole();

    public Role getRoleWithRoleId(String roleId);

    public Role getRoleWithUserName(String userName);
}
