package uz.pdp.hrmanagement.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;

@Component
public class AppEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishUserCreated(final String email, final String code) {
        applicationEventPublisher.publishEvent(new UserCreatedEvent(this, email, code));
    }

    public void publishUserAddedToTask(User taskTakenUser, User taskGivenUser, Task task) {
        applicationEventPublisher.publishEvent(new UserAddedToTaskEvent(this, taskTakenUser, taskGivenUser, task));
    }
}
