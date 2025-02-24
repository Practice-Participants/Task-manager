package domain.mapper;

import domain.dto.TaskDTO;
import domain.entity.Task;
import domain.entity.TaskStatus;
import domain.model.TaskModel;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapper {
    public TaskDTO toDto(Task task){
        return TaskDTO.builder()
                .reporterID(task.getReporterID())
                .assigneeID(task.getAssigneeID())
                .title(task.getTitle())
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .tag(task.getTag())
                .build();
    }

    public Task toTask(TaskDTO dto){
        return Task.builder()
                .reporterID(dto.getReporterID())
                .assigneeID(dto.getAssigneeID())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .deadline(dto.getDeadline())
                .status(TaskStatus.TO_DO)
                .priority(dto.getPriority())
                .tag(dto.getTag())
                .build();
    }
    public Task toTask(TaskModel model){
        return Task.builder()
                .assigneeID(model.getAssigneeID())
                .title(model.getTitle())
                .description(model.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(model.getStatus())
                .priority(model.getPriority())
                .tag(model.getTag())
                .build();
    }

    public TaskModel toModel(Task task){
        return TaskModel.builder()
                .assigneeID(task.getAssigneeID())
                .title(task.getTitle())
                .status(task.getStatus())
                .priority(task.getPriority())
                .tag(task.getTag())
                .build();
    }
}
