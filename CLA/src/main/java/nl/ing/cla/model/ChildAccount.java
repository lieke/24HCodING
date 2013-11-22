package nl.ing.cla.model;

import java.util.LinkedHashMap;
import java.util.Map;

import nl.ing.cla.util.CLAUtil;

import org.springframework.beans.factory.annotation.Autowired;

public class ChildAccount {
	
	String accountNumber;
	double balance;
	String name;
	int age;
	
	Map<Long, Chore> choreList = new LinkedHashMap<Long, Chore>();
	Map<Long, SavingGoal> goalList = new LinkedHashMap<Long, SavingGoal>();
	
	public void addChore(final Chore chore) {
		final long id = CLAUtil.giveMeAUniqueId();
		chore.setId(id);
		choreList.put(id, chore);
	}
	
	public void updateChore(final long id, final Chore chore) {
		choreList.put(id, chore);
	}
	
	public void addGoal(final SavingGoal savingGoal) {
		final long id =  CLAUtil.giveMeAUniqueId();
		savingGoal.setId(id);
		goalList.put(id, savingGoal);
	}
	
	public void updateGoal(final long id, final SavingGoal savingGoal) {
		goalList.put(id, savingGoal);
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
	
	public Map<Long, Chore> getChoreList() {
		return choreList;
	}

	public void setChoreList(Map<Long, Chore> choreList) {
		this.choreList = choreList;
	}

	public Map<Long, SavingGoal> getGoalList() {
		return goalList;
	}

	public void setGoalList(Map<Long, SavingGoal> goalList) {
		this.goalList = goalList;
	}

	public ChildAccount(){
		
	}
	
	public ChildAccount(String accountNumber, double balance, String name, int age){
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.age = age;
		this.name = name;
		
	}
	
}
