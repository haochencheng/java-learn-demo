package com.cc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cc.dao.BlogDao;
import com.cc.entity.Blog;
import com.cc.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Resource
	private BlogDao blogDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cc.service.BlogService#countList()
	 */
	@Override
	public List<Blog> countList() {
		return blogDao.countList();
	}

	@Override
	public List<Blog> list(Map<String, Object> map) {
		return blogDao.list(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return blogDao.getTotal(map);
	}

	@Override
	public Blog findById(Integer id) {
		return blogDao.findById(id);
	}

	@Override
	public Integer update(Blog blog) {
		return blogDao.update(blog);
	}

	@Override
	public Blog getLastBlog(Integer id) {
		return blogDao.getLastBlog(id);
	}

	@Override
	public Blog getNextBlog(Integer id) {
		return blogDao.getNextBlog(id);
	}

	@Override
	public int add(Blog blog) {
		return blogDao.add(blog);
	}

	@Override
	public Integer delete(Integer id) {
		return blogDao.delete(id);
	}


	@Override
	public Integer getBlogByTypeId(Integer blogTypeId) {
		return blogDao.getBlogByTypeId(blogTypeId);
	}

}
