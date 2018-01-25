package pers.cc;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pers.cc.entity.SerialNo;
import pers.cc.repository.SerialNoRepo;
import pers.cc.service.IncreaseService;
import pers.cc.util.IdUtil;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class HibernateBootstrap {


    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("classpath:spring.xml");
        IncreaseService increaseService= (IncreaseService) applicationContext.getBean("increaseService");
        CountDownLatch countDownLatch=new CountDownLatch(100);
        for (int i = 0; i < 1; i++) {
            increaseService.increase(1);
            countDownLatch.countDown();
        }
        countDownLatch.await();
        System.out.println(increaseService.findById(1));
    }



}
