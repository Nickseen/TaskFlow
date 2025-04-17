package task.flow.taskflow.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import task.flow.taskflow.model.Participant;
import task.flow.taskflow.repository.ParticipantRepository;
import task.flow.taskflow.service.ParticipantService;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository repository;


    @Override
    public List<Participant> findAllParticipants() {
        return repository.findAll();
    }

    @Override
    public Participant saveParticipant(Participant participant) {
        return repository.save(participant);
    }

    @Override
    public Participant findByEmail(String email) {
        return repository.findParticipantsByEmail(email);
    }

    @Override
    public Participant updateParticipant(Participant participant) {
        return repository.save(participant);
    }

    @Override
    @Transactional
    public void deleteParticipant(String email) {
        repository.deleteByEmail(email);
    }
}
