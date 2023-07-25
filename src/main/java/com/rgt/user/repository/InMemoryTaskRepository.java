package com.rgt.user.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rgt.user.entity.Priority;
import com.rgt.user.entity.Project;
import com.rgt.user.entity.Task;
@Repository
public class InMemoryTaskRepository implements TaskRepository {

	private Map<Integer, Task> tasks;
    private int nextTaskId;

    public InMemoryTaskRepository() {
        tasks = new HashMap<>();
        nextTaskId = 1;
    }

    @Override
    public void addTask(Task task) {
        task.setId(nextTaskId++);
        tasks.put(task.getId(), task);
    }

    @Override
    public void removeTask(Task task) {
        tasks.remove(task.getId());
    }

    @Override
    public Task getTaskById(int taskId) {
        return tasks.get(taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Task> getTasksByProject(Project project) {
        List<Task> tasksInProject = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getProject().equals(project)) {
                tasksInProject.add(task);
            }
        }
        return tasksInProject;
    }

    @Override
    public List<Task> getTasksByPriority(Priority priority) {
        List<Task> tasksByPriority = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getPriority() == priority) {
                tasksByPriority.add(task);
            }
        }
        return tasksByPriority;
    }
}
