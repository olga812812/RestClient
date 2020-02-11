package restclient;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Scanner;

@SpringBootApplication
public class RestClientApplication {
    @Value("${api1.url.base.path}")
    private String apiBasePath;


    public static void main (String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
     }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Traverson traverson() {
        Traverson traverson = new Traverson(URI.create(apiBasePath), MediaTypes.HAL_JSON);
        return traverson;
    }

}
