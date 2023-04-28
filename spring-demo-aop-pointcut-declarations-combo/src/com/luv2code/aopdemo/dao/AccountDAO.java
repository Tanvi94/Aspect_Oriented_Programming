package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCod;
	
	public String getName() {
		System.out.println(getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()");
		this.name = name;
	}

	public String getServiceCod() {
		System.out.println(getClass() + ": in getServiceCod()");
		return serviceCod;
	}

	public void setServiceCod(String serviceCod) {
		System.out.println(getClass() + ":in setServiceCod()");
		this.serviceCod = serviceCod;
	}

	public void addAccount(Account account,boolean vipFlag) {
		System.out.println(getClass()+ ":DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public boolean doWork() {
		System.out.println(getClass()+": doWork()");
		return false;
	}
}
