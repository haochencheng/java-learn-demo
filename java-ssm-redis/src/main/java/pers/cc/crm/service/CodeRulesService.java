package pers.cc.crm.service;

import java.util.List;

import pers.cc.crm.entity.CodeRules;

/**
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年1月5日
 * @Version:1.0.0
 */
public interface CodeRulesService {

    /**
     * @param codeRules
     * @return success return<code>1<code> else return<code>0<code>
     * @Description:插入编码规则
     */
    public void addCodeRules(CodeRules codeRules);

    /**
     * @return codeRulesName list
     * @Description:获取编码名字集合
     */
    public List<String> getAllCodeRulesName();

}