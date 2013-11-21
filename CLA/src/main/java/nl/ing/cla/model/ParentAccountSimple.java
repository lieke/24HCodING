package nl.ing.cla.model;

import java.util.ArrayList;
import java.util.List;

public class ParentAccountSimple {	
	String accountNumber;
	double balance;
	String name;
	int age;
	List<String> childNames = new ArrayList<String>();
	
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
	public List<String> getChildNames() {
		return childNames;
	}
	public void setChildNames(List<String> childNames) {
		this.childNames = childNames;
	}
	
	

}
