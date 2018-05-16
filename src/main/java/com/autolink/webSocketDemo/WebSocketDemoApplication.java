package com.autolink.webSocketDemo;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class WebSocketDemoApplication {

    public static ApplicationContext SPRING_CONTEXT;

    public static void showBeans() {
        String[] beanNames = SPRING_CONTEXT.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            log.info(beanName + "==========" + SPRING_CONTEXT.getBean(beanName).getClass());
        }
    }

    public static void main(String[] args) {
        SPRING_CONTEXT = SpringApplication.run(WebSocketDemoApplication.class, args);
        showBeans();
    }
}
