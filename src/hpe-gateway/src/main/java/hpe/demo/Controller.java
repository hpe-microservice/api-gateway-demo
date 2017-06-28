package hpe.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
	private static final Logger LOG = LoggerFactory.getLogger(Controller.class);
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/open/token", method = RequestMethod.GET)
	public TokenResult token(@RequestParam("appkey") String appkey, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		TokenResult r = new TokenResult();
		if (!varifyUser(appkey, username, password)) {
			r.bizDesc = "varify failed for user: " + username;
			r.token = "";
			return r;
		}
		r.bizDesc = "Succeed";
		r.token = TokenManager.genToken(appkey, username, password);
		return r;
	}

	private boolean varifyUser(String appkey, String username, String password) {
		String url = "http://auth/check?appkey=" + appkey + "&username=" + username + "&password=" + password;
		Boolean r = false;
		try {
			r = this.restTemplate.getForObject(url, Boolean.class);
		} catch (Exception e) {
			LOG.warn(e.toString());
			return false;
		}
		return r;
	}
}

class TokenResult {
	public String bizDesc;
	public String token;
}