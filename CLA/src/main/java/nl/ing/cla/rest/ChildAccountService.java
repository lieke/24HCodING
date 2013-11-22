package nl.ing.cla.rest;


import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import nl.ing.cla.model.SavingGoal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/childAccounts")
public class ChildAccountService {

	@Autowired
	private GetData getData;

	@Autowired
	private SaveData saveData;

	
	@GET
	@Path("/{childName}")
	@Produces(MediaType.APPLICATION_JSON)
	public ChildAccount getTrackInJSON(@PathParam("childName") String childName) {
		return getData.getChildAccountData(childName);
	}
	
	@GET
	@Path("/{childName}/chores")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Long, Chore> getAllChores(@PathParam("childName") String childName) {		
		return getData.getChildAccountData(childName).getChoreList();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{childName}/chores")
	public Response createChoreForChild(@PathParam("childName") String childName, Chore chore) {
		
		ChildAccount childAccount = getData.getChildAccountData(childName);
		childAccount.addChore(chore);
		
		saveData.saveChildAccountData(childAccount);	
		
		return Response.status(201).entity(chore.getId()).build();		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{childName}/chores/{choreID}")
	public Response updateOrCreateChoreForChild(@PathParam("childName") String childName, @PathParam("choreID") long choreID, Chore chore) {
		
		ChildAccount childAccount = getData.getChildAccountData(childName);
		childAccount.updateChore(choreID, chore);		
		saveData.saveChildAccountData(childAccount);
		
		return Response.status(201).entity(chore).build();		
	}
	
	@DELETE
	@Path("/{childName}/chores/{choreID}")
	public Response deleteChoreForChild(@PathParam("childName") String childName, @PathParam("choreID") long choreID) {
		
		ChildAccount childAccount = getData.getChildAccountData(childName);
		childAccount.deleteChore(choreID);	
		saveData.saveChildAccountData(childAccount);
		
		return Response.status(201).entity("Delete chore with ID=" + choreID + " successfully.").build();		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{childName}/goals")
	public Response createSavingGoalForChild(@PathParam("childName") String childName, SavingGoal goal) {
		
		ChildAccount childAccount = getData.getChildAccountData(childName);
		childAccount.addGoal(goal);
		
		saveData.saveChildAccountData(childAccount);
		
		return Response.status(201).entity(goal.getId()).build();		
	}
	
	@POST
	@Path("/{childName}/finished/{choreID}")
	public Response finishChore(@PathParam("childName") String childName, @PathParam("choreID") long choreID) {
		ChildAccount childAccount = getData.getChildAccountData(childName);
		childAccount.getChore(choreID).setAsDone();
		return Response.status(201).entity(childName + " finished chore " + choreID).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{childName}/goals/{goalID}")
	public Response updateOrCreateSavingGoalForChild(@PathParam("childName") String childName, @PathParam("goalID") long goalID, SavingGoal goal) {		
		ChildAccount childAccount = getData.getChildAccountData(childName);
		childAccount.updateGoal(goalID, goal);	
		saveData.saveChildAccountData(childAccount);
		
		return Response.status(201).entity(goal).build();		
	}
	
	@DELETE
	@Path("/{childName}/goals/{goalID}")
	public Response deleteSavingGoalForChild(@PathParam("childName") String childName, @PathParam("goalID") long goalID) {		
		ChildAccount childAccount = getData.getChildAccountData(childName);
		childAccount.deleteGoal(goalID);
		saveData.saveChildAccountData(childAccount);
		
		return Response.status(201).entity("Delete saving goal with ID=" + goalID + " successfully.").build();		
	}
	
	
}