package restclient.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import restclient.domain.Component;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestClientController {
    @Value("${api2.url.base.path}")
    private String url2BasePath;
    @Value("${api1.url.base.path}")
    private String url1BasePath;
    private RestTemplate restTemplate;

    public RestClientController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/all")
    public ResponseEntity<Component[]> getAllComponents(){
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        log.info("restTEmplate" + restTemplate);
        return restTemplate.getForEntity(url2BasePath+"/components", Component[].class);

    }

    @GetMapping("/components/{id}")
    public ResponseEntity<Component> getAllComponents(@PathVariable Long id){
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        log.info("restTEmplate" + restTemplate);
        return restTemplate.getForEntity(url2BasePath+"/components/"+id, Component.class);
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
