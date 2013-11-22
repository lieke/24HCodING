package nl.ing.cla.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ErrorException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7175326595360638540L;
	
	public ErrorException (final Exception exception) {	
		
		super(Response.status(Response.Status.BAD_REQUEST)
	             .entity("{\"error\":\"" + exception.getMessage() + "\"}").type(MediaType.APPLICATION_JSON_TYPE).build());
	}
	
	
}
