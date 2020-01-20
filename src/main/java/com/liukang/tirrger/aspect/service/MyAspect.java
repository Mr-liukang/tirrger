/*
package com.liukang.tirrger.aspect.service;


import com.liukang.tirrger.aspect.validator.UserValidator;
import com.liukang.tirrger.aspect.validator.impl.UserValidatorImpl;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    */
/*@DeclareParents(value="com.liukang.tirrger.aspect.service.impl.UserServiceImpl+",defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;*//*

    @DeclareParents(value = "com.liukang.tirrger.aspect.service.impl.UserServiceImpl",defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;
   @Pointcut("execution(* com.liukang.tirrger.aspect.service.impl.UserServiceImpl.printUser(..))")
   public void pointCut(){
       System.out.println("pointCut...");
   }
   @Before("pointCut()")
    public void before(){
       System.out.println("before...");
   }
   @After("pointCut()")
    public void after(){
       System.out.println("after...");
   }
   @AfterReturning("pointCut()")
    public void afterReturning(){
       System.out.println("afterReturning...");
   }
   @AfterThrowing("pointCut()")
    public void afterThrowing(){
       System.out.println("afterThrowing...");
   }

}
*/
