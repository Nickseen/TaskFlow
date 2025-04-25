package task.flow.taskflow.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import task.flow.taskflow.model.Member;
import task.flow.taskflow.model.Participant;
import task.flow.taskflow.model.Project;
import task.flow.taskflow.model.ProjectRole;
import task.flow.taskflow.repository.MemberRepository;
import task.flow.taskflow.repository.ParticipantRepository;
import task.flow.taskflow.repository.ProjectRepository;
import task.flow.taskflow.service.MemberService;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;
    private final ProjectRepository projectRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public List<Member> findMembersByProjectName(String project_name) {
        return repository.findByProjectName(project_name);
    }

    @Override
    public List<Member> findMembersByProjectNameAndRole(String project_name, ProjectRole role) {
        return repository.findByProjectNameAndRole(project_name, role);
    }

    @Override
    public Member findMemberByProjectAndParticipant(String project_name, String email) {
        return repository.findByProjectNameAndParticipantEmail(project_name, email);
    }

    @Override
    @Transactional
    public Member saveMember(String project_name, String email, ProjectRole role) {
        Project project = projectRepository.findByName(project_name);
        Participant participant = participantRepository.findParticipantsByEmail(email);

        Member member = new Member();
        member.setProject(project);
        member.setParticipant(participant);
        member.setRole(role);

        return repository.save(member);
    }

    @Override
    @Transactional
    public Member updateMemberRole(String project_name, String email, ProjectRole newRole) {
        Member member = repository.findByProjectNameAndParticipantEmail(project_name, email);
        if (member != null) {
            member.setRole(newRole);
            return repository.save(member);
        }
        return null;
    }


    @Override
    @Transactional
    public void removeMember(String project_name, String email) {
        repository.deleteByProjectNameAndParticipantEmail(project_name, email);
    }
}
