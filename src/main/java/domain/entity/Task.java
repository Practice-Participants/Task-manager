package domain.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


/**
 * Created by Lorden on 19.02.2025
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String textTask;
    private LocalDateTime createTime;
    private LocalDateTime deadline;
}
