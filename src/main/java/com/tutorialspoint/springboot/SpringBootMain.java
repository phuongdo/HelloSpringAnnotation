package com.tutorialspoint.springboot;

/**
 * Created by phuongdv on 5/18/17.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @SpringBootApplication provides a load of defaults ( like the embedded servlet container)
 *  It also turns on Spring MVC’s @EnableWebMvc annotation that activates web endpoints.
 */
@SpringBootApplication
public class SpringBootMain {

    public static void main(String[] args) {
        // running without service endpoinds
        SpringApplication.run(SpringBootMain.class,args);

    }
}
