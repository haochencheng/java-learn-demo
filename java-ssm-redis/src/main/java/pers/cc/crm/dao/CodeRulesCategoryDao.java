package pers.cc.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import pers.cc.crm.entity.CodeRulesCategory;

public interface CodeRulesCategoryDao {

	@Select("select * from code_rules_category where codeRulesCategoryId=#{codeRulesCategoryId}")
	CodeRulesCategory getCodeRulesCategoryWithCodeRulesCategoryId(String codeRulesCategoryId);

	@Select("select * from code_rules_category where parentId=#{parentId}")
	List<CodeRulesCategory> getCodeRulesCategoryWithParentId(String parentId);

}
