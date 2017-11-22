package pers.cc.crm.entity;

import java.util.Date;

/**
 * 编码规则分类实体
 * 
 * @author cc
 *
 */
/**
 * @author Administrator
 *
 */
public class CodeRulesCategory {

	private String codeRulesCategoryId;
	private String categoryName;
	private String categoryCode;
	private String parentId;
	private String status;
	private String sortIndex;
	private String version;
	private String creator;
	private Date creatTIme;
	private Date updateTime;
	private String remark;
	private String description;
	private String expandOne;
	private String expandTwo;
	private String expandThree;

	public String getCodeRulesCategoryId() {
		return codeRulesCategoryId;
	}

	public void setCodeRulesCategoryId(String codeRulesCategoryId) {
		this.codeRulesCategoryId = codeRulesCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatTIme() {
		return creatTIme;
	}

	public void setCreatTIme(Date creatTIme) {
		this.creatTIme = creatTIme;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpandOne() {
		return expandOne;
	}

	public void setExpandOne(String expandOne) {
		this.expandOne = expandOne;
	}

	public String getExpandTwo() {
		return expandTwo;
	}

	public void setExpandTwo(String expandTwo) {
		this.expandTwo = expandTwo;
	}

	public String getExpandThree() {
		return expandThree;
	}

	public void setExpandThree(String expandThree) {
		this.expandThree = expandThree;
	}

}
