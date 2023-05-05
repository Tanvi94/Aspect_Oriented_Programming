package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

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
	
	private static Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());

	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	 public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinpoint) throws Throwable {
		
		//print out which method we are advising on
		String method = theProceedingJoinpoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @Around on method: "+method);
		
		//get begin timestamp
		long begin = System.currentTimeMillis();
		
		//execute the method
		
		Object result = theProceedingJoinpoint.proceed();	
		
		//get end timestamp
		long stop = System.currentTimeMillis();
		
		//compute duration and display it
		long duration = stop - begin;
		myLogger.info("\n====> Duration: "+duration/1000.0+ "seconds");
		
		return result;
		
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
				//print out which method we are advising on
				String method = theJoinPoint.getSignature().toShortString();
				myLogger.info("\n=====>>> Executing @After(Finally) on method: "+method);
				
	}
	
	@AfterThrowing(
				   pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
				   throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint,Throwable theExc) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====> Executing @AfterThrowing on method: "+method);
		
		//log the exception
		myLogger.info("\n====>>> The Exception is: "+theExc);
	}
	
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint,List<Account> result) {
		
		//print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Executing @AfterResturning on method: "+method);
		
		//print out the results of the method call
		myLogger.info("Result is: "+result);
		
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
		myLogger.info("Result is: "+result);
		
		
	}


	//lets start with an @Before advice
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJointPoint) {
		myLogger.info("\n======>>> Execution @Before Advice on method");
		
		//display the method signature
		MethodSignature methoSig = (MethodSignature) theJointPoint.getSignature();
		myLogger.info("Method: "+ methoSig);
		
		//display method arguments
		
		//get args
		Object[] args = theJointPoint.getArgs();
		
		//loop through args
		for(Object tempArg: args) {
			myLogger.info(tempArg.toString());
			if(tempArg instanceof Account) {
				//downcast and print specific stuff
				Account theAcc = (Account) tempArg;
				myLogger.info("Account name: "+theAcc.getName());
				myLogger.info("Account level: "+theAcc.getLevel());
			}
		}
	}
	
	
}
