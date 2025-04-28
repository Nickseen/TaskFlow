package task.flow.taskflow.dto;

import lombok.Data;
import task.flow.taskflow.model.enums.ProjectRole;

@Data
public class MemberDTO {
    private String email;
    private ProjectRole role;
}
