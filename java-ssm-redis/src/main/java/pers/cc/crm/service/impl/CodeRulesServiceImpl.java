package pers.cc.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pers.cc.crm.dao.CodeRulesDao;
import pers.cc.crm.entity.CodeRules;
import pers.cc.crm.service.CodeRulesService;

@Service("codeRulesService")
public class CodeRulesServiceImpl implements CodeRulesService {

    @Resource
    CodeRulesDao codeRulesDao;

    @Override
    public void addCodeRules(CodeRules codeRules) {
        codeRulesDao.add(codeRules);
    }

    @Override
    public List<String> getAllCodeRulesName() {
        return codeRulesDao.getAllCodeNames();
    }

}
