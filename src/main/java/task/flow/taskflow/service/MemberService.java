package task.flow.taskflow.service;

import task.flow.taskflow.model.Member;
import task.flow.taskflow.model.ProjectRole;

import java.util.List;

public interface MemberService {
    List<Member> findMembersByProjectName(String project_name);
    List<Member> findMembersByProjectNameAndRole(String project_name, ProjectRole role);
    Member findMemberByProjectAndParticipant(String project_name, String email);
    Member saveMember(String projectName, String email, ProjectRole role);
    Member updateMemberRole(String projectName, String email, ProjectRole newRole);
    void removeMember(String project_name, String email);
}