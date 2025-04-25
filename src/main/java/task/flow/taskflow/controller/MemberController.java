package task.flow.taskflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import task.flow.taskflow.model.Member;
import task.flow.taskflow.model.ProjectRole;
import task.flow.taskflow.service.MemberService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService service;


    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/project/{project_name}")
    public List<Member> findByProjectName(@PathVariable("project_name") String project_name) {
        return service.findMembersByProjectName(project_name);
    }

    @GetMapping("/project/{project_name}/role/{role}")
    public List<Member> findMembersByProjectNameAndRole(
            @PathVariable("project_name") String project_name,
            @PathVariable("role") ProjectRole role) {
        return service.findMembersByProjectNameAndRole(project_name, role);
    }

    @GetMapping("/project/{project_name}/participant/{email}")
    public Member findMemberByProjectAndParticipant(
            @PathVariable("project_name") String project_name,
            @PathVariable("email") String email) {
        return service.findMemberByProjectAndParticipant(project_name, email);
    }

    @PostMapping("/add_member")
    public Member addMember(@RequestBody Map<String, String> request) {
        String project_name = request.get("project_name");
        String email = request.get("email");
        ProjectRole role = ProjectRole.valueOf(request.get("role"));
        return service.saveMember(project_name, email, role);
    }

    @PutMapping("/update/{project_name}/{email}/{role}")
    public Member updateMemberRole(
            @PathVariable("project_name") String project_name,
            @PathVariable("email") String email,
            @PathVariable("role") ProjectRole newRole) {
        return service.updateMemberRole(project_name, email, newRole);
    }

    @DeleteMapping("/remove/{project_name}/{email}")
    public void removeMember(
            @PathVariable("project_name") String project_name,
            @PathVariable("email") String email) {
        service.removeMember(project_name, email);
    }


}
