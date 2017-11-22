package pers.cc.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

public class BuilderEngineDemo {

    public static void main(String[] args) throws Exception {
        // ProcessEngineConfiguration cfg = ProcessEngineConfiguration
        // .createStandaloneProcessEngineConfiguration();
        // cfg.setDatabaseSchemaUpdate(
        // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
        // .setJdbcDriver("com.mysql.jdbc.Driver").setJdbcUsername("root")
        // .setJdbcPassword("niubidecc.")
        // .setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti");
        // ProcessEngine processEngine = cfg.buildProcessEngine();
        // String pName = processEngine.getName();
        // String ver = ProcessEngine.VERSION;
        // System.out.println(
        // "ProcessEngine [" + pName + "] Version: [" + ver + "]");
        // 引擎配置
        ProcessEngineConfiguration cgf = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource(
                        "activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine = cgf.buildProcessEngine();
        String pName = processEngine.getName();
        String ver = ProcessEngine.VERSION;
        System.out.println(
                "ProcessEngine [" + pName + "] Version: [" + ver + "]");
    }

}
