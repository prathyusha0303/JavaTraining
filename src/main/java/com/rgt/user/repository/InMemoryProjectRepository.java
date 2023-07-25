package com.rgt.user.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rgt.user.entity.Project;

public class InMemoryProjectRepository implements ProjectRepository {

	private Map<String, Project> projects;

	public InMemoryProjectRepository() {
		// Initialize an empty HashMap to store projects, using the project name (String) as the key and the Project object as the value.
		projects = new HashMap<>();
	}

	@Override
	public void addProject(Project project) {
		// Add the project to the projects map with its name as the key.
		projects.put(project.getName(), project);
	}

	@Override
	public void removeProject(Project project) {
		// Remove the project from the projects map based on its name.
		projects.remove(project.getName());
	}

	@Override
	public Project getProjectByName(String name) {
		// Retrieve the project from the projects map based on its name.
		return projects.get(name);
	}

	@Override
	public List<Project> getAllProjects() {
		// Return a list containing all the Project objects stored in the repository.
        // We use ArrayList constructor to create a new list and pass the values of the projects map.
		return new ArrayList<>(projects.values());
	}
}
