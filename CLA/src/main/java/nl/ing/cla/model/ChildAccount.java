package nl.ing.cla.model;

import java.util.ArrayList;
import java.util.List;

public class ChildAccount {

	int id;
	String accountNumber;
	double balance;
	String name;
	int age;
	List<Chore> choreList = new ArrayList<Chore>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	
	public ChildAccount(int id, String accountNumber, double balance, String name, int age){
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.id = id;
		this.age = age;
		this.name = name;
		
	}
	
}
