package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {


    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("Beans3.xml");

        // Let us raise a start event.
        context.start();

        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();

        // Let us raise a stop event.
        context.stop();
    }
    public static void main3(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(TextEditorConfig.class);

        TextEditor te = ctx.getBean(TextEditor.class);
        te.spellCheck();
    }
    public static void main2(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

        HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
        helloWorld.setMessage("Hello World!");
        helloWorld.getMessage();

    }
    public static void main1(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("BeansInject.xml");

//        HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
//        objA.getMessage1();
//        objA.getMessage2();
//
//        HelloIndia objB = (HelloIndia) context.getBean("helloIndia");
//        objB.getMessage1();
//        objB.getMessage2();
//        objB.getMessage3();


//        TextEditor te = (TextEditor) context.getBean("textEditor");
//        te.spellCheck();
        JavaCollection jc=(JavaCollection)context.getBean("javaCollection");

        jc.getAddressList();
        jc.getAddressSet();
        jc.getAddressMap();
        jc.getAddressProp();
    }
}