package com.tutorialspoint.scheduler;

/**
 * Created by phuongdv on 5/20/17.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Configuration : source of bean definitions for the application context
 * @EnableAutoConfiguration: add bean base on CLASSPATH setting
 * @EnableWebMvc (automatically added when it sees spring-webmvc on the CLASSPATH)
 * @ComponentScan tells Spring to look for other components,services, configurations
 *
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}