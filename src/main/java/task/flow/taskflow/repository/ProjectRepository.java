package task.flow.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task.flow.taskflow.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p JOIN p.creator c WHERE c.email = :email")
    List<Project> findByCreatorEmail(@Param("email") String email);
    void deleteByName(String project_name);
}
