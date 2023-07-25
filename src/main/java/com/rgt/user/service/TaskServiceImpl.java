package com.rgt.user.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rgt.user.entity.Priority;
import com.rgt.user.entity.Project;
import com.rgt.user.entity.Task;
import com.rgt.user.repository.TaskRepository;
@Service
public class TaskServiceImpl implements TaskService{

	private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(String title, String description, LocalDate dueDate, Priority priority) {
        Task task = new Task(title, description, dueDate, priority, false);
        taskRepository.addTask(task);
    }

    @Override
    public void markTaskAsCompleted(Task task) {
        task.setCompleted(true);
    }

	@Override
	public List<Task> getTasksByPriority(Priority priority) {
		List<Task> task=taskRepository.getTasksByPriority(priority);
		return task;
	}

	@Override
	public Task getTaskById(int taskId) {
		Task task=taskRepository.getTaskById(taskId);
		return task;
	}

	@Override
	public void createTask(String title, String description, LocalDate dueDate, Priority priority, Project project) {
		 Task task = new Task(title, description, dueDate, priority, project);
	        taskRepository.addTask(task);
		
	}
}
