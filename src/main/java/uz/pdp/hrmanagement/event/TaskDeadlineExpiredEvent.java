package uz.pdp.hrmanagement.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;

import java.util.Map;
import java.util.Set;

@Getter
public class TaskDeadlineExpiredEvent extends ApplicationEvent {
    private final Map<User, Set<Task>> tasks;

    public TaskDeadlineExpiredEvent(Object source, Map<User, Set<Task>> tasks) {
        super(source);
        this.tasks = tasks;
    }
}
