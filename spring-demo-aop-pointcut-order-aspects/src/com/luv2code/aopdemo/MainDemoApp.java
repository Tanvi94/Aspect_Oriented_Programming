package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		//read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);
		
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO",MembershipDAO.class);
		
		Account myAccount = new Account();
		//call the business method
		theAccountDAO.addAccount(myAccount,true);
		
		theAccountDAO.doWork();
		
		//call accountDAO getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCod("silver");
		
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCod();
		
		
		//call the membership business method
		theMembershipDAO.addSillyMember();
		
		theMembershipDAO.goToSleep();
		//close the context
		context.close();
	}

}
