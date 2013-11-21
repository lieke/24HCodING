package nl.ing.cla.db;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.ParentAccount;
import nl.ing.cla.model.ParentAccountSimple;
import nl.ing.cla.util.CLAUtil;

public class GetData {
	
	public static ChildAccount getChildAccountData (final String name) {
		final String fileName = CLAUtil.getFileName(name);
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

	public static ParentAccount getParentAccountData (String name) {
		final String fileName = CLAUtil.getFileName(name);
		ParentAccountSimple parentAccountSimple;
		ParentAccount parentAccount;
		
		return null;
		
	}
}