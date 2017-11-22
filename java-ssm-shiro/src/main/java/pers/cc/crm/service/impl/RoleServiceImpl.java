package pers.cc.crm.service.impl;

import org.springframework.stereotype.Service;
import pers.cc.crm.dao.RoleDao;
import pers.cc.crm.entity.Role;
import pers.cc.crm.service.RoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cc on 2016/11/27.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleDao roleDao;


    public List<Role> getRole() {
        return roleDao.getRoleName();
    }

    @Override
    public Role getRoleWithRoleId(Integer roleId) {
        Role role=roleDao.getRoleWithRoleId(roleId);
        return role;
    }

    @Override
    public Role getRoleWithUserName(String userName) {
        Role role=roleDao.getRoleWithUserName(userName);
        return role;
    }
}
