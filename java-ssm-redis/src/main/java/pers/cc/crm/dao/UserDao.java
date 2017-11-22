package pers.cc.crm.dao;

import org.apache.ibatis.annotations.Select;

import pers.cc.crm.entity.User;

/**
 * Created by cc on 2016/11/20.
 */
public interface UserDao {

	@Select("select * from sys_user where userName=#{userName}")
	User getUserByUserName(String userName);

}
