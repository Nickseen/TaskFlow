package task.flow.taskflow.dto;

import lombok.Data;
import task.flow.taskflow.model.enums.IssuePriority;
import task.flow.taskflow.model.enums.IssueStatus;

@Data
public class IssueDTO {
    private String title;
    private String description;
    private IssueStatus status;
    private IssuePriority priority;
}