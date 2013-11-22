package nl.ing.cla.db;

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.ParentAccount;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: adriaan
 * Date: 22-11-2013
 * Time: 06:03
 * To change this template use File | Settings | File Templates.
 */
public interface GetData {
	ChildAccount getChildAccountData (String name);

	ParentAccount getParentAccountData(String name) throws JsonParseException, JsonMappingException, IOException;
}
