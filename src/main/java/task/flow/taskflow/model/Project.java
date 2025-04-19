package task.flow.taskflow.model;

import jakarta.persistence.*;
import lombok.Data;
import task.flow.taskflow.utility.PasswordUtil;

@Data
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String project_name;

    @Column(name = "password_hash")
    private String passwordHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private Participant creator;

    public void setPassword(String password) {
        this.passwordHash = PasswordUtil.hashPassword(password);
    }
}
