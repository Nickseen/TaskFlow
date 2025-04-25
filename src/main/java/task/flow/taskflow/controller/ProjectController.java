package task.flow.taskflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import task.flow.taskflow.dto.ProjectDTO;
import task.flow.taskflow.model.Member;
import task.flow.taskflow.model.Project;
import task.flow.taskflow.service.MemberService;
import task.flow.taskflow.service.ProjectService;
import task.flow.taskflow.utility.ProjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectService service;
    private final MemberService memberService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public List<ProjectDTO> getAllProjectsWithMembers() {
        return service.findAllProjects().stream()
                .map(project -> {
                    List<Member> members = memberService.findMembersByProjectName(project.getName());
                    return projectMapper.toProjectDTO(project, members);
                })
                .collect(Collectors.toList());
    }


    @Autowired
    public ProjectController(ProjectService service, MemberService memberService, ProjectMapper projectMapper) {
        this.service = service;
        this.memberService = memberService;
        this.projectMapper = projectMapper;
    }



    @GetMapping("/creator/{email}")
    public List<Project> findByCreatorEmail(@PathVariable("email") String email) {
        return service.findByCreatorEmail(email);
    }

    @GetMapping("/{name}")
    public Project findByName(@PathVariable("name") String project_name) {
        return service.findProject(project_name);
    }

    @PostMapping("/save_project")
    public Project saveProject(@RequestBody Project project) {
        return service.saveProject(project);
    }

    @DeleteMapping("/delete_project/{name}")
    public void deleteByName(@PathVariable("name") String project_name) {
        service.deleteProject(project_name);
    }

    @PutMapping("/update_project")
    public Project updateProject(@RequestBody Project project) {
        return service.updateProject(project);
    }

    @GetMapping("/validate_password/{name}/{password}")
    public boolean validateProjectPassword(@PathVariable("name") String project_name, @PathVariable("password") String password) {
        return service.validateProjectPassword(project_name, password);
    }
}
