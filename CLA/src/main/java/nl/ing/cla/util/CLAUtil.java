package nl.ing.cla.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicLong;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.stereotype.Component;

@Component
public class CLAUtil implements Closeable {

	private final File dataLocation = createTempDir();
	
	private AtomicLong ID = new AtomicLong();
	
	public long giveMeAUniqueId() {
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
			return file;
		} catch (IOException e) {
			// TODO handle this more properly
			throw new RuntimeException("Could not create temp dir.", e);
		}
	}

	@Override
	public void close() throws IOException {
		FileUtils.deleteDirectory(dataLocation);
	}
}
