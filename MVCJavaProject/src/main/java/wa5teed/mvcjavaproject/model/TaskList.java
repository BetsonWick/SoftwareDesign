package wa5teed.mvcjavaproject.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
