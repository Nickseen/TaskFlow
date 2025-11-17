package task.flow.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task.flow.taskflow.model.Member;
import task.flow.taskflow.model.enums.ProjectRole;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m JOIN m.project p WHERE p.name = :projectName")
    List<Member> findByProjectName(@Param("projectName") String projectName);

    @Query("SELECT m FROM Member m JOIN m.project p WHERE p.name = :projectName AND m.role = :role")
    List<Member> findByProjectNameAndRole(@Param("projectName") String projectName, @Param("role") ProjectRole role);

    @Query("SELECT m FROM Member m JOIN m.project p JOIN m.participant part WHERE p.name = :project_name AND part.email = :email")
    Member findByProjectNameAndParticipantEmail(@Param("project_name") String project_name, @Param("email") String email);

    @Modifying
    @Query("DELETE FROM Member m WHERE m.project.name = :project_name AND m.participant.email = :email")
    void deleteByProjectNameAndParticipantEmail(@Param("project_name") String project_name, @Param("email") String email);
}