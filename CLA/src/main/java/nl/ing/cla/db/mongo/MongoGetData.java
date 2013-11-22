package nl.ing.cla.db.mongo;

import nl.ing.cla.db.GetData;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.ParentAccount;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: adriaan
 * Date: 22-11-2013
 * Time: 05:58
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MongoGetData implements GetData {
	@Autowired
	private Datastore datastore;

	@Override
	public ChildAccount getChildAccountData (final String name) {
		return datastore.find(ChildAccount.class, "name", name).get();
	}

	@Override
	public ParentAccount getParentAccountData(String name) throws IOException {
		return datastore.find(ParentAccount.class, "name", name).get();
	}

}