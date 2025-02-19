package domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * Created by Lorden on 19.02.2025
 */
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long userId;
    private String textTask;

}
