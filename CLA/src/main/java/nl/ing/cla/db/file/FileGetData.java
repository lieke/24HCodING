package nl.ing.cla.db.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.ParentAccount;
import nl.ing.cla.model.DataFileBasedParentAccount;
import nl.ing.cla.util.CLAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileGetData implements nl.ing.cla.db.GetData {
	@Autowired
	private CLAUtil util;
	
	@Override
	public ChildAccount getChildAccountData(final String name) {
		final String fileName = util.getFileName(name + "_child");
		ObjectMapper mapper = new ObjectMapper();
		try {
			ChildAccount childAccount = mapper.readValue(new File(fileName), ChildAccount.class);
			return childAccount;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ParentAccount getParentAccountData(String name) throws JsonParseException, JsonMappingException, IOException {
		final String fileName = util.getFileName(name + "_parent");
		DataFileBasedParentAccount parentAccountSimple;
		List<ChildAccount> childaccounts = new ArrayList<ChildAccount>();
		
		ObjectMapper mapper = new ObjectMapper();
		parentAccountSimple = mapper.readValue(new File(fileName), DataFileBasedParentAccount.class);
		
		for (String childName: parentAccountSimple.getChildNames()) {
			ChildAccount childAccount = getChildAccountData (childName);
			childaccounts.add(childAccount);
		}
		
		ParentAccount parentAccount = new ParentAccount(parentAccountSimple, childaccounts);
		
		return parentAccount;
		
	}
}
