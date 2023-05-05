package com.luv2code.aopdemo;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundwithLoggerDemoApp {
	
	private static Logger myLogger = Logger.getLogger(AroundwithLoggerDemoApp.class.getName());

	public static void main(String[] args) {
		//read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring container
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService",TrafficFortuneService.class);
		
		myLogger.info("\nMain program: AroundDemoApp");
		
		myLogger.info("calling getFortune");
		
		String data = theFortuneService.getFortune();
		
		myLogger.info("\nMy Fortune is: "+data);
		
		myLogger.info("Finished");
		
		//close the context
		context.close();
	}

}
