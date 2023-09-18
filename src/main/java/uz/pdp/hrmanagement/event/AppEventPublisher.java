package uz.pdp.hrmanagement.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AppEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishUserCreate(final String email, final String code) {
        applicationEventPublisher.publishEvent(new UserCreateEvent(this, email, code));
    }
}
