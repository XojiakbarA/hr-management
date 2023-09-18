package uz.pdp.hrmanagement.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;

@Getter
public class UserSetTaskStatusEvent extends ApplicationEvent {
    private final Task task;
    private final User userWhoSet;
    public UserSetTaskStatusEvent(Object source, Task task, User userWhoSet) {
        super(source);
        this.task = task;
        this.userWhoSet = userWhoSet;
    }
}
