package uz.pdp.hrmanagement.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;

@Getter
public class UserAddedToTaskEvent extends ApplicationEvent {
    private final User taskTakenUser;
    private final Task task;

    public UserAddedToTaskEvent(Object source, User taskTakenUser, Task task) {
        super(source);
        this.taskTakenUser = taskTakenUser;
        this.task = task;
    }
}
