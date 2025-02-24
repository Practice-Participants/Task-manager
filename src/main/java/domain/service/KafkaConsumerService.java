package domain.service;

import domain.model.LogEntry;
import domain.repository.LogRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
@Service
public class KafkaConsumerService {
    private final LogRepository logRepository;
    public KafkaConsumerService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
    @KafkaListener(topics = "audit-log-topic", groupId = "audit-log-group")
    public void consume(ConsumerRecord<String, String> record) {
        LogEntry logEntry = new LogEntry(record.key(), record.value());
        logRepository.save(logEntry);
        System.out.println("Received: " + record.value());
    }
}
