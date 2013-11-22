package nl.ing.cla.util;

import nl.ing.cla.db.SaveData;
import nl.ing.cla.db.file.FileGetData;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;
import nl.ing.cla.model.DataFileBasedParentAccount;
import nl.ing.cla.model.ParentAccount;
import nl.ing.cla.model.SavingGoal;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitialiser {
	@Autowired
	private FileGetData getData;

	@Autowired
	private SaveData saveData;

	public static List<ParentAccount> parentAccounts = new ArrayList<ParentAccount>();

	@PostConstruct
	public void init() throws JsonGenerationException, JsonMappingException, IOException {
		DataFileBasedParentAccount simpleParentAccount = new DataFileBasedParentAccount();
		simpleParentAccount.setAccountNumber("P001");
		simpleParentAccount.setAge(34);
		simpleParentAccount.setBalance(2000.0);
		simpleParentAccount.setName("FRANCOIS");
		List<String> childrenNames = new ArrayList<String>();
		simpleParentAccount.setChildNames(childrenNames);
		childrenNames.add("LISA");

		ChildAccount ca = new ChildAccount("C001", 20.5, "LISA", 6);
		
		Chore chore = new Chore();
		chore.setName("car wash");
		chore.setPrice(3.5);
		chore.setStatus(Chore.NEW_STATUS);
		chore.setDate("23-11-2013");
		ca.addChore(chore);
		
		SavingGoal goal = new SavingGoal();
		goal.setGoal(25);
		goal.setSaved(5);
		goal.setName("doll");
		ca.addGoal(goal);

		goal = new SavingGoal();
		goal.setGoal(150);
		goal.setSaved(2);
		goal.setName("nintendo");
		ca.addGoal(goal);

		if(getData.getChildAccountData("LISA") == null){
			saveData.saveChildAccountData(ca);
		}
		try {
			if(getData.getParentAccountData("FRANCOIS") == null){
				saveData.saveDataFileBasedParentAccountData(simpleParentAccount);
			}
		} catch (IOException e) {
			saveData.saveDataFileBasedParentAccountData(simpleParentAccount);
		}
	}
}
