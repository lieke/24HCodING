package nl.ing.cla.model;

import java.util.ArrayList;
import java.util.List;

public class ParentAccount {	
	String accountNumber;
	double balance;
	String name;
	int age;
	List<ChildAccount> childaccounts = new ArrayList<ChildAccount>();
	
	public ParentAccount(String accountNumber, double balance, String name, int age){
		this.accountNumber = accountNumber;
		this.balance = balance;		
		this.name = name;
		this.age = age;
		
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<ChildAccount> getChildaccounts() {
		if(childaccounts == null){
			childaccounts = new ArrayList<ChildAccount>();
		}
		return childaccounts;
	}

	public void setChildaccounts(List<ChildAccount> childaccounts) {
		this.childaccounts = childaccounts;
	}
	
	public void addChildAccount(ChildAccount ca){
		childaccounts.add(ca);
		
	}
}
