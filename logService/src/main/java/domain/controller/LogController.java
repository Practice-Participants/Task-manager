package domain.controller;

import domain.model.LogEntry;
import domain.repository.LogRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {
    private final LogRepository logRepository;
    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping
    public List<LogEntry> getAllLogs() {
        return logRepository.findAll();
    }
}
