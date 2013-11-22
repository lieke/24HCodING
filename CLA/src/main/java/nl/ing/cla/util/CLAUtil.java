package nl.ing.cla.util;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CLAUtil implements Closeable {

	private final File dataLocation = createTempDir();
	
	private static AtomicLong ID = new AtomicLong();
	
	public static long giveMeAUniqueId() {
		return ID.incrementAndGet();
	}

	private String objectToJSON(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		return ow.writeValueAsString(obj);

	}


	public void saveFile(String identifier, Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		final String fileName = getFileName(identifier);
		PrintWriter out = new PrintWriter(new File(fileName), "UTF-8");
		out.write(objectToJSON(obj));
		out.flush();
		out.close();

	}

	public String getFileName(final String identifier) {
		return new File(dataLocation, identifier + ".json").getAbsolutePath();
	}

	private static File createTempDir() {
		try {
			File file = Files.createTempDirectory("CLA").toFile();
			file.deleteOnExit(); // a cowardly attempt, as there will probably be files in there
			System.out.println("Using data directory: " + file.getAbsolutePath());
			return file;
		} catch (IOException e) {
			// TODO handle this more properly
			throw new RuntimeException("Could not create temp dir.", e);
		}
	}

	@Override
	@PreDestroy
	public void close() throws IOException {
		System.out.println("Removing data directory: " + dataLocation.getAbsolutePath());
		FileUtils.deleteDirectory(dataLocation);
	}
}
