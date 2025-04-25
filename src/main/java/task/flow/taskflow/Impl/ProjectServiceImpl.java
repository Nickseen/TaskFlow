package task.flow.taskflow.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import task.flow.taskflow.model.Project;
import task.flow.taskflow.model.Member;
import task.flow.taskflow.model.enums.ProjectRole;
import task.flow.taskflow.repository.MemberRepository;
import task.flow.taskflow.repository.ProjectRepository;
import task.flow.taskflow.service.ProjectService;
import task.flow.taskflow.utility.PasswordUtil;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;
    private final MemberRepository memberRepository;

    @Override
    public List<Project> findAllProjects() {
        return repository.findAll();
    }

    @Override
    public List<Project> findByCreatorEmail(String email) {
        return repository.findByCreatorEmail(email);
    }

    @Override
    @Transactional
    public Project saveProject(Project project) {
        project.setPasswordHash(PasswordUtil.hashPassword(project.getPassword()));
        project.setPassword(null);

        Project savedProject = repository.save(project);

        Member ownerMember = new Member();
        ownerMember.setProject(savedProject);
        ownerMember.setParticipant(project.getCreator());
        ownerMember.setRole(ProjectRole.OWNER);

        memberRepository.save(ownerMember);

        return savedProject;
    }

    @Override
    public Project findProject(String project_name) {
        return repository.findByName(project_name);
    }

    @Override
    @Transactional
    public void deleteProject(String project_name) {
        repository.deleteByName(project_name);
    }

    @Override
    @Transactional
    public Project updateProject(Project project) {
        return repository.save(project);
    }

    @Override
    public boolean validateProjectPassword(String project_name, String password) {
        Project project = repository.findByName(project_name);
        return PasswordUtil.checkPassword(password, project.getPasswordHash());
    }
}
