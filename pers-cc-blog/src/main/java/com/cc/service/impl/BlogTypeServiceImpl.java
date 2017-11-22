package com.cc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cc.dao.BlogTypeDao;
import com.cc.entity.BlogType;
import com.cc.service.BlogTypeService;

/**
 * 博客类型Service实现类
 * 
 * @author Administrator
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

	@Resource
	private BlogTypeDao blogTypeDao;

	
	public List<BlogType> countList() {
		return blogTypeDao.countList();
	}

	
	public BlogType findById(Integer id) {
		return blogTypeDao.findById(id);
	}

	
	public List<BlogType> list(Map<String, Object> map) {
		return blogTypeDao.list(map);
	}

	
	public Long getTotal(Map<String, Object> map) {
		return blogTypeDao.getTotal(map);
	}

	
	public Integer add(BlogType blogType) {
		return blogTypeDao.add(blogType);
	}

	
	public Integer update(BlogType blogType) {
		return blogTypeDao.update(blogType);
	}

	
	public Integer delete(Integer id) {
		return blogTypeDao.delete(id);
	}

}
