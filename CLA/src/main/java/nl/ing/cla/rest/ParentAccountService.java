package nl.ing.cla.rest;

import java.io.IOException;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import nl.ing.cla.db.GetData;
import nl.ing.cla.model.ParentAccount;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Component;

@Component
@Path("/parentAccounts")
public class ParentAccountService {

	@GET
	@Path("/{parentName}")
	@Produces(MediaType.APPLICATION_JSON)
	public ParentAccount getTrackInJSON(@PathParam("parentName") String parentName) {
		ParentAccount pa = null;
		try {
			pa = GetData.getParentAccountData(parentName);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pa;
	}
}