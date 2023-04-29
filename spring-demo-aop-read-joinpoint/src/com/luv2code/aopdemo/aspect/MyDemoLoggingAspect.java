package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

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
		
		//get args
		Object[] args = theJointPoint.getArgs();
		
		//loop through args
		for(Object tempArg: args) {
			System.out.println(tempArg);
			if(tempArg instanceof Account) {
				//downcast and print specific stuff
				Account theAcc = (Account) tempArg;
				System.out.println("Account name: "+theAcc.getName());
				System.out.println("Account level: "+theAcc.getLevel());
			}
		}
	}
	
	
}
