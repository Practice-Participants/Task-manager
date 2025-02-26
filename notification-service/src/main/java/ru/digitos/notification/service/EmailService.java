package ru.digitos.notification.service;

public interface EmailService {

    void sendSimpleEmail(String toAddress, String subject, String message);
}