package task.flow.taskflow.service;

import task.flow.taskflow.model.Participant;

import java.util.List;


public interface ParticipantService {
    List<Participant> findAllParticipants();
    Participant saveParticipant(Participant participant);
    Participant findByEmail(String email);
    Participant updateParticipant(Participant participant);
    void deleteParticipant(String email);
}
