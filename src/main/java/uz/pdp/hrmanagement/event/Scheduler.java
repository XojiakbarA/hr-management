package uz.pdp.hrmanagement.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.pdp.hrmanagement.entity.Task;
import uz.pdp.hrmanagement.entity.User;
import uz.pdp.hrmanagement.entity.enums.Status;
import uz.pdp.hrmanagement.service.TaskService;

import java.util.*;

@Component
public class Scheduler {
    @Autowired
    private AppEventPublisher appEventPublisher;
    @Autowired
    private TaskService taskService;

    @Scheduled(cron = "@hourly")
    public void checkDeadline() {
        List<Task> expiredTasks = taskService.findAllByDeadlineAfterAndStatusNot(new Date(), Status.COMPLETED);
        Map<User, Set<Task>> groupByGivenUser = new HashMap<>();
        System.out.println("schedule");
        expiredTasks.forEach(oTask -> {
            groupByGivenUser.put(oTask.getUser(), new HashSet<>());
            List<Task> filteredByUser = expiredTasks.stream().filter(t -> t.getUser().getId().equals(oTask.getUser().getId())).toList();
            groupByGivenUser.get(oTask.getUser()).addAll(filteredByUser);
        });
        appEventPublisher.publishTaskDeadlineExpiredEvent(groupByGivenUser);
    }
}
