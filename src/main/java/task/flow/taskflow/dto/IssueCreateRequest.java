package task.flow.taskflow.dto;

import lombok.Data;
import task.flow.taskflow.model.enums.IssuePriority;
import task.flow.taskflow.model.enums.IssueStatus;

@Data
public class IssueCreateRequest {
    private String project_name;
    private String title;
    private String description;
    private IssueStatus status;
    private IssuePriority priority;
    private String assign_email;
    private String report_email;
}