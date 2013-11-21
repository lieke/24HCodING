package nl.ing.cla.db;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.util.CLAUtil;

public class SaveData {
	
	public static void saveChildAccountData (ChildAccount childAccount) {
		try {
			CLAUtil.saveFile(childAccount.getName(), childAccount);
		} catch (JsonGenerationException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	

}
