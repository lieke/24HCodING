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
	
	public void saveChildAccountData (ChildAccount childAccount) {
		try {
			util.saveFile(childAccount.getName() + "_child", childAccount);
		} catch (JsonGenerationException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void saveDataFileBasedParentAccountData (DataFileBasedParentAccount parentAccount) {
		try {
			util.saveFile(parentAccount.getName() + "_parent", parentAccount);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
}
