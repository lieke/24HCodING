package nl.ing.cla.config;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: adriaan
 * Date: 22-11-2013
 * Time: 06:09
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class SpringConfiguraton {
	@Bean
	public Datastore getMongoDataStore() throws UnknownHostException {
		MongoClient client = new MongoClient("172.16.35.203");
		Morphia morphia = new Morphia();
		Datastore ds = morphia.createDatastore(client, "cla");
		morphia.mapPackage("nl.ing.cla.model");
		return ds;
	}
}
