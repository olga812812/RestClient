package restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class RestClientApplication {

    public static void main (String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
     }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
