package nl.ing.cla.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.Closeable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * User: adriaan
 * Date: 22-11-2013
 * Time: 14:35
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RoaringLion implements Closeable {
	private static final String BASE_URL = "http://172.16.35.237:5000/benki/api/v0/";
	private static final Charset CHARSET = Charset.forName("UTF-8");

	private final ExecutorService executorService = Executors.newSingleThreadExecutor();
	private final CloseableHttpClient client = HttpClients.createDefault();

	public void deposit(double amount) {
		Map<String, Object> map = new HashMap<>();
		map.put("amount", amount);

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			post("deposit", objectMapper.writeValueAsString(map));
		} catch (IOException e) {
			throw new RuntimeException("Error sending message", e);
		}
	}

	public static void main(String[] args) {
		new RoaringLion().deposit(42.5);
	}

	private void post(final String suffix, final String bodyJson) {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				String url = BASE_URL + suffix;
				HttpPost post = new HttpPost(url);
				post.setHeader("Content-Type", "application/json");
				post.setEntity(new StringEntity(bodyJson, CHARSET));

				CloseableHttpResponse response = null;
				try {
					response = client.execute(post);
					System.out.println("Status code from " + url + ": " + response.getStatusLine().getStatusCode());
				} catch (IOException e) {
					e.printStackTrace();  // TODO
				} finally {
					if (response != null) {
						try {
							response.close();
						} catch (IOException e) {
							e.printStackTrace();  // TODO
						}
					}
				}
			}
		});
	}

	@Override
	@PreDestroy
	public void close() throws IOException {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace(); // TODO
		}
		executorService.shutdown();
	}
}
