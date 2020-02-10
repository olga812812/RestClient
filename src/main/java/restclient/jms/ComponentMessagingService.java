package restclient.jms;

import restclient.domain.Component;

public interface ComponentMessagingService {

    void sendComponent(Component component);
}
