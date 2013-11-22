package nl.ing.cla.model;

import java.util.LinkedHashMap;
import java.util.Map;


import nl.ing.cla.util.CLAUtil;


public class ChildAccount extends Account {	
	String accountNumber;
	String name;
	int age;
	
	Map<Long, Chore> choreList = new LinkedHashMap<Long, Chore>();
	Map<Long, SavingGoal> goalList = new LinkedHashMap<Long, SavingGoal>();
	
		
	
	public void addChore(Chore chore) {
		final long id = CLAUtil.giveMeAUniqueId();
		chore.setId(id);
		choreList.put(id, chore);
	}
	
	public void setChoreAsDone(final long id) {
		Chore chore = choreList.get(id);
		chore.setAsDone();
	}
	
	//NB: this method only updates status, does not transfer any of the funds or updates goal!
	public void setChoreAsPaid(final long id) {
		Chore chore = choreList.get(id);
		chore.setAsPaid();
	}
	
	public void updateChore(final long id, final Chore chore) {
		choreList.put(id, chore);
	}
	
	public Chore getChore(long id) {
		return choreList.get(id);
	}
	
	public void deleteChore(final long id) {
		choreList.remove(id);
	}
	
	public void addGoal(final SavingGoal savingGoal) {
		final long id =  CLAUtil.giveMeAUniqueId();
		savingGoal.setId(id);
		goalList.put(id, savingGoal);
	}
	
	public void updateGoal(final long id, final SavingGoal savingGoal) {
		goalList.put(id, savingGoal);
	}
	
	public void deleteGoal(final long id) {
		goalList.remove(id);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
