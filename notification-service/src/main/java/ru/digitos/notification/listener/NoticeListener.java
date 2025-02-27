package ru.digitos.notification.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class NoticeListener {
    //@KafkaListener(topics = "notice-events")
    @KafkaListener(topics = "task-service")
    public void listen(Map task){
        log.info("Recived title: {}", task.get("title"));
        log.info("Recived userId: {}", task.get("userId"));
        log.info("Recived status: {}", task.get("status"));
    }
}
