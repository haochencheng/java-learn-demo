package pers.cc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {

    /**  
     *   
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("===========进入登陆servlet========");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("cookie名称" + cookie.getName());
            System.out.println("cookie过期时间" + cookie.getMaxAge());
            System.out.println("cookie路径" + cookie.getPath());
            System.out.println("cookie值" + cookie.getValue());
        }
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        String remberMe = req.getParameter("remberMe");

        Cookie nameCookie = new Cookie("userNameCookie", userName);
        Cookie pwdCookie = new Cookie("pwdCookie", pwd);
        Cookie rembermeCookie = new Cookie("remberMeCookie", remberMe);
        Cookie JSESSIONID = new Cookie("JSESSIONID", "");

        nameCookie.setMaxAge(60 * 2);
        pwdCookie.setMaxAge(60 * 2);
        rembermeCookie.setMaxAge(60 * 2);
        JSESSIONID.setMaxAge(0);

        System.out.println("登陆用户名:" + userName);
        System.out.println("登陆密码:" + pwd);
        System.out.println("是否记住我:" + remberMe);

        resp.addCookie(nameCookie);
        resp.addCookie(pwdCookie);
        resp.addCookie(rembermeCookie);
        resp.addCookie(JSESSIONID);

    }

}
