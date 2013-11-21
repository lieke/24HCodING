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

import org.springframework.stereotype.Component;

@Component
@Path("/json/childAccount")
public class ChildAccountService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public ChildAccount getTrackInJSON() {

		ChildAccount ca = new ChildAccount(1, "C001", 20.5, "LISA", 6);
		return ca;

	}
	
	@GET
	@Path("/get/{childAcountId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ChildAccount getTrackInJSON(@PathParam("childAcountId") String childAcountId) {

		ChildAccount ca = new ChildAccount(1, "C001", 20.5, "LISA", 6);
		return ca;
	}
	

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Track track) {

		String result = "Child saved : " + track;
		return Response.status(201).entity(result).build();
		
	}
	
}