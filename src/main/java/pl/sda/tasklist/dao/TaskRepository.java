package pl.sda.tasklist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.tasklist.model.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
