package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class HelloMain {

    public static void main(String[] args) {
        SpringApplication.run(HelloMain.class, args);
    }

}

@RestController
class HelloController {
	private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/")
    public String home() {
    	LOG.info("/");
        return "你好-POC!";
    }

}
