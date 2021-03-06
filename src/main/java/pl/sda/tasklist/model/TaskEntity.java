package pl.sda.tasklist.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long uuid;
    private String name;
    private String description;
    private boolean isDone;
    private Priority priority;
}
