package restclient.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import restclient.domain.Component;
import restclient.jms.JmsComponentMessagingService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestClientController {
    @Value("${api2.url.base.path}")
    private String url2BasePath;
    @Value("${api1.url.base.path}")
    private String url1BasePath;
    private RestTemplate restTemplate;
    private Traverson traverson;
    private JmsComponentMessagingService jmsMessagingService;

    public RestClientController(RestTemplate restTemplate, Traverson traverson, JmsComponentMessagingService jmsMessagingService) {

        this.restTemplate = restTemplate;
        this.traverson = traverson;
        this.jmsMessagingService=jmsMessagingService;
    }

    @GetMapping("/all")
    public ResponseEntity<Component[]> getAllComponents(){
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        log.info("restTEmplate" + restTemplate);
        return restTemplate.getForEntity(url2BasePath+"/components", Component[].class);

    }

    @GetMapping("/allByTraverson")
    public Collection<Component> getAllComponentsByTraverson(){
        ParameterizedTypeReference<CollectionModel<Component>> componentType =
                new ParameterizedTypeReference<CollectionModel<Component>>() {};
        CollectionModel<Component> components = traverson.follow("components").toObject(componentType);
        return components.getContent();

    }

    @GetMapping("/components/{id}")
    public ResponseEntity<Component> getAllComponents(@PathVariable Long id){
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        log.info("restTEmplate" + restTemplate);
        return restTemplate.getForEntity(url2BasePath+"/components/"+id, Component.class);
    }

    @GetMapping("/convertAndSend/component/{id}")
    public String sendComponent(@PathVariable Long id){
                 Component component = restTemplate.getForObject(url2BasePath+"/components/"+id, Component.class);
                 jmsMessagingService.sendComponent(component);
                 return "Component with id="+id+" was sent to Artemis";
    }

    @PostMapping(path = "/saveComponent", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveComponent(@RequestBody Component component){
        return restTemplate.postForObject(url1BasePath+"/components", component, String.class);
    }

    @PutMapping(path="/updateComponent/{id}", consumes = "application/json")
    public void updateComponent(@PathVariable Long id, @RequestBody Component component){
        restTemplate.put(url1BasePath+"/components/"+id, component, id);

    }

    @PatchMapping(path="/patchComponent/{id}", consumes = "application/json")
    public String patchComponent(@PathVariable Long id, @RequestBody Component component){
       return  restTemplate.patchForObject(url1BasePath+"/components/"+id, component, String.class);
    }

    @DeleteMapping("/deleteComponent/{id}")
    public void deleteComponent(@PathVariable Long id) {
        restTemplate.delete(url1BasePath+"/components/"+id, id);
        }

}
