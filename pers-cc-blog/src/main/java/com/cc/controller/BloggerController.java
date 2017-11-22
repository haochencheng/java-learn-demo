package com.cc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cc.entity.Blogger;
import com.cc.service.BloggerService;
import com.cc.util.CryptographyUtil;

/**
 * 博主Controller层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

	@Resource
	private BloggerService bloggerService;

	@RequestMapping("/login")
	public String login(Blogger blogger, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUserName(),
				CryptographyUtil.md5(blogger.getPassword(), "cc"));
		try {
			subject.login(token); // 登录验证
			return "redirect:/cc/main.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名或密码错误");
			return "/cc/login";
		}
	}

	/**
	 * 关于博主
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe() throws Exception {
		ModelAndView mAndView = new ModelAndView();
		mAndView.addObject("pageTitle", "关于博主_cc个人博客系统");
		mAndView.addObject("mainPage", "foreground/blogger/info.jsp");
		mAndView.setViewName("mainTemp");
		return mAndView;
	}

}
