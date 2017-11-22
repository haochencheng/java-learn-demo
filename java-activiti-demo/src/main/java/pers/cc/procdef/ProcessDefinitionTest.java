package pers.cc.procdef;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ProcessDefinitionTest {

    /**
     * 获取默认流程引擎实例,会自动读取activiti.cfg.xml文件
     */
    private ProcessEngine processEngine = ProcessEngines
            .getDefaultProcessEngine();

    /**
     * 查询流程定义 返回流程定义集合 对应表act_re_procdef
     * 
     * @Description:
     */
    @Test
    public void list() {
        String processDefinitionKey = "helloworld";
        List<ProcessDefinition> pdList = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey).list();
        pdList.forEach((n) -> {
            System.out.println(n.getId());
            System.out.println(n.getName());
            System.out.println(n.getKey());
            System.out.println(n.getVersion());
        });
    }

    /**
     * 通过ID查询某个定义流程
     * 
     * @Description:
     */
    @Test
    public void getById() {
        String processDefinitionId = "helloworld:3:20004";
        ProcessDefinition pd = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        System.out.println(pd.getId());
        System.out.println(pd.getName());
        System.out.println(pd.getKey());
        System.out.println(pd.getVersion());
    }

    /**
     * 根据流程部署ID和资源文件名称查询流程图片
     * 
     * @Description:
     */
    @Test
    public void getImageById() throws Exception {
        InputStream inputStream = processEngine.getRepositoryService()
                .getResourceAsStream("22501", "HelloWorld/HelloWorld.png");
        FileUtils.copyInputStreamToFile(inputStream,
                new File("C:/Users/Administrator/Desktop/HelloWorld.png"));

    }

    /**
     * 根据流程部署ID和资源文件名称查询流程图片
     * 
     * @Description:
     */
    @Test
    public void getLastVersion() throws Exception {
        List<ProcessDefinition> processDefinitionList = processEngine
                .getRepositoryService().createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc().list();
        Map<String, ProcessDefinition> processDefinitionListMap = new HashMap<>();
        processDefinitionList.forEach((pd) -> {
            processDefinitionListMap.put(pd.getKey(), pd);
        });
        List<ProcessDefinition> pdList = new LinkedList<ProcessDefinition>();
        pdList.addAll(processDefinitionListMap.values());
        System.out.println(pdList.toString());
    }

    @Test
    public void deletByKey() {
        String processDefinitionKey = "helloworld2";
        List<ProcessDefinition> processDefinitionList = processEngine
                .getRepositoryService().createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey).list();
        processDefinitionList.forEach((pd) -> {
            processEngine.getRepositoryService()
                    .deleteDeployment(pd.getDeploymentId(), true);
        });
    }
}
