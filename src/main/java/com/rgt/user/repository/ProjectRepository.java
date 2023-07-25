package com.rgt.user.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rgt.user.entity.Project;

@Repository
public interface ProjectRepository {

	void addProject(Project project);
    void removeProject(Project project);
    Project getProjectByName(String name);
    List<Project> getAllProjects();
}
