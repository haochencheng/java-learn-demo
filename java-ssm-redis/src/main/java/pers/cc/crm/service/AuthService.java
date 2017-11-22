package pers.cc.crm.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

import pers.cc.crm.entity.Auth;

/**
 * Created by cc on 2016/12/12.
 */
public interface AuthService {

    public Auth getAuthWithAuthId(String authId);

    public JSONArray getAuthWithParentId(String parentId, List<String> authIds)
            throws Exception;

    public JSONArray getAuthListWithParentId(String parentId,
            List<String> authIds) throws Exception;

    public List<Auth> getAuthWithParentId(String parentId);

}
