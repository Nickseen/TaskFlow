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

@Service
@AllArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository repository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;




    @Override
    @Transactional
    public Issue createIssue(String project_name, String title, String description, IssueStatus status,
                             IssuePriority priority, String assign_email, String report_email) {
        Issue issue = new Issue();
        Project project = projectRepository.findByName(project_name);
        Member assignee = memberRepository.findByProjectNameAndParticipantEmail(project_name, assign_email);
        Member reporter = memberRepository.findByProjectNameAndParticipantEmail(project_name, report_email);

        if (issue.getStatus() == null) {
            issue.setStatus(IssueStatus.BACKLOG);
        }

        if (issue.getPriority() == null) {
            issue.setPriority(IssuePriority.MEDIUM);
        }

        LocalDateTime now = LocalDateTime.now();
        issue.setProject(project);
        issue.setTitle(title);
        issue.setDescription(description);
        issue.setStatus(status);
        issue.setPriority(priority);
        issue.setAssignee(assignee);
        if (reporter != null) {
            issue.setReporter(reporter);
        }
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
    public List<Issue> getProjectIssues(String project_name) {
        return repository.findByProjectName(project_name);
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
    
