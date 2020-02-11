package restclient.jms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import restclient.domain.Component;

@Service
public class JmsComponentMessagingService implements ComponentMessagingService{
    private JmsTemplate jms;

    @Autowired
    public JmsComponentMessagingService(JmsTemplate jms){
        this.jms = jms;
        }

    @Override
    public void sendComponent(Component component) {
        MessagePostProcessor addComponentSource = message->{message.setStringProperty("COMPONENT_SOURCE","REST"); return message;};
            jms.convertAndSend("artemis", component, addComponentSource);
    }
}
