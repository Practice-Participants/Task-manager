package domain.service;

import domain.dto.TaskDTO;
import domain.entity.Task;
import domain.exception.NoTaskException;
import domain.mapper.TaskMapper;
import domain.model.TaskModel;
import domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Lorden on 19.02.2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper mapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public Task creteTask(TaskDTO dto) {
        Task task = mapper.toTask(dto);
        taskRepository.save(task);
        log.info("Create task, ID:" + task.getId());
        kafkaTemplate.send("task-event", String.format("Task created with ID: %s", task.getId()));
        return task;
    }

    @Transactional
    public Task updateTask(TaskModel model, Long id) {
        Task task = taskRepository.findById(id).orElseThrow(NoTaskException::new);
        Task updateTask = mapper.toTask(model);

        updateTask.setId(task.getId());
        updateTask.setReporterID(task.getReporterID());
        updateTask.setCreatedAt(task.getCreatedAt());
        updateTask.setUpdatedAt(LocalDateTime.now());
        updateTask.setDeadline(task.getDeadline());
        updateTask.setStatus(task.getStatus());

        log.info("Update task, ID:" + updateTask.getId());
        kafkaTemplate.send("task-event", String.format("Update task with ID: %s", updateTask.getId()));

        return taskRepository.save(updateTask);
    }

    public void deleteTask(Long id) {
        log.info("Update task, ID:" + id);
        kafkaTemplate.send("task-event", String.format("Delete task with ID: %s", id));

        taskRepository.deleteById(id);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(NoTaskException::new);
    }

}
