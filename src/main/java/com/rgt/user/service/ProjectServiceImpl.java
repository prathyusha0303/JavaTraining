package com.rgt.user.service;

import java.util.ArrayList;
import java.util.List;

import com.rgt.user.entity.Project;
import com.rgt.user.entity.Task;
import com.rgt.user.repository.ProjectRepository;
import com.rgt.user.repository.TaskRepository;

public class ProjectServiceImpl implements ProjectService{

	private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void createProject(String name) {
        Project project = new Project(name);
        projectRepository.addProject(project);
    }

    @Override
    public List<Task> getAllTasksInProject(String projectName) {
        Project project = projectRepository.getProjectByName(projectName);
        if (project != null) {
            return taskRepository.getTasksByProject(project);
        }
        return new ArrayList<>();
    }

	@Override
	public List<Project> getAllProjects() {
		List<Project> project = projectRepository.getAllProjects();
		return project;
	}
}
