package wa5teed.mvcjavaproject.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import wa5teed.mvcjavaproject.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByListId(Long listId);

    @Transactional
    void deleteByListId(Long listId);
}
