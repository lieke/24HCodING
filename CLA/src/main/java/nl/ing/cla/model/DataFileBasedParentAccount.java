package nl.ing.cla.model;

import java.util.ArrayList;
import java.util.List;

public class DataFileBasedParentAccount extends ParentAccountBase{	
	List<String> childNames = new ArrayList<String>();

	public DataFileBasedParentAccount() {
		
	}
	public DataFileBasedParentAccount(ParentAccount parentAccount) {
		this.accountNumber = parentAccount.getAccountNumber();
		this.balance = parentAccount.getBalance();
		this.name = parentAccount.getName();
		this.age = parentAccount.getAge();
		for (ChildAccount childAccount:parentAccount.getChildaccounts()) {
			this.childNames.add(childAccount.getName());
		}
	}
	
	public List<String> getChildNames() {
		return childNames;
	}
	public void setChildNames(List<String> childNames) {
		this.childNames = childNames;
	}
}
