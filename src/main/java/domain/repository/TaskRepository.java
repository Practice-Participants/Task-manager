package domain.repository;

import domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Lorden on 19.02.2025
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByReporterID(Long userId);
}
