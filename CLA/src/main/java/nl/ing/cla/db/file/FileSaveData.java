package nl.ing.cla.db.file;

import java.io.IOException;

import nl.ing.cla.db.SaveData;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.DataFileBasedParentAccount;
import nl.ing.cla.util.CLAUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileSaveData implements SaveData {
	@Autowired
	private CLAUtil util;
	
	@Override
	public void saveChildAccountData(ChildAccount childAccount) throws JsonGenerationException, JsonMappingException, IOException {
		util.saveFile(childAccount.getName() + "_child", childAccount);
			
	}
	
	@Override
	public void saveDataFileBasedParentAccountData(DataFileBasedParentAccount parentAccount) throws JsonGenerationException, JsonMappingException, IOException {
		util.saveFile(parentAccount.getName() + "_parent", parentAccount);		
	}
	
	
	
}
