package pers.cc.activiti.flow;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class StudentLeave {

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
                .addClasspathResource(
                        "diagrams/StudentLeaveProcess/StudentLeave.bpmn") // 加载资源文件
                .addClasspathResource(
                        "diagrams/StudentLeaveProcess/StudentLeave.png") // 加载资源文件
                .name("StudentLeave").deploy(); // 部署
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
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey("studentLeave");
        System.out.println("流程实例ID" + processInstance.getId());
        System.out.println("流程名称" + processInstance.getName());
        System.out.println("流程定义ID" + processInstance.getProcessDefinitionId());
    }

    /**
     * 查询任务
     * 
     * @Description:
     */
    @Test
    public void findTask() {
        List<Task> taskList = processEngine.getTaskService().createTaskQuery()
                .taskAssignee("王五").list();
        taskList.forEach((task) -> {
            System.out.println("任务ID:" + task.getId());
            System.out.println("任务名称:" + task.getName());
            System.out.println("任务创建时间:" + task.getCreateTime());
            System.out.println("任务实例ID:" + task.getProcessInstanceId());
        });
    }

    @Test
    public void complete() {
        processEngine.getTaskService().complete("57502");
    }

}
