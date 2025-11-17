package task.flow.taskflow.utility;

import org.springframework.stereotype.Component;
import task.flow.taskflow.dto.IssueDTO;
import task.flow.taskflow.dto.MemberDTO;
import task.flow.taskflow.dto.ProjectDTO;
import task.flow.taskflow.model.Issue;
import task.flow.taskflow.model.Member;
import task.flow.taskflow.model.Project;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapper {
    public ProjectDTO toProjectDTO(Project project, List<Member> members, List<Issue> issues) {
        ProjectDTO dto = new ProjectDTO();
        dto.setName(project.getName());

        dto.setMembers(members.stream()
                .map(this::toMemberDTO)
                .collect(Collectors.toList()));

        dto.setIssues(issues.stream()
                .map(this::toIssueDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    private MemberDTO toMemberDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setEmail(member.getParticipant().getEmail());
        dto.setRole(member.getRole());
        return dto;
    }

    private IssueDTO toIssueDTO(Issue issue) {
        IssueDTO dto = new IssueDTO();
        dto.setTitle(issue.getTitle());
        dto.setDescription(issue.getDescription());
        dto.setStatus(issue.getStatus());
        dto.setPriority(issue.getPriority());
        return dto;
    }
}