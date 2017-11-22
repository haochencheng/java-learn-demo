package pers.cc.crm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pers.cc.crm.entity.Role;
import pers.cc.crm.entity.User;
import pers.cc.crm.exception.LoginException;
import pers.cc.crm.service.RoleService;
import pers.cc.crm.service.UserService;
import pers.cc.crm.util.CryptographyUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by cc on 2016/11/20.
 */
@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(User user, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        request.setAttribute("user", user);
        if (user.getRoleId() == null) {
            throw new LoginException("请选择用户类型");
        }
        int roleId = user.getRoleId();
        Role role = roleService.getRoleWithRoleId(roleId);
        Subject currentUser = SecurityUtils.getSubject();
        //String username and password.  Acquire in
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), CryptographyUtil.md5(user.getPassword(), "cc"));
        if (user.getRememberMe() != null) {
            //”Remember Me” built-in, just do this:
            token.setRememberMe(true);
        }
        try {
            currentUser.login(token);  //登陆验证
            currentUser.checkRole(role.getRoleName());
        } catch (UnknownAccountException uae) {
            throw new LoginException("用户名或密码错误！");
        } catch (IncorrectCredentialsException ice) {
            throw new LoginException("用户名或密码错误 ！");
        } catch (LockedAccountException lae) {
            throw new LoginException("用户被锁定！禁止登陆！");
        } catch (ExcessiveAttemptsException eae) {
            throw new LoginException("登陆失败超过3次,用户将被锁定！");
        } catch (UnauthorizedException ue) {
            throw new LoginException("用户类型不正确");
        } catch (AuthorizationException ae) {
            throw new LoginException("ae.getMessage()");
        }
        mav.addObject(role);
        if (currentUser.hasRole("管理员")) {
            mav.setViewName("admin/mainTemp");
            return mav;
        }
        mav.setViewName("common/user");
        return mav;
    }

}
