package com.rgt.user.service;

import java.util.List;

import com.rgt.user.entity.Project;
import com.rgt.user.entity.Task;

public interface ProjectService {

	void createProject(String name);
    List<Task> getAllTasksInProject(String projectName);
	List<Project> getAllProjects();
}
