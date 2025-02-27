package domain.controller;

import domain.dto.TaskDTO;
import domain.entity.Task;
import domain.mapper.TaskMapper;
import domain.model.TaskModel;
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
    public TaskDTO create(@RequestBody TaskDTO dto){
        Task task = taskService.creteTask(dto);
        return mapper.toDto(task);
    }

    @GetMapping("/{id}")
    public Task read(@PathVariable Long id){
        return taskService.findById(id);
    }

    @PutMapping("/{id}")
    public TaskModel updateTask(@PathVariable Long id, @RequestBody TaskModel model) {
        Task task = taskService.updateTask(model, id);
        return mapper.toModel(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

}
