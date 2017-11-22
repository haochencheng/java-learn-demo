package pers.cc.crm.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

import pers.cc.crm.entity.CodeRulesCategory;

public interface CodeRulesCategoryService {

    public CodeRulesCategory getCodeRulesCategoryWithCodeRulesCategoryId(
            String CodeRulesCategoryId);

    public JSONArray getChildrenNodesWithParentId(String parentId);

    public JSONArray getCodeRulesCategoryTreeWithParentId(String parentId)
            throws Exception;

    public List<CodeRulesCategory> getCodeRulesCategoryWithParentId(
            String parentId);
}
