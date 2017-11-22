package pers.cc.crm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;

import pers.cc.crm.entity.RoleAuth;
import pers.cc.crm.entity.User;
import pers.cc.crm.exception.LoginException;
import pers.cc.crm.service.AuthService;
import pers.cc.crm.service.RoleAuthService;
import pers.cc.crm.service.RoleService;
import pers.cc.crm.service.UserService;
import pers.cc.crm.util.CryptographyUtil;
import pers.cc.crm.util.StringUtil;

/**
 * Created by cc on 2016/11/20.
 */
@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private RoleAuthService roleAuthService;

    @Resource
    private AuthService authService;

    /**
     * @param user
     * @param request
     * @param response
     * @return 登陆成功界面
     * @throws Exception
     * @Description:
     */
    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView login(User user, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        request.setAttribute("user", user);
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()
                && currentUser.getPrincipal().equals(user.getUserName())) {
            if (currentUser.hasRole("管理员")) {
                mav.setViewName("admin/mainTemp");
                return mav;
            }
            mav.setViewName("user/user");
            return mav;
        }
        // String username and password. Acquire in
        UsernamePasswordToken token = new UsernamePasswordToken(
                user.getUserName(),
                CryptographyUtil.md5(user.getPassword(), "cc"));
        if (user.getRememberMe() != null) {
            // ”Remember Me” built-in, just do this:
            token.setRememberMe(true);
        }
        try {
            currentUser.login(token); // 登陆验证
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
            throw new LoginException("没有权限");
        } catch (AuthenticationException ace) {
            ace.printStackTrace();
            throw new LoginException("用户名或密码错误!");
        }
        // 动态生成权限树
        List<String> authIdList = new ArrayList<>();
        Session session = SecurityUtils.getSubject().getSession();
        String rolesId = (String) session.getAttribute("roleId");
        String topTreeNodeAuthId = (String) session
                .getAttribute("topTreeNodeAuthId");
        List<RoleAuth> roleAuthList = roleAuthService.getRoleAuthIds(rolesId);
        if (roleAuthList.size() == 0) {
            mav.setViewName("user/user");
            return mav;
        }
        for (RoleAuth r : roleAuthList) {
            String authId = r.getAuthId();
            authIdList.add(authId);
        }
        JSONArray menuTree = new JSONArray();
        if (StringUtil.isNotEmpty(topTreeNodeAuthId)) {
            menuTree = authService.getAuthListWithParentId(topTreeNodeAuthId,
                    authIdList);
        }
        // 将menuTree放入全局变量
        request.getSession().getServletContext().setAttribute("menuTree",
                menuTree);
        if (currentUser.hasRole("管理员")) {
            mav.setViewName("admin/mainTemp");
            return mav;
        }
        mav.setViewName("user/user");
        return mav;
    }

}
