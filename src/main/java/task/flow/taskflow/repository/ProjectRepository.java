package task.flow.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.flow.taskflow.model.Participant;
import task.flow.taskflow.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findProjectsByCreator(Participant email);
}
