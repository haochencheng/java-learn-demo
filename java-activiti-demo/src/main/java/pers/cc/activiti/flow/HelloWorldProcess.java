package pers.cc.activiti.flow;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HelloWorldProcess {

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
                .addClasspathResource("diagrams/helloworld2/HelloWorld2.bpmn") // 加载资源文件
                .addClasspathResource("diagrams/helloworld2/HelloWorld2.png") // 加载资源文件
                .name("HelloWorld").deploy(); // 部署
        System.out.println("流程部署ID:" + deployment.getId());
        System.out.println("流程部署名称:" + deployment.getName());
    }

    /**
     * 启动流程
     * 
     * @Description:
     */
    @Test
    public void start() {
        ProcessInstance pi = processEngine.getRuntimeService()
                .startProcessInstanceByKey("helloworld");
        System.out.println("流程实例ID:" + pi.getId());
        System.out.println("流程定义ID:" + pi.getProcessDefinitionId());
    }

    /**
     * 查看任务
     * 
     * @Description:
     */
    @Test
    public void findTask() {
        List<Task> taskList = processEngine.getTaskService() // 任务相关service
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

    /** 查询流程状态（判断流程正在执行，还是结束） */
    @Test
    public void isProcessEnd() {
        String processInstanceId = "7504";
        ProcessInstance pi = processEngine
                .getRuntimeService()/** 表示正在执行的流程实例和执行对象 */
                .createProcessInstanceQuery()/** 创建流程实例查询 */
                .processInstanceId(processInstanceId)/** 使用流程实例ID查询 */
                .singleResult();
        if (pi == null) {
            System.out.println("流程已经结束");
        } else {
            System.out.println("流程没有结束");
        }
    }

    /**
     * 完成任务
     * 
     * @Description:
     */
    @Test
    public void completeTask() throws Exception {
        TaskService taskService = processEngine.getTaskService();
        taskService.complete("15004");
    }

}
