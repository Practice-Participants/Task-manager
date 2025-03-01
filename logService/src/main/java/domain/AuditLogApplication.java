package domain;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableKafka
public class AuditLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuditLogApplication.class, args);
    }
}
