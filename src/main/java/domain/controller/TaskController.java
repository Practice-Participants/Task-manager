package domain.controller;

import domain.dto.TaskDto;
import domain.entity.Task;
import domain.mapper.TaskMapper;
import domain.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lorden on 19.02.2025
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/task")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper mapper;

    @PostMapping("/create")
    public TaskDto create(@RequestBody TaskDto dto){
        Task task = taskService.creteTask(dto);
        return mapper.toDto(task);
    }

    @GetMapping("/{id}")
    public Task read(@PathVariable Long id){
        return taskService.findById(id);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task task =taskService.updateTask(taskDto, id);
        return mapper.toDto(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}
