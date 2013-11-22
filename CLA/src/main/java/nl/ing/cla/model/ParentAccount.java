package nl.ing.cla.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Entity("parents")
@Index("name")
public class ParentAccount extends ParentAccountBase {
	@Reference
	List<ChildAccount> childaccounts = new ArrayList<ChildAccount>();

	@Id
	private ObjectId id;
	
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
