package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	//lets start with an @Before advice
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJointPoint) {
		System.out.println("\n======>>> Execution @Before Advice on method");
		
		//display the method signature
		MethodSignature methoSig = (MethodSignature) theJointPoint.getSignature();
		System.out.println("Method: "+ methoSig);
		//display method arguments
	}
	
	
}
