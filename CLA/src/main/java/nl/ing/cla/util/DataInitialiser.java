package nl.ing.cla.util;

import nl.ing.cla.db.GetData;
import nl.ing.cla.db.SaveData;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;
import nl.ing.cla.model.DataFileBasedParentAccount;
import nl.ing.cla.model.ParentAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitialiser {
	@Autowired
	private GetData getData;

	@Autowired
	private SaveData saveData;

	public static List<ParentAccount> parentAccounts = new ArrayList<ParentAccount>();

	@PostConstruct
	public void init() {
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
		ca.addChore(chore);

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
