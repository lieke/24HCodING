package nl.ing.cla.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.ing.cla.model.Person;
import nl.ing.cla.model.Track;

import org.springframework.stereotype.Component;

@Component
@Path("/json/person")
public class ParentAccountService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getTrackInJSON() {

		Person person = new Person(1, "LISA", "F", 06 );
		return person;

	}
	
	@GET
	@Path("/get/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getTrackInJSON(@PathParam("trackId") String trackId) {

		Person person = new Person(1, "LISA", "F", 06 );
		return person;
	}
	

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(Track track) {

		String result = "Person saved : " + track;
		return Response.status(201).entity(result).build();
		
	}
	
}