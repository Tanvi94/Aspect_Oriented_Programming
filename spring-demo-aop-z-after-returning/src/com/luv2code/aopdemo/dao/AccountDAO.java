package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

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
	
	//add a new method: findAccounts()
	public List<Account> findAccounts(){
		List<Account> myAccounts = new ArrayList<>();
		//create sample accounts
		Account temp1 = new Account("John","Silver");
		Account temp2 = new Account("Madhu","Platinum");
		Account temp3 = new Account("Tanvi","Gold");
		
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		//add them to our accounts list
		return myAccounts;
	}

	public void addAccount(Account account,boolean vipFlag) {
		System.out.println(getClass()+ ":DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public boolean doWork() {
		System.out.println(getClass()+": doWork()");
		return false;
	}
}
