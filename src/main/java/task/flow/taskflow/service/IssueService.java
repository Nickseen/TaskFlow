package task.flow.taskflow.service;

import task.flow.taskflow.model.Issue;
import task.flow.taskflow.model.enums.IssuePriority;
import task.flow.taskflow.model.enums.IssueStatus;
import java.util.List;

public interface IssueService {
    Issue createIssue(Issue issue);
    Issue updateIssue(Long id, Issue issue);
    void deleteIssue(Long id);
    List<Issue> getProjectIssues(Long projectId);
    List<Issue> getMemberAssignedIssues(Long memberId);
}