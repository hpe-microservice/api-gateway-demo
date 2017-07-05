package hpe.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hpe.demo.auth.entity.Auth;
import hpe.demo.auth.service.AuthDaoService;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthMain {

	public static void main(String args[]) {
		SpringApplication.run(AuthMain.class, args);
	}
}

@RestController
class ServiceInstanceRestController {
	@Autowired
	AuthDaoService service;

	@RequestMapping("/check")
	public boolean check(@RequestParam("appkey") int appkey, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		Auth a = service.findByAppkeyAndUsername(appkey, username);
		if (a == null) {
			return false;
		}
		return a.getPassword().equals(password);
	}

	@RequestMapping("/query-app/{appkey}")
	public String queryApp(@PathVariable int appkey) {
		Auth a = service.findByAppkey(appkey);
		if (a == null) {
			return "";
		}
		return a.getAppname();
	}

	@RequestMapping("/save")
	public Auth save(@RequestParam("appkey") int appkey, @RequestParam("appname") String appname,
			@RequestParam("username") String username, @RequestParam("password") String password) {
		Auth au = new Auth(appkey, appname, username, password);
		return service.save(au);
	}

}
