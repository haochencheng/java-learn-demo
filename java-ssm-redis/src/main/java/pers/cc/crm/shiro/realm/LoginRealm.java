package pers.cc.crm.shiro.realm;

import java.util.List;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import pers.cc.crm.entity.Auth;
import pers.cc.crm.entity.Role;
import pers.cc.crm.entity.User;
import pers.cc.crm.service.AuthService;
import pers.cc.crm.service.RoleService;
import pers.cc.crm.service.UserService;

/**
 * Created by cc on 2016/11/20.
 */
public class LoginRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private AuthService authService;

    // 为当前的登录的用户授予角色和权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) throws AuthorizationException {
        if (principals == null) {
            throw new AuthorizationException("用户权限设置不可为空!");
        }
        String userName = (String) getAvailablePrincipal(principals);
        Role role = roleService.getRoleWithUserName(userName);
        if (role == null) {
            throw new AuthorizationException("用户名不存在!");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole(role.getRoleName());
        return info;
    }

    // 验证当前登录的用户
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userService.getUsreByUserName(userName);
        if (user != null) {
            List<Auth> authList = authService.getAuthWithParentId("0");
            if (authList.size() != 1) {
                try {
                    throw new LoginException("树顶点只能有一个,并且parentId要为0!");
                } catch (LoginException e) {
                    e.printStackTrace();
                }
            }
            String topTreeNodeAuthId = authList.get(0).getAuthId();
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("topTreeNodeAuthId", topTreeNodeAuthId);
            session.setAttribute("roleId", user.getRoleId());
            return new SimpleAuthenticationInfo(user.getUserName(),
                    user.getPassword(), getName());
        } else {
            return null;
        }
    }

}
