package banquemisr.challenge05.model;

import lombok.*;
import org.thymeleaf.context.Context;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {

    private String subject;
    private String[] to;
    private String from;
    private String body;
    private String templateName;
    private Context context;
    private String[] bcc;
    private String replyTo;
}
