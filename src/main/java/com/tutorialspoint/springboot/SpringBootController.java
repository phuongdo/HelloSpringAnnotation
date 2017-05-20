package com.tutorialspoint.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by phuongdv on 5/18/17.
 */
@Controller
@RequestMapping("/hello")
public class SpringBootController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * The key difference points are : forward to VIEW (eg. JSP, Template) , it simply returns the data to be
     * written directly to the @BODY
     * @param name
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
