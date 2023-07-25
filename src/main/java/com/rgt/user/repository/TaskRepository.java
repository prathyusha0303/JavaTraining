package com.rgt.user.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rgt.user.entity.Priority;
import com.rgt.user.entity.Project;
import com.rgt.user.entity.Task;
@Repository
public interface TaskRepository {

	void addTask(Task task);
    void removeTask(Task task);
    Task getTaskById(int taskId);
    List<Task> getAllTasks();
    List<Task> getTasksByProject(Project project);
    List<Task> getTasksByPriority(Priority priority);	
}
