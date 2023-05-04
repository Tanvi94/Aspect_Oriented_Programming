package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	 public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinpoint) throws Throwable {
		
		//print out which method we are advising on
		String method = theProceedingJoinpoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @Around on method: "+method);
		
		//get begin timestamp
		long begin = System.currentTimeMillis();
		
		//execute the method
		
		Object result = theProceedingJoinpoint.proceed();	
		
		//get end timestamp
		long stop = System.currentTimeMillis();
		
		//compute duration and display it
		long duration = stop - begin;
		System.out.println("\n====> Duration: "+duration/1000.0+ "seconds");
		
		return result;
		
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
				//print out which method we are advising on
				String method = theJoinPoint.getSignature().toShortString();
				System.out.println("\n=====>>> Executing @After(Finally) on method: "+method);
				
	}
	
	@AfterThrowing(
				   pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
				   throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint,Throwable theExc) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====> Executing @AfterThrowing on method: "+method);
		
		//log the exception
		System.out.println("\n====>>> The Exception is: "+theExc);
	}
	
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint,List<Account> result) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @AfterResturning on method: "+method);
		
		//print out the results of the method call
		System.out.println("Result is: "+result);
		
		//let's post-process the data --lets modify it
		
		//convert the account names to uppercase
		convertAccountNamestoUpperCase(result);
		
	}
	
	private void convertAccountNamestoUpperCase(List<Account> result) {
		
		//loop through accounts
		//get Upparcase version of account name
		//update name on the account
				
		for(Account acc: result) {
			String theUpperName = acc.getName().toUpperCase();
			acc.setName(theUpperName);
		}
		System.out.println("Result is: "+result);
		
		
	}


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
