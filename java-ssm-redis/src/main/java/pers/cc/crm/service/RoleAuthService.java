package pers.cc.crm.service;

import pers.cc.crm.entity.RoleAuth;

import java.util.List;

/**
 * Created by cc on 2016/12/8.
 */
public interface RoleAuthService {

    public List<RoleAuth> getRoleAuthIds(String roleId);

}
