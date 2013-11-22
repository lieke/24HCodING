package nl.ing.cla.db;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.DataFileBasedParentAccount;
import nl.ing.cla.util.CLAUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveData {
	@Autowired
	private CLAUtil util;
	
	public void saveChildAccountData (ChildAccount childAccount) throws JsonGenerationException, JsonMappingException, IOException {
		util.saveFile(childAccount.getName() + "_child", childAccount);
			
	}
	
	public void saveDataFileBasedParentAccountData (DataFileBasedParentAccount parentAccount) throws JsonGenerationException, JsonMappingException, IOException {		
		util.saveFile(parentAccount.getName() + "_parent", parentAccount);		
	}
	
	
	
}
