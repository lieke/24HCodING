package nl.ing.cla.db;

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.DataFileBasedParentAccount;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: adriaan
 * Date: 22-11-2013
 * Time: 06:05
 * To change this template use File | Settings | File Templates.
 */
public interface SaveData {
	void saveChildAccountData (ChildAccount childAccount) throws JsonGenerationException, JsonMappingException, IOException;

	void saveDataFileBasedParentAccountData (DataFileBasedParentAccount parentAccount) throws JsonGenerationException, JsonMappingException, IOException;
}
