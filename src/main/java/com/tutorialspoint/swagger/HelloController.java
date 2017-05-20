package com.tutorialspoint.swagger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by phuongdv on 5/19/17.
 */
@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, value = "/api/hello")
    public String sayHello(){
        return "Swagger say hello";
    }

}
