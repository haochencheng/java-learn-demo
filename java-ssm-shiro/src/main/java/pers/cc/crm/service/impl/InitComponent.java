package pers.cc.crm.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pers.cc.crm.entity.Role;
import pers.cc.crm.entity.User;
import pers.cc.crm.service.RoleService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by cc on 2016/11/27.
 */
@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware{

    private static ApplicationContext applicationContext;

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application=sce.getServletContext();
        RoleService roleService= (RoleService) applicationContext.getBean("roleService");
        List<Role> roleList=roleService.getRole();
        application.setAttribute("roleList",roleList);

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InitComponent.applicationContext =applicationContext;
    }
}
