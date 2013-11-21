package nl.ing.cla.model;

import java.util.ArrayList;
import java.util.List;

public class ParentAccount extends ParentAccountBase {	
	List<ChildAccount> childaccounts = new ArrayList<ChildAccount>();
	
	public ParentAccount(DataFileBasedParentAccount parentAccountSimple, List<ChildAccount> childAccounts) {
		this.accountNumber = parentAccountSimple.getAccountNumber();
		this.balance = parentAccountSimple.getBalance();
		this.name = parentAccountSimple.getName();
		this.age = parentAccountSimple.getAge();
		this.childaccounts = childAccounts;
	}

	public ParentAccount(String accountNumber, double balance, String name, int age) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.name = name;
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
