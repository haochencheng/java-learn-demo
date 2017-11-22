package pers.cc.activiti.flow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class MyJunitTest {

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    /**
     * 获取默认流程引擎实例,会自动读取activiti.cfg.xml文件
     */
    private ProcessEngine processEngine = ProcessEngines
            .getDefaultProcessEngine();

    /**
     * 部署流程
     * 
     * @Description:
     */
    @Test
    public void deploy() {
        Deployment deployment = processEngine.getRepositoryService()// 获取部署相关service
                .createDeployment() // 创建部署
                .addClasspathResource("diagrams/HelloWorld.bpmn") // 加载资源文件
                .addClasspathResource("diagrams/HelloWorld.png") // 加载资源文件
                .name("HelloWorld").deploy(); // 部署
        System.out.println("流程部署ID:" + deployment.getId());
        System.out.println("流程部署名称:" + deployment.getName());
    }

    @Test
    @org.activiti.engine.test.Deployment(resources = {
            "diagrams/HelloWorld.xml" })
    public void start() {
        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("helloworld");
        assertNotNull(processInstance);
        Task task = activitiRule.getTaskService().createTaskQuery()
                .singleResult();
        assertEquals("cctask", task.getName());
    }

    @Test
    @org.activiti.engine.test.Deployment(resources = {
            "diagrams/HelloWorld.xml" })
    public void searchTask() {
        List<Task> taskList = activitiRule.getTaskService() // 任务相关service
                .createTaskQuery() // 创建任务查询
                .taskAssignee("cc") // 制定某个人
                .list();
        System.out.println(taskList.size());
        taskList.forEach(task -> {
            System.out.println("任务ID:" + task.getId());
            System.out.println("任务名称:" + task.getName());
            System.out.println("任务创建时间:" + task.getCreateTime());
            System.out.println("任务实例ID:" + task.getProcessInstanceId());
        });
    }

    @Test
    @org.activiti.engine.test.Deployment(resources = {
            "diagrams/HelloWorld.xml" })
    public void complete() {
        TaskService taskService = activitiRule.getTaskService();
        taskService.complete("2504");
    }

}
