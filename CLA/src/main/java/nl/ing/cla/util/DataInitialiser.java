package nl.ing.cla.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.ing.cla.db.GetData;
import nl.ing.cla.db.SaveData;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.DataFileBasedParentAccount;
import nl.ing.cla.model.ParentAccount;

import org.springframework.stereotype.Component;

@Component
public class DataInitialiser {
	
	public static List<ParentAccount> parentAccounts = new ArrayList<ParentAccount>();
	
	static{
		DataFileBasedParentAccount simpleParentAccount = new DataFileBasedParentAccount();
		simpleParentAccount.setAccountNumber("P001");
		simpleParentAccount.setAge(34);
		simpleParentAccount.setBalance(2000.0);
		simpleParentAccount.setName("FRANCOIS");
		List<String> childrenNames = new ArrayList<String>();
		simpleParentAccount.setChildNames(childrenNames);
		childrenNames.add("LISA");
		
		ChildAccount ca = new ChildAccount("C001", 20.5, "LISA", 6);

		if(GetData.getChildAccountData("LISA") == null){
			SaveData.saveChildAccountData(ca);
		}
		try {
			if(GetData.getParentAccountData("FRANCOIS") == null){
				SaveData.saveDataFileBasedParentAccountData(simpleParentAccount);
			}
		} catch (IOException e) {
			SaveData.saveDataFileBasedParentAccountData(simpleParentAccount);
		}
	}
	
}
