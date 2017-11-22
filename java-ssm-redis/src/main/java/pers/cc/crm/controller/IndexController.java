package pers.cc.crm.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by cc on 2016/12/6.
 */
@Controller
public class IndexController {

    @RequestMapping("/login")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        // Cookie[] cookieList = request.getCookies();
        // for (Cookie cookie : cookieList) {
        // if ("rememberMe_cookie".equals(cookie.getName())
        // && !"deleteMe".equals(cookie.getValue())) {
        // mav.addObject("checked", "checked");
        // mav.setViewName("login");
        // return mav;
        // }
        // }
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/main")
    public ModelAndView home(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        ModelAndView mav = new ModelAndView();
        if (currentUser.isAuthenticated()) {
            if (currentUser.hasRole("管理员")) {
                mav.setViewName("admin/mainTemp");
                return mav;
            }
            mav.setViewName("user/user");
        }
        return mav;
    }

}
