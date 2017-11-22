package pers.cc.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pers.cc.crm.dao.CodeRulesCategoryDao;
import pers.cc.crm.entity.CodeRulesCategory;
import pers.cc.crm.service.CodeRulesCategoryService;

@Service("codeRulesCategoryService")
public class CodeRulesCategoryServiceImpl implements CodeRulesCategoryService {

    @Resource
    CodeRulesCategoryDao codeRulesCategoryDao;

    @Override
    public CodeRulesCategory getCodeRulesCategoryWithCodeRulesCategoryId(
            String CodeRulesCategoryId) {
        // TODO Auto-generated method stub
        return codeRulesCategoryDao.getCodeRulesCategoryWithCodeRulesCategoryId(
                CodeRulesCategoryId);
    }

    @Override
    public JSONArray getChildrenNodesWithParentId(String parentId) {
        JSONArray childrenNodesJsonArry = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        List<CodeRulesCategory> codeRulesCategorieList = codeRulesCategoryDao
                .getCodeRulesCategoryWithParentId(parentId);
        for (CodeRulesCategory c : codeRulesCategorieList) {
            jsonObject = (JSONObject) JSONObject.toJSON(c);
            childrenNodesJsonArry.add(jsonObject);
        }
        return childrenNodesJsonArry;
    }

    @Override
    public JSONArray getCodeRulesCategoryTreeWithParentId(String parentId)
            throws Exception {
        JSONArray jsonArray = this.getChildrenNodesWithParentId(parentId);
        String codeRulesCategoryId = "";
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            codeRulesCategoryId = jsonObject.getString("codeRulesCategoryId");
            if (hasChildren(codeRulesCategoryId)) {
                jsonObject.put("children", getCodeRulesCategoryTreeWithParentId(
                        codeRulesCategoryId));
            }
        }
        System.out.println(jsonArray);
        return jsonArray;
    }

    @Override
    public List<CodeRulesCategory> getCodeRulesCategoryWithParentId(
            String parentId) {
        // TODO Auto-generated method stub
        return codeRulesCategoryDao.getCodeRulesCategoryWithParentId(parentId);
    }

    private boolean hasChildren(String parentId) throws Exception {
        List<CodeRulesCategory> codeRulesCategoryList = codeRulesCategoryDao
                .getCodeRulesCategoryWithParentId(parentId);
        return codeRulesCategoryList.size() > 0;
    }

}
