package pers.cc.crm.service.impl;

import org.springframework.stereotype.Service;
import pers.cc.crm.dao.UserDao;
import pers.cc.crm.entity.User;
import pers.cc.crm.service.UserService;

import javax.annotation.Resource;

/**
 * Created by cc on 2016/11/20.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public User getUsreByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }



}
