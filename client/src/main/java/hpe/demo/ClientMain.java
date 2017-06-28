package hpe.demo;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientMain {
	static Logger log = Logger.getLogger("ClientMain");

	public static void main(String[] args) {
		SpringApplication.run(ClientMain.class, args);

		if (args.length < 3) {
			log.warning("Please input 3 arguments: appkey, username, password");
			return;
		}

		String appkey = args[0];
		String username = args[1];
		String password = args[2];

		String token = getToken(appkey, username, password);
		log.info("Get token: " + token);

		if(token.equals("")){
			log.info("Get token failed for user: " + username);
			return;
		}
			
		String result = invoke(appkey, token, "http://localhost:8080/hello");
		log.info("Invoke API: " + result);
	}

	private static String getToken(String appkey, String username, String password) {
		RestTemplate rt = new RestTemplate();
		String url = "http://localhost:8080?appkey=" + appkey + "&username=" + username + "&password=" + password;
		Result r = rt.getForObject(url, Result.class);

		return r.token;
	}

	private static String invoke(String appkey, String token, String apiUrl) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("appkey", appkey);
		headers.add("token", token);
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, headers);

		String result = restTemplate.postForObject(apiUrl, requestEntity, String.class);
		return result;
	}
}

class Result {
	public String bizDesc;
	public String token;
}
