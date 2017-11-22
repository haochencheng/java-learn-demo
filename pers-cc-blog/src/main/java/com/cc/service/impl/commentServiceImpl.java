package com.cc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cc.dao.CommentDao;
import com.cc.entity.Comment;
import com.cc.service.CommentService;

/**
 * 评论Service实现类
 * 
 * @author Administrator
 *
 */
@Service("commentService")
public class commentServiceImpl implements CommentService {

	@Resource
	private CommentDao commentDao;

	@Override
	public List<Comment> list(Map<String, Object> map) {
		return commentDao.list(map);
	}

	@Override
	public int add(Comment comment) {
		return commentDao.add(comment);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return commentDao.getTotal(map);
	}

	@Override
	public Integer update(Comment comment) {
		return commentDao.update(comment);
	}

	@Override
	public Integer delete(Integer id) {
		return commentDao.delete(id);
	}

}
