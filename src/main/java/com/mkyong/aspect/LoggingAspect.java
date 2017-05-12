package com.mkyong.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * Created by phuongdv on 5/12/17.
 */

/**
 *  Modularization of a concern that cuts across multiple classes.
 *  AspectJ style
 */
@Aspect
public class LoggingAspect {

    /**
     * JoinPoint: a point during the execution of a program, such as the execution
     * of a method or the handling of an exception.
     * In Spring AOP, a join point always represents a method execution.
     * TRIGGER
     *
     * @param joinPoint
     */

    @Before("execution(* com.mkyong.bo.CustomerBo.addCustomer(..))")
    public void logBefore(JoinPoint joinPoint) {
        // this advice : action taken by an aspect at a  particular point cut
        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }

    /**
     * Execute after the execution of customeBO interface, catch and intercept(chan, cat) the return value
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(
            pointcut = "execution(* com.mkyong.bo.CustomerBo.addCustomerReturnValue(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        System.out.println("logAfterReturning() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
        System.out.println("******");

    }


    @AfterThrowing(
            pointcut = "execution(* com.mkyong.bo.CustomerBo.addCustomerThrowException(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
        System.out.println("******");
    }

    @Around("execution(* com.mkyong.bo.CustomerBo.addCustomerAround(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("logAround() is running!");
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
        System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));
        System.out.println(joinPoint.getArgs()[0]);

        System.out.println("Around before is running!");
        joinPoint.proceed(); //continue on the intercepted method
        System.out.println("Around after is running!");

        System.out.println("******");

    }
}
