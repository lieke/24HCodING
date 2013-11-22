package nl.ing.cla.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.ing.cla.db.GetData;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;
import nl.ing.cla.model.ParentAccount;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/parentAccounts")
public class ParentAccountService {

	@Autowired
	private GetData getData;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ParentAccount getTrackInJSON() {

		ParentAccount pa = null;
		try {
			pa = getData.getParentAccountData("FRANCOIS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pa;
	}

	@GET
	@Path("/{parentName}")
	@Produces(MediaType.APPLICATION_JSON)
	public ParentAccount getTrackInJSON(
			@PathParam("parentName") String parentName) {
		ParentAccount pa = null;
		try {
			pa = getData.getParentAccountData(parentName);
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

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{parentName}/completes/{choreID}/for/{childName}")
	public Response createChoreForChild(
			@PathParam("parentName") String parentName,
			@PathParam("choreID") long choreID,
			@PathParam("childName") String childName) {

		try {
			ParentAccount parent = getData.getParentAccountData(parentName);
			ChildAccount child = getData.getChildAccountData(childName);
			Chore chore = child.getChore(choreID);

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

		return null;// Response.status(201).entity(chore.getId()).build();
	}

}