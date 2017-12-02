package pers.spring.boot.demo.test.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

/**
 * Created by cc on 2017/10/25
 */
/*
 * @Component
 * 
 * @EnableScheduling
 */
public class UpdateCronTask implements SchedulingConfigurer {

	public static String cron = "0/2 * * * * ?";
	int i = 0;

	private AtomicInteger atomicInteger;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(() -> {
			i++;
			// 任务逻辑
			System.out.println("第" + (i) + "次开始执行操作... " + "时间：【"
					+ new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(new Date()) + "】");
		}, triggerContext -> {

			System.out.println(triggerContext.lastActualExecutionTime());

			// 任务触发，可修改任务的执行周期
			CronTrigger trigger = new CronTrigger(cron);
			Date nextExec = trigger.nextExecutionTime(triggerContext);
			return nextExec;
		});
	}
}
