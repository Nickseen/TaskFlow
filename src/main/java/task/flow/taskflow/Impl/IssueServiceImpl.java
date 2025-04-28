package task.flow.taskflow.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import task.flow.taskflow.model.Issue;
import task.flow.taskflow.model.Member;
import task.flow.taskflow.model.Project;
import task.flow.taskflow.model.enums.IssuePriority;
import task.flow.taskflow.model.enums.IssueStatus;
import task.flow.taskflow.repository.IssueRepository;
import task.flow.taskflow.repository.MemberRepository;
import task.flow.taskflow.repository.ProjectRepository;
import task.flow.taskflow.service.IssueService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository repository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;




    @Override
    @Transactional
    public Issue createIssue(Issue issue) {
        Project project = projectRepository.findById(issue.getProject().getId())
                .orElseThrow(() -> new EntityNotFoundException("Project not found"));

        Member reporter = memberRepository.findById(issue.getReporter().getId())
                .orElseThrow(() -> new EntityNotFoundException("Reporter not found"));

        if (issue.getStatus() == null) {
            issue.setStatus(IssueStatus.BACKLOG);
        }

        if (issue.getPriority() == null) {
            issue.setPriority(IssuePriority.MEDIUM);
        }

        LocalDateTime now = LocalDateTime.now();
        issue.setCreatedAt(now);
        issue.setUpdatedAt(now);

        return repository.save(issue);

    }



    @Override
    @Transactional
    public Issue updateIssue(Long id, Issue issue) {

        Issue existingIssue = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Issue not found with id: " + id));



        existingIssue.setUpdatedAt(LocalDateTime.now());

        return repository.save(existingIssue);
    }

    @Override
    @Transactional
    public void deleteIssue(Long id) {
        repository.deleteIssueById(id);
    }

    @Override
    public List<Issue> getProjectIssues(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + projectId));

        return project.getIssues().stream().toList();
    }

    @Override
    public List<Issue> getMemberAssignedIssues(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));

        return member.getIssues().stream().toList();
    }

    @Override
    public List<Issue> findByProjectNameAndStatus(String projectName, IssueStatus status) {
        return repository.findByProjectNameAndStatus(projectName, status);
    }

    @Override
    public List<Issue> findByAssigneeEmail(String email) {
        return repository.findByAssigneeEmail(email);
    }

    @Override
    public List<Issue> findByReporterEmail(String email) {
        return repository.findByReporterEmail(email);
    }
}
    
