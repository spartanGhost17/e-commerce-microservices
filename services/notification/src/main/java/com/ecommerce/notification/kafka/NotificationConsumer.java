package com.ecommerce.notification.kafka;

import com.ecommerce.notification.enums.NotificationType;
import com.ecommerce.notification.kafka.order.OrderConfirmation;
import com.ecommerce.notification.kafka.payment.PaymentConfirmation;
import com.ecommerce.notification.model.Notification;
import com.ecommerce.notification.repository.NotificationRepository;
import com.ecommerce.notification.service.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.ecommerce.notification.constants.ApplicationConstants.ORDER_TOPIC;
import static com.ecommerce.notification.constants.ApplicationConstants.PAYMENT_TOPIC;
import static com.ecommerce.notification.enums.NotificationType.ORDER_CONFIRMATION;
import static com.ecommerce.notification.enums.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = PAYMENT_TOPIC)
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from payment-topic:: %s", PAYMENT_TOPIC));
        notificationRepository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        //send email
        String customerName = paymentConfirmation.customerFirstName() + " " + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = ORDER_TOPIC)
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consuming the message from order-topic:: {}", ORDER_TOPIC);
        notificationRepository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );


        // send email
        String customerName = orderConfirmation.customer().firstName() + " " + orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

}
