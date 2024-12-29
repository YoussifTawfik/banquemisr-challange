package banquemisr.challenge05.integration;

import banquemisr.challenge05.model.EmailModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;


@Service
@Slf4j
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(EmailModel email) {
        String templateName = email.getTemplateName();
        String replyTo = email.getReplyTo();
        String[] toAddress = email.getTo();
        String[] bccAddress = email.getBcc();
        if (StringUtils.isEmpty(templateName)) {
            log.info("sending email template {}", templateName);
        }
        if (null != toAddress && toAddress.length > 0) {
            log.info("sending email to {}", (Object) toAddress);
        }
        if (null != bccAddress && bccAddress.length > 0) {
            log.info("sending email bcc {}", (Object) bccAddress);
        }
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            if (null != toAddress && toAddress.length > 0) {
                messageHelper.setTo(toAddress);
            }
            if (null != bccAddress && bccAddress.length > 0) {
                messageHelper.setBcc(bccAddress);
            }
            messageHelper.setFrom(email.getFrom());
            messageHelper.setSubject(email.getSubject());
            if (StringUtils.isEmpty(templateName)) {
                messageHelper.setText(email.getBody());
            } else {
                String emailBody = templateEngine.process(templateName, email.getContext());
                messageHelper.setText(emailBody, true);
            }
            if (StringUtils.isNotEmpty(replyTo)) {
                messageHelper.setReplyTo(replyTo);
            }
            mailSender.send(message);
            if (null != toAddress && toAddress.length > 0) {
                log.info("successfully sent email to {}", (Object) toAddress);
            }
            if (null != bccAddress && bccAddress.length > 0) {
                log.info("successfully sent email bcc {}", (Object) bccAddress);
            }

        } catch (MessagingException e) {
            if (null != toAddress && toAddress.length > 0) {
                log.error("could not send email to {} ", toAddress, e);
            }
            if (null != bccAddress && bccAddress.length > 0) {
                log.error("could not send email to {} ", bccAddress, e);
            }
        }
    }
}