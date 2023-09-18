package uz.pdp.hrmanagement.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;

import java.util.Map;
import java.util.Set;

@Component
public class AppEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishUserCreated(final String email, final String code) {
        applicationEventPublisher.publishEvent(new UserCreatedEvent(this, email, code));
    }

    public void publishUserAddedToTask(final User taskTakenUser,final Task task) {
        applicationEventPublisher.publishEvent(new UserAddedToTaskEvent(this, taskTakenUser, task));
    }

    public void publishUserSetTaskStatusEvent(final Task task, final User userWhoSet) {
        applicationEventPublisher.publishEvent(new UserSetTaskStatusEvent(this, task, userWhoSet));
    }

    public void publishTaskDeadlineExpiredEvent(final Map<User, Set<Task>> tasks) {
        applicationEventPublisher.publishEvent(new TaskDeadlineExpiredEvent(this, tasks));
    }
}
