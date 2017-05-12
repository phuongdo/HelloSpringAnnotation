package com.tutorialspoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// XML configuration the same
// <beans>
//        <bean id = "helloWorld" class = "com.tutorialspoint.HelloWorld" />
//</beans>
@Configuration
public class HelloWorldConfig {
    @Bean
    public HelloWorld helloWorld(){
        return new HelloWorld();
    }
}
