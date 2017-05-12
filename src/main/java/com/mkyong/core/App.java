package com.mkyong.core;

import com.mkyong.bo.CustomerBo;
import com.mkyong.customer.services.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by phuongdv on 5/12/17.
 */
public class App {

    public static void main(String[] args) {
        domain2();
    }



    public static void domain2(){

        ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring-Customer.xml");

    }

    public static void demo1(){

//        ApplicationContext appContext = new ClassPathXmlApplicationContext("Spring-Customer.xml");
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[] { "Spring-Customer.xml" });

//        CustomerBo custome<value>hijackAfterMethodBean</value>r = (CustomerBo) appContext.getBean("customerBo");
//        customer.addCustomer();
//        customer.addCustomerReturnValue();

//        String[] argstrs = new String[] {"phuong","ngoc","cuong"};
//
//        customer.addCustomerAround(argstrs);
        CustomerService cust = (CustomerService) appContext.getBean("customerServiceProxy");
        System.out.println("*************************");
        cust.printName();
        System.out.println("*************************");
        cust.printURL();
        System.out.println("*************************");
        try {
            cust.printThrowException();
        } catch (Exception e) {

        }

    }
}
