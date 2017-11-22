package pers.cc.crm.entity;

/**
 * Created by cc on 2016/12/8.
 */
public class RoleAuth {

    private String id;
    private String roleId;
    private String authId;
    private String ROLEAUTHDESCRIBE;
    private String expandOne;
    private String expandTwo;
    private String expandThree;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getROLEAUTHDESCRIBE() {
        return ROLEAUTHDESCRIBE;
    }

    public void setROLEAUTHDESCRIBE(String ROLEAUTHDESCRIBE) {
        this.ROLEAUTHDESCRIBE = ROLEAUTHDESCRIBE;
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
