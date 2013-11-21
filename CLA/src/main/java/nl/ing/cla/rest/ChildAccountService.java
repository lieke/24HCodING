package nl.ing.cla.rest;


import javax.sound.midi.Track;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.ing.cla.db.GetData;
import nl.ing.cla.db.SaveData;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;

import org.springframework.stereotype.Component;

@Component
@Path("/childAccounts")
public class ChildAccountService {

	
	@GET
	@Path("/{childName}")
	@Produces(MediaType.APPLICATION_JSON)
	public ChildAccount getTrackInJSON(@PathParam("childName") String childName) {
		return GetData.getChildAccountData(childName);
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{childName}/chores")
	public Response createChoreForChild(@PathParam("childName") String childName, Chore chore) {
		
		ChildAccount childAccount = GetData.getChildAccountData(childName);
		childAccount.addChore(chore);
		
		SaveData.saveChildAccountData(childAccount);
		
		String result = "Chore" + chore.getName() + " added for child" + childAccount.getName();
		return Response.status(201).entity(result).build();		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{childName}/chores/{choreName}")
	public Response createChoreForChild(@PathParam("childName") String childName, @PathParam("choreName") String choreName, Chore chore) {
		
		ChildAccount childAccount = GetData.getChildAccountData(childName);
		childAccount.updateChore(choreName, chore);		
		SaveData.saveChildAccountData(childAccount);
		
		String result = "Chore" + chore.getName() + " updated for child" + childAccount.getName();
		return Response.status(201).entity(result).build();		
	}
	
	
	
}