package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
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

    @RequestMapping("/")
    public String home() {
        return "你好-POC";
    }

}
