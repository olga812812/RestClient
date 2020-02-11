package restclient.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class componentListener {
    @JmsListener(destination="component")
    public void getComponent(Component component) {
        log.info("ComponentListener received component: " + component);
    }
}
