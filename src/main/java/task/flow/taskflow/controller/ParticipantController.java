package task.flow.taskflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.flow.taskflow.model.Participant;
import task.flow.taskflow.service.ParticipantService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/participants")
public class ParticipantController {
    private final ParticipantService service;

    @Autowired
    public ParticipantController(ParticipantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Participant> findAllParticipants() {
        return service.findAllParticipants();
    }

    @PostMapping("/save_participant")
    public Participant saveParticipant(@RequestBody Participant participant) {
        return service.saveParticipant(participant);
    }

    @GetMapping("/{email}")
    public Participant findByEmail(@PathVariable("email") String email) {
        return service.findByEmail(email);
    }

    @PutMapping("/update_participant")
    public Participant updateParticipant(@RequestBody Participant participant) {
        return service.updateParticipant(participant);
    }

    @DeleteMapping("/delete_participant/{email}")
    public void deleteParticipant(@PathVariable("email") String email) {
        service.deleteParticipant(email);
    }
}