package com.cc.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import com.cc.entity.Blog;
import com.cc.entity.BlogType;
import com.cc.entity.Blogger;
import com.cc.entity.Link;
import com.cc.service.BlogService;
import com.cc.service.BlogTypeService;
import com.cc.service.BloggerService;
import com.cc.service.LinkService;

@Controller
public class InitComponent implements ServletContextListener, ApplicationContextAware {

	private static ApplicationContext applicationContext;

	/*
	 * 公共页面放入application缓存 (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent ServletContextEvent) {
		ServletContext application = ServletContextEvent.getServletContext();
		BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
		Blogger blogger = bloggerService.find();
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);

		LinkService linkService = (LinkService) applicationContext.getBean("linkService");
		List<Link> linkList = linkService.list(null); // 查询所有友情链接
		application.setAttribute("linkList", linkList);

		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
		List<BlogType> blogTypeCountList = blogTypeService.countList();
		application.setAttribute("blogTypeCountList", blogTypeCountList); // 查询博客类别及博客数量

		BlogService blogService = (BlogService) applicationContext.getBean("blogService");
		List<Blog> blogCountList = blogService.countList();
		application.setAttribute("blogCountList", blogCountList); // 根据日期分组查询博客
	}

	public void contextDestroyed(ServletContextEvent ServletContextEvent) {
		// TODO Auto-generated method stub

	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		InitComponent.applicationContext = applicationContext;
	}

}
