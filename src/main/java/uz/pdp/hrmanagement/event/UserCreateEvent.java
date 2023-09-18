package uz.pdp.hrmanagement.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserCreateEvent extends ApplicationEvent {
    private final String email;
    private final String code;

    public UserCreateEvent(Object source, String email, String code) {
        super(source);
        this.email = email;
        this.code = code;
    }
}
