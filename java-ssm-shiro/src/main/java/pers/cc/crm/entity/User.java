package pers.cc.crm.entity;

/**
 * Created by cc on 2016/11/20.
 */
public class User {

    private Integer id;
    private String userName;
    private String password;
    private Integer roleId;
    private String userType;
    private String userDescribe;
    private String expandOne;
    private String expandTwo;
    private String expandThree;

    private Integer rememberMe; //记住我

    public Integer getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Integer rememberMe) {
        this.rememberMe = rememberMe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserDescribe() {
        return userDescribe;
    }

    public void setUserDescribe(String userDescribe) {
        this.userDescribe = userDescribe;
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
