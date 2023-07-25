package com.rgt.user.service;

import java.time.LocalDate;
import java.util.List;

import com.rgt.user.entity.Priority;
import com.rgt.user.entity.Project;
import com.rgt.user.entity.Task;

public interface TaskService {

	void createTask(String title, String description, LocalDate dueDate, Priority priority);
    void markTaskAsCompleted(Task task);
	List<Task> getTasksByPriority(Priority priority);
	Task getTaskById(int taskId);
	void createTask(String title, String description, LocalDate dueDate, Priority priority, Project project);
}
