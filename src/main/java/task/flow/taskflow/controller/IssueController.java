package task.flow.taskflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.flow.taskflow.model.Issue;
import task.flow.taskflow.model.enums.IssueStatus;
import task.flow.taskflow.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/create")
    public Issue createIssue(@RequestBody Issue issue) {
        return issueService.createIssue(issue);
    }

    @PutMapping("/{id}")
    public Issue updateIssue(@PathVariable Long id, @RequestBody Issue issue) {
        return issueService.updateIssue(id, issue);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
    }

    @GetMapping("/project/{projectId}")
    public List<Issue> getProjectIssues(@PathVariable Long projectId) {
        return issueService.getProjectIssues(projectId);
    }

    @GetMapping("/assignee/{memberId}")
    public List<Issue> getMemberAssignedIssues(@PathVariable Long memberId) {
        return issueService.getMemberAssignedIssues(memberId);
    }

    @GetMapping("/project/{projectName}/status/{status}")
    public List<Issue> findByProjectNameAndStatus(
            @PathVariable String projectName,
            @PathVariable IssueStatus status) {
        return issueService.findByProjectNameAndStatus(projectName, status);
    }

    @GetMapping("/assignee/email/{email}")
    public List<Issue> findByAssigneeEmail(@PathVariable String email) {
        return issueService.findByAssigneeEmail(email);
    }

    @GetMapping("/reporter/email/{email}")
    public List<Issue> findByReporterEmail(@PathVariable String email) {
        return issueService.findByReporterEmail(email);
    }
}