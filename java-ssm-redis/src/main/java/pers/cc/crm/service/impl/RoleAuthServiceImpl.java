package pers.cc.crm.service.impl;

import org.springframework.stereotype.Service;
import pers.cc.crm.dao.RuleAuthDao;
import pers.cc.crm.entity.RoleAuth;
import pers.cc.crm.service.RoleAuthService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cc on 2016/12/8.
 */
@Service("roleAuthService")
public class RoleAuthServiceImpl implements RoleAuthService{

    @Resource
    RuleAuthDao ruleAuthDao;

    @Override
    public List<RoleAuth> getRoleAuthIds(String roleId) {
        return ruleAuthDao.getRoleAuthIds(roleId);
    }
}
