package task.flow.taskflow.service;

import task.flow.taskflow.model.Issue;
import task.flow.taskflow.model.enums.IssuePriority;
import task.flow.taskflow.model.enums.IssueStatus;
import java.util.List;

public interface IssueService {
    Issue createIssue(String project_name, String title, String description, IssueStatus status,
                      IssuePriority priority, String assign_email, String report_email);
    Issue updateIssue(Long id, Issue issue);
    void deleteIssue(Long id);
    List<Issue> getProjectIssues(Long projectId);
    List<Issue> getMemberAssignedIssues(Long memberId);
    List<Issue> findByProjectNameAndStatus(String projectName, IssueStatus status);
    List<Issue> findByAssigneeEmail(String email);
    List<Issue> findByReporterEmail(String email);

}