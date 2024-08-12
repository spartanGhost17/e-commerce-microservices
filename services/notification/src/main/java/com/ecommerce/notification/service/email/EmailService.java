package com.ecommerce.notification.service.email;

import com.ecommerce.notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ecommerce.notification.constants.ApplicationConstants.SENDER_BUSINESS_EMAIL;
import static com.ecommerce.notification.enums.EmailTemplates.ORDER_CONFIRMATION;
import static com.ecommerce.notification.enums.EmailTemplates.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(String destinantionEmail, String customerName, BigDecimal amount, String orderReference) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        messageHelper.setFrom(SENDER_BUSINESS_EMAIL);
        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount.toString());
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinantionEmail);

            mailSender.send(mimeMessage);

            log.info("INFO - Email sent successfully to {} with template {}", destinantionEmail, templateName);

        } catch (MessagingException exception) {
            log.warn("WARNING - Cannot Send email to {}", destinantionEmail);
            log.error("ERROR - Cannot Send email to {}", destinantionEmail, exception);
        }
    }

    @Async
    public void sendOrderConfirmationEmail(String destinantionEmail, String customerName, BigDecimal amount, String orderReference, List<Product> products) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        messageHelper.setFrom(SENDER_BUSINESS_EMAIL);
        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount.toString());
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinantionEmail);

            mailSender.send(mimeMessage);

            log.info("INFO - (Order Confirmation) Email sent successfully to {} with template {}", destinantionEmail, templateName);

        } catch (MessagingException exception) {
            log.warn("WARNING - (Order Confirmation) Cannot Send email to {}", destinantionEmail);
            log.error("ERROR - (Order Confirmation) Cannot Send email to {}", destinantionEmail, exception);
        }
    }

    /* use completable future to do same job as @Async
    public CompletableFuture<Void> sendEmail(String to, String subject, String text) {
        return CompletableFuture.runAsync(() -> {
            // Email sending logic
        });
    }*/
}
