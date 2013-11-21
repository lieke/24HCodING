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
	
	private static String objectToJSONString(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(obj);	
		
	}
	
	public static void saveParent(String identifier, Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		final String fileName = DATA_LOCATION + identifier + ".txt";
		PrintWriter out = new PrintWriter(new File(fileName), "UTF-8");
		out.write(objectToJSONString(obj));
		out.flush();
		out.close();

	}
}
