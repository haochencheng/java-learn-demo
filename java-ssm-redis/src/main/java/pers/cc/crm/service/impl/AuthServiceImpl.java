package pers.cc.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pers.cc.crm.dao.AuthDao;
import pers.cc.crm.entity.Auth;
import pers.cc.crm.service.AuthService;

/**
 * Created by cc on 2016/12/12.
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthDao authDao;

    @Override
    public Auth getAuthWithAuthId(String authId) {
        return authDao.getAuthWithAuthId(authId);
    }

    @Override
    public JSONArray getAuthWithParentId(String parentId, List<String> authIds)
            throws Exception {
        System.out.println(authIds);
        JSONArray jsonArray = new JSONArray();
        List<Auth> authList = authDao.getAuthWithParentId(parentId, authIds);
        JSONObject jsonObject = new JSONObject();
        for (Auth a : authList) {
            System.out.println(a.getAuthId());
            if (!hasChildren(a.getAuthId(), authIds)) {
                a.setState("open");
            }
            jsonObject = (JSONObject) JSONObject.toJSON(a);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public JSONArray getAuthListWithParentId(String parentId,
            List<String> authIds) throws Exception {
        JSONArray jsonArray = this.getAuthWithParentId(parentId, authIds);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject.getString("state"));
            if (!"open".equals(jsonObject.getString("state"))) {
                jsonObject.put("children", getAuthListWithParentId(
                        jsonObject.getString("authId"), authIds));
            }
        }
        System.out.println(jsonArray);
        return jsonArray;
    }

    private boolean hasChildren(String parentId, List<String> authIds)
            throws Exception {
        List<Auth> authList = authDao.getAuthWithParentId(parentId, authIds);
        return authList.size() > 0;
    }

    @Override
    public List<Auth> getAuthWithParentId(String parentId) {
        return authDao.getTopNodeTreeAuthWithParentId(parentId);
    }

}
