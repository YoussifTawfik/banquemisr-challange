package banquemisr.challenge05.service.impl;

import banquemisr.challenge05.entity.Todo;
import banquemisr.challenge05.integration.EmailSender;
import banquemisr.challenge05.model.EmailModel;
import banquemisr.challenge05.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Profile("task-reminder-email")
@Service
@RequiredArgsConstructor
@Slf4j
public class TaskReminderScheduler {

    private final EmailSender emailSender;
    private final TodoService todoService;

    @Value("${email.task.reminder-subject}")
    private String taskReminderEmailSubject;

    @Value("${email.from.task.management}")
    private String fromTaskManagement;


    // At 12:00am each day
    @Scheduled(cron = "0 0 0 * * *")
    public void initiate(){
        log.info("Run Task Reminder Scheduler......");
        List<Todo> todoList = todoService.fetchTodosWithinPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        todoList.forEach(todo -> {
            log.info("Sending email to: {}", todo.getUser().getEmail());
           sendEmail(todo.getUser().getEmail(), todo.getUser().getName(), todo.getTitle(), todo.getDueDate());
        });
    }


    private void sendEmail(String toEmail, String username, String taskTitle, LocalDateTime dueDate) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("title", taskTitle);
        context.setVariable("dueDate", dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        emailSender.sendEmail(EmailModel.builder()
                .from(fromTaskManagement)
                .to(new String[]{toEmail})
                .context(context)
                .subject(taskReminderEmailSubject)
                .templateName("task-reminder-email-template")
                .build());
    }
}
