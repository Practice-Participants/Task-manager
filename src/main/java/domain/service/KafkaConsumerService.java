package domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.LogEntry;
import domain.repository.LogRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final LogRepository logRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumerService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @KafkaListener(topics = "audit-log-topic", groupId = "audit-log-group")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            JsonNode jsonNode = objectMapper.readTree(record.value());
            String eventType = jsonNode.get("eventType").asText();
            String eventData = jsonNode.get("eventData").toString();

            LogEntry logEntry = new LogEntry(eventType, eventData);
            logRepository.save(logEntry);

            System.out.println("Received and saved: " + record.value());
        } catch (Exception e) {
            System.err.println("Error processing Kafka message: " + e.getMessage());
        }
    }
}
