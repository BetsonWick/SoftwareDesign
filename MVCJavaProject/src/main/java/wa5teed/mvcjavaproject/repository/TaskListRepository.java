package wa5teed.mvcjavaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wa5teed.mvcjavaproject.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
