package nl.ing.cla.util;

import java.util.ArrayList;
import java.util.List;

import nl.ing.cla.db.GetData;
import nl.ing.cla.db.SaveData;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.ParentAccount;

import org.springframework.stereotype.Component;

@Component
public class DataInitialiser {
	
	public static List<ParentAccount> parentAccounts = new ArrayList<ParentAccount>();
	
	static{
		ParentAccount pa = new ParentAccount("P001", 2000.0, "Francois", 34);
		ChildAccount ca = new ChildAccount("C001", 20.5, "LISA", 6);
		pa.addChildAccount(ca);
		parentAccounts.add(pa);
		
		if(GetData.getChildAccountData("LISA") == null){
			SaveData.saveChildAccountData(ca);
		}
	}
	
}
