package domain.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "logs")
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;
    private String eventData;
    private LocalDateTime timestamp = LocalDateTime.now();

    public LogEntry() {}

    public LogEntry(String eventType, String eventData) {
        this.eventData = eventData;
        this.eventType = eventType;
    }
}
