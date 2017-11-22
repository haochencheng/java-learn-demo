package pers.cc.activiti.flow;

import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiTestCase;
import org.activiti.engine.test.Deployment;

public class CompleteTest extends ActivitiTestCase {

    @Deployment
    public void testSimpleProcess() {
        runtimeService.startProcessInstanceByKey("helloWorld");

        Task task = taskService.createTaskQuery().singleResult();
        assertEquals("My Task", task.getName());

        taskService.complete(task.getId());
        assertEquals(0, runtimeService.createProcessInstanceQuery().count());
    }

}
