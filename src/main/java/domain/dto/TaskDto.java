package domain.dto;

import domain.entity.TaskPriority;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Lorden on 19.02.2025
 */
@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long reporterID;
    private Long assigneeID;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String tag;
    private TaskPriority priority;
}
