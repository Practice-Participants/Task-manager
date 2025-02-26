package domain.model;

import domain.entity.TaskPriority;
import domain.entity.TaskStatus;
import lombok.*;

/**
 * Created by Lorden on 22.02.2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskModel {
    private Long assigneeID;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private String tag;
}
