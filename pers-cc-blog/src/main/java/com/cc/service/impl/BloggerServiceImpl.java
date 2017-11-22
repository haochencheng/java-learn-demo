package com.cc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cc.dao.BloggerDao;
import com.cc.entity.Blogger;
import com.cc.service.BloggerService;

/**
 * 博主实现类
 * 
 * @author Administrator
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {

	@Resource
	private BloggerDao bloggerDao;

	
	public Blogger getByUserName(String userName) {
		return bloggerDao.getByUserName(userName);
	}

	
	public Blogger find() {
		return bloggerDao.find();
	}

	
	public Integer update(Blogger blogger) {
		return bloggerDao.update(blogger);
	}

}
