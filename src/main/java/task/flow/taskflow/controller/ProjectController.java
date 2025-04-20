package task.flow.taskflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task.flow.taskflow.model.Project;
import task.flow.taskflow.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<Project> findAllProjects() {
        return service.findAllProjects();
    }

    @GetMapping("/creator/{email}")
    public List<Project> findByCreatorEmail(@PathVariable("email") String email) {
        return service.findByCreatorEmail(email);
    }

    @GetMapping("/{project_name}")
    public Project findByName(@PathVariable("project_name") String project_name) {
        return service.findByName(project_name);
    }

    @PostMapping("/save_project")
    public Project saveProject(@RequestBody Project project) {
        return service.saveProject(project);
    }

    @DeleteMapping("/delete_project/{project_name}")
    public void deleteByName(@PathVariable("project_name") String project_name) {
        service.deleteByName(project_name);
    }

    @PutMapping("/update_project")
    public Project updateProject(@RequestBody Project project) {
        return service.updateProject(project);
    }

    @GetMapping("/validate_password/{project_name}/{password}")
    public boolean validateProjectPassword(@PathVariable("project_name") String project_name, @PathVariable("password") String password) {
        return service.validateProjectPassword(project_name, password);
    }
}
