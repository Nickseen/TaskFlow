package task.flow.taskflow.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String role;


}
