package pers.cc.crm.entity;

/**
 * Created by cc on 2016/11/20.
 */
public class Role {

    private Integer roleId;
    private String roleName;
    private String authId;
    private String roleDescribe;
    private String expandOne;
    private String expandTwo;
    private String expandThree;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
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
