package pers.cc.crm.entity;

/**
 * Created by cc on 2016/11/20.
 */
public class Role {

	private String roleId;
	private String roleName;
	private String roleDescribe;
	private String expandOne;
	private String expandTwo;
	private String expandThree;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescribe() {
		return roleDescribe;
	}

	public void setRoleDescribe(String roleDescribe) {
		this.roleDescribe = roleDescribe;
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
