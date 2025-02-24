package ru.digitos.notification_service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SimpleNoticeManager implements NoticeManager {
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    public void placeOrder(Notice notice) {
        // Проводим бизнес-расчеты...
        // Вызываем взаимодействующие объекты для сохранения заказа...
        /// Создаем безопасную для потока "копию" шаблонного сообщения и настраиваем ее
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        //msg.setTo(notice.getCustomer().getEmailAddress());
        msg.setTo("test@mail.ru");
        //msg.setText(
        //        "Dear " + notice.getCustomer().getFirstName()
        //                + notice.getCustomer().getLastName()
        //                + ", thank you for placing order. Your order number is "
        //                + notice.getOrderNumber());
        msg.setText(
                "Dear " + "FirstName"
                        + "LastName"
                        + ", thank you for placing order. Your order number is "
                        + "NoticeNumber");
        try {
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            // просто заносим в журнал и двигаемся дальше...
            System.err.println(ex.getMessage());
        }
    }
}
