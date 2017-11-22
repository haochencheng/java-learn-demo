package pers.cc.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import pers.cc.crm.entity.CodeRules;

public interface CodeRulesDao {

    //
    @Insert("insert into code_rules(rulesId,rulesName,segmentQuantity,rulesSeparator,minLength,maxLength,rulesStatus,validateSwitch,codeSource,formatSwitch,description,version,creator,CATEGORYCODE)values(#{rulesId},#{rulesName},#{segmentQuantity},#{rulesSeparator},#{minLength},#{maxLength},#{rulesStatus},#{validateSwitch},#{codeSource},#{formatSwitch},#{description},#{version},#{creator},#{codeRulesCategoryId})")
    public void add(CodeRules codeRules);

    @Select("select rulesName from code_rules")
    public List<String> getAllCodeNames();

}
