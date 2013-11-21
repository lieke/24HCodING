package nl.ing.cla.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class CLAUtil {	
	
	private final static String DATA_LOCATION = "c:\\temp\\";
	
	private static String objectToJSON(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(obj);	
		
	}
	
	
	public static void saveFile(String identifier, Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		final String fileName = getFileName(identifier);
		PrintWriter out = new PrintWriter(new File(fileName), "UTF-8");
		out.write(objectToJSON(obj));
		out.flush();
		out.close();

	}
	
	public static String getFileName(final String identifier) {
		return  DATA_LOCATION + identifier + ".json";
	}
}
