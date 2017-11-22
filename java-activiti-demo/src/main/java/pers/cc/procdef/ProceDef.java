package pers.cc.procdef;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class ProceDef {

    private ProcessEngine processEngine = ProcessEngines
            .getDefaultProcessEngine();

    @Test
    public void deployWithClassPath() {
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("diagrams/HelloWorld.bpmn")
                .addClasspathResource("diagrams/HelloWorld.png")
                .name("HelloWorld流程").deploy();

        System.out.println("流程部署ID:" + deployment.getId());
        System.out.println("流程部署名称:" + deployment.getName());
    }

    @Test
    public void deployWithZip() {
        InputStream inputStream = this.getClass().getClassLoader()
                .getResourceAsStream("diagrams/HelloWorld.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment().addZipInputStream(zipInputStream).deploy();

        System.out.println("流程部署ID:" + deployment.getId());
        System.out.println("流程部署名称:" + deployment.getName());

    }

}
