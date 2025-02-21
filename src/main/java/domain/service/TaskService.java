package domain.service;

import domain.dto.TaskDto;
import domain.entity.Task;
import domain.entity.TaskStatus;
import domain.exception.NoTaskException;
import domain.mapper.TaskMapper;
import domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Lorden on 19.02.2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper mapper;

    @Transactional
    public Task creteTask(TaskDto dto){
        Task task = mapper.toTask(dto);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setStatus(TaskStatus.TO_DO);
        return task;
    }

    public Task updateTask(TaskDto dto, Long id){
        Task task = taskRepository.findById(id).orElseThrow(NoTaskException::new);
        Task intermediateEntry = mapper.toTask(dto);

        intermediateEntry.setId(task.getId());
        task.setCreatedAt(task.getCreatedAt());
        task.setUpdatedAt(task.getUpdatedAt());
        task.setStatus(task.getStatus());

        return taskRepository.save(intermediateEntry);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow(NoTaskException::new);
    }

}
