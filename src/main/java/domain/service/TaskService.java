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
import java.util.HashMap;

/**
 * Created by Lorden on 19.02.2025
 */
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper mapper;
    private final KafkaTemplate<String, HashMap<String, String>> kafkaTemplate;

    @Transactional
    public Task creteTask(TaskDTO dto) {
        Task task = mapper.toTask(dto);
        taskRepository.save(task);

        HashMap<String, String> taskMap = mapper.toMap(task);
        taskMap.put("event", "TASK_CREATED");

        kafkaTemplate.send("task-service", taskMap);

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

        HashMap<String, String> taskMap = mapper.toMap(task);
        taskMap.put("event", "TASK_UPDATE");

        kafkaTemplate.send("task-service", taskMap);

        return taskRepository.save(updateTask);
    }

    public void deleteTask(Long id) {
        HashMap<String, String> taskMap = new HashMap<>();
        taskMap.put("event", "TASK_DELITE");

        kafkaTemplate.send("task-service", taskMap);

        taskRepository.deleteById(id);
    }

    public Task findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(NoTaskException::new);
        HashMap<String, String> taskMap = mapper.toMap(task);
        taskMap.put("event", "TASK_READ");

        return task;
    }

}
