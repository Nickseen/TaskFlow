package task.flow.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.flow.taskflow.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    void deleteByEmail(String email);
    Participant findParticipantsByEmail(String email);
}
