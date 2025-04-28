package task.flow.taskflow.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {
    private String name;
    private List<MemberDTO> members;
    private List<IssueDTO> issues;
}
