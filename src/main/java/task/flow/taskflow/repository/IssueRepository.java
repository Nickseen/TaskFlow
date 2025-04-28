package task.flow.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task.flow.taskflow.model.Issue;
import task.flow.taskflow.model.enums.IssueStatus;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Query("SELECT i FROM Issue i JOIN i.assignee a JOIN a.participant p WHERE p.email = :email")
    List<Issue> findByAssigneeEmail(@Param("email") String email);

    @Query("SELECT i FROM Issue i JOIN i.reporter r JOIN r.participant p WHERE p.email = :email")
    List<Issue> findByReporterEmail(@Param("email") String email);

    @Query("SELECT i FROM Issue i JOIN i.project p WHERE p.name = :projectName AND i.status = :status")
    List<Issue> findByProjectNameAndStatus(
            @Param("projectName") String projectName,
            @Param("status") IssueStatus status
    );

    void deleteIssueById(Long id);
}