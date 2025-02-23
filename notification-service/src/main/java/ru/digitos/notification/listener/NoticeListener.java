package ru.digitos.notification.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NoticeListener {
    @KafkaListener(topics = "notice-events")
    public void listen(String payload){
        log.info("Recived notice: {}", payload);
    }
}
