package pers.cc.crm.entity;

/**
 * Created by cc on 2016/12/7. 权限实体类
 */
public class Auth {

	private String authId;
	private String authName;
	private String authPath;
	private String parentId;
	private String state;
	private String iconClsId;
	private String authDescribe;
	private String expandOne;
	private String expandTwo;
	private String expandThree;

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthPath() {
		return authPath;
	}

	public void setAuthPath(String authPath) {
		this.authPath = authPath;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconClsId() {
		return iconClsId;
	}

	public void setIconClsId(String iconClsId) {
		this.iconClsId = iconClsId;
	}

	public String getAuthDescribe() {
		return authDescribe;
	}

	public void setAuthDescribe(String authDescribe) {
		this.authDescribe = authDescribe;
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
