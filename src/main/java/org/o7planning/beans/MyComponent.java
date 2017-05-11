package org.o7planning.beans;

import org.springframework.stereotype.Component;

/**
 * Created by phuongdv on 5/11/17.
 */


//Indicates that an annotated class is a "component".
//        Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MyComponent {

    @Autowired
    private MyRepository repository;

    public void showAppInfo() {
        System.out.println("Now is: "+ repository.getSystemDateTime());
        System.out.println("App Name: "+ repository.getAppName());
    }

}