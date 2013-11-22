package nl.ing.cla.db.mongo;

import nl.ing.cla.db.SaveData;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.DataFileBasedParentAccount;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: adriaan
 * Date: 22-11-2013
 * Time: 06:36
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MongoSaveData implements SaveData {
	@Autowired
	private Datastore datastore;


	@Override
	public void saveChildAccountData(ChildAccount childAccount) throws IOException {
		datastore.save(childAccount);
	}

	@Override
	public void saveDataFileBasedParentAccountData(DataFileBasedParentAccount parentAccount) throws IOException {
		datastore.save(parentAccount);
	}
}
