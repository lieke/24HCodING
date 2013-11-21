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

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.Chore;

import org.springframework.stereotype.Component;

@Component
@Path("/json/childAccount")
public class ChildAccountService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ChildAccount getTrackInJSON() {

		ChildAccount ca = new ChildAccount("C001", 20.5, "LISA", 6);
		return ca;

	}
	
	@GET
	@Path("/{childName}")
	@Produces(MediaType.APPLICATION_JSON)
	public ChildAccount getTrackInJSON(@PathParam("childName") String childName) {

		ChildAccount ca = new ChildAccount("C001", 20.5, childName, 6);
		return ca;
	}
	

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{childName}/chore")
	public Response createChoreForChild(Chore chore) {
		
		ChildAccount ca = new ChildAccount("C001", 20.5, "LISA", 6);
		ca.addChore(chore);
		String result = "Chore" + chore.getName() + "added for child" + ca.getName();
		return Response.status(201).entity(result).build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Track track) {

		String result = "Child saved : " + track;
		return Response.status(201).entity(result).build();
		
	}
	
}