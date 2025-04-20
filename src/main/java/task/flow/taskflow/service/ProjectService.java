package task.flow.taskflow.service;

import task.flow.taskflow.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAllProjects();
    List<Project> findByCreatorEmail(String email);
    Project saveProject(Project project);
    Project findByName(String project_name);
    void deleteByName(String project_name);
    Project updateProject(Project project);
    boolean validateProjectPassword(String project_name, String password);


}
