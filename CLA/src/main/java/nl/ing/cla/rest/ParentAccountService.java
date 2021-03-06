package nl.ing.cla.rest;

import nl.ing.cla.db.GetData;
import nl.ing.cla.db.SaveData;
import nl.ing.cla.exception.BalanceToLowToTransferMoneyException;
import nl.ing.cla.exception.ErrorException;
import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;
import nl.ing.cla.model.DataFileBasedParentAccount;
import nl.ing.cla.model.ParentAccount;
import nl.ing.cla.util.RoaringLion;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Component
@Path("/parentAccounts")
public class ParentAccountService {

	@Autowired
	private GetData getData;
	
	@Autowired
	private SaveData saveData;

	@Autowired
	private RoaringLion lion;

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
	public Response completeChoreForChild(
			@PathParam("parentName") String parentName,
			@PathParam("choreID") long choreID,
			@PathParam("childName") String childName) throws BalanceToLowToTransferMoneyException {

		ParentAccount parent = null;
		try {
			parent = getData.getParentAccountData(parentName);
		} catch (IOException e) {
			throw new ErrorException(e);
		}
		
		ChildAccount child = getData.getChildAccountData(childName);
		Chore chore = child.getChore(choreID);
	
		// complete chore (change status)
		chore.setAsPaid();
			
		child.updateChore(choreID, chore);
		// transfer money from parent to child account
		parent.transferTo(chore.getPrice(), child);
		
		try {
			saveData.saveChildAccountData(child);	
			saveData.saveDataFileBasedParentAccountData(new DataFileBasedParentAccount(parent));

			lion.deposit(chore.getPrice());
		} catch (IOException e) {
			throw new ErrorException(e);
		}
		
		return Response.status(201).entity(child.getName() + " got paid for another chore!!").build();
	}

}