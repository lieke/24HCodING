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

		ChildAccount ca = new ChildAccount("C001", 98, "LISA", 6);
		
		Chore chore = new Chore();
		chore.setName("auto wassen");
		chore.setPrice(5);
		chore.setStatus(Chore.NEW_STATUS);
		chore.setDate("23-11-2013");
		chore.setType("carwash");
		ca.addChore(chore);
		
		chore = new Chore();
		chore.setName("afwassen");
		chore.setPrice(1);
		chore.setStatus(Chore.NEW_STATUS);
		chore.setDate("23-11-2013");
		chore.setType("dishwasher");
		ca.addChore(chore);
		
		SavingGoal goal = new SavingGoal();
		goal.setGoal(120);
		goal.setSaved(20);
		goal.setName("bicycle");
		ca.addGoal(goal);

		goal = new SavingGoal();
		goal.setGoal(80);
		goal.setSaved(78);
		goal.setName("furby");
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
