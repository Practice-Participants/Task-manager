package domain.mapper;

import domain.dto.TaskDto;
import domain.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);

    Task toTask(TaskDto dto);
}
