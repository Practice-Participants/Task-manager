package domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.LogEntry;
import domain.repository.LogRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class KafkaConsumerService {
    private final LogRepository logRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumerService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

@KafkaListener(topics = "task-service", groupId = "audit-log-group")
public void consume(ConsumerRecord<String, String> record) {
    try {
        Map<String, String> taskData = objectMapper.readValue(record.value(), Map.class);

        String eventType = taskData.get("event");
        String userId = taskData.get("userId");
        String title = taskData.get("title");
        String status = taskData.get("status");

        String logMessage;
        switch (eventType) {
            case "TASK_CREATED":
                logMessage = String.format("Task created by user %s: '%s' with status %s", userId, title, status);
                break;
            case "TTASK_UPDATE":
                logMessage = String.format("Task updated by user %s: '%s' now has status %s", userId, title, status);
                break;
            case "TASK_DELITE":
                logMessage = String.format("Task deleted by user %s: '%s' now has status %s", userId, title, status);
                break;
            case "TASK_READ":
                logMessage = String.format("Task viewed by user %s: '%s' with status %s", userId, title, status);
                break;
            default:
                logMessage = "Unknown task event";
        }

        LogEntry logEntry = new LogEntry(eventType, logMessage);
        logRepository.save(logEntry);

        System.out.println("Logged: " + logMessage);
    } catch (Exception e) {
        System.err.println("Error processing Kafka message: " + e.getMessage());
        e.printStackTrace();
    }
}


}
