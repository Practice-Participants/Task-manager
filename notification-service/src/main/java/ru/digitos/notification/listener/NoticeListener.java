package ru.digitos.notification.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.digitos.notification.model.NoticeEntity;
import ru.digitos.notification.repository.NoticeRepository;
import java.util.Map;

@Slf4j
@Component
public class NoticeListener {

    private final NoticeRepository noticeRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NoticeListener(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @KafkaListener(topics = "task-service", groupId = "notification-service")
    public void listen(ConsumerRecord<String, String> record){
        //log.info("Recived title: {}", task.get("title"));
        //log.info("Recived userId: {}", task.get("userId"));
        //log.info("Recived status: {}", task.get("status"));

        try {
            Map<String, String> taskData = objectMapper.readValue(record.value(), Map.class);
            String userId = taskData.get("userId");
            String title = taskData.get("title");
            String description = taskData.get("description");
            String taskStatus = taskData.get("status");

            String eMail = "admin@digit-os.ru";

            NoticeEntity noticeEntity = new NoticeEntity(Long.valueOf(userId), eMail, title, description, taskStatus);
            noticeRepository.save(noticeEntity);
            System.out.println("Notification " + title + " create.");
        } catch (Exception e) {
            System.err.println("Error processing Kafka message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
