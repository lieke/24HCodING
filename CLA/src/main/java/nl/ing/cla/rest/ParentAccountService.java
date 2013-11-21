package nl.ing.cla.rest;

import javax.sound.midi.Track;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.ing.cla.model.ChildAccount;
import nl.ing.cla.model.ParentAccount;

import org.springframework.stereotype.Component;

@Component
@Path("/json/parentAccount")
public class ParentAccountService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ParentAccount getTrackInJSON() {

		ParentAccount pa = new ParentAccount(1,"P001", 2000.0, "Francois", 34);
		ChildAccount ca = new ChildAccount(1, "C001", 20.5, "LISA", 6);
		pa.addChildAccount(ca);
		return pa;

	}
	
	@GET
	@Path("/{parentAccountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ParentAccount getTrackInJSON(@PathParam("parentAccountId") int parentAccountId) {

		ParentAccount pa = new ParentAccount(parentAccountId,"P001", 2000.0, "Francois", 34);
		ChildAccount ca = new ChildAccount(1, "C001", 20.5, "LISA", 6);
		pa.addChildAccount(ca);
		return pa;
	}
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Track track) {

		String result = "Parent saved : " + track;
		return Response.status(201).entity(result).build();
		
	}
	
}