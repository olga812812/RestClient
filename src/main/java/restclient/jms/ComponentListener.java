package restclient.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Service;
import restclient.domain.Component;


@Slf4j
@Service
public class ComponentListener {
    @JmsListener(destination="component")
    public void getComponent(Component component) {
        log.info("ComponentListener received component: " + component);
    }
}
