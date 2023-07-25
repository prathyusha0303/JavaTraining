package com.rgt.user;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rgt.user.entity.Priority;
import com.rgt.user.entity.Project;
import com.rgt.user.entity.Task;
import com.rgt.user.repository.InMemoryProjectRepository;
import com.rgt.user.repository.InMemoryTaskRepository;
import com.rgt.user.repository.ProjectRepository;
import com.rgt.user.repository.TaskRepository;
import com.rgt.user.service.ProjectService;
import com.rgt.user.service.ProjectServiceImpl;
import com.rgt.user.service.TaskService;
import com.rgt.user.service.TaskServiceImpl;

@SpringBootApplication
public class UserTaskManagementApplicationSolidApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Create instances of repositories and services
		TaskRepository taskRepository = new InMemoryTaskRepository();
		ProjectRepository projectRepository = new InMemoryProjectRepository();
		TaskService taskService = new TaskServiceImpl(taskRepository);
		ProjectService projectService = new ProjectServiceImpl(projectRepository, taskRepository);

		while (true) {
			displayMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				createProject(scanner, projectService);
				break;
			case 2:
				createTask(scanner, taskService, projectService);
				break;
			case 3:
				markTaskAsCompleted(scanner, taskService);
				break;
			case 4:
				viewTasksByProject(scanner, projectService);
				break;
			case 5:
				viewTasksByPriority(scanner, taskService);
				break;
			case 6:
				System.out.println("Exiting the application.");
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please try again.");

			}
		}
	}
	// Display the main menu options
	private static void displayMenu() {
		System.out.println("User Task Management Application");
		System.out.println("1. Create a new project");
		System.out.println("2. Create a new task");
		System.out.println("3. Mark a task as completed");
		System.out.println("4. View tasks by project");
		System.out.println("5. View tasks by priority");
		System.out.println("6. Exit");
		System.out.print("Enter your choice: ");

	}
	// Method to create a new task
	private static void createTask(Scanner scanner, TaskService taskService, ProjectService projectService) {
		// Read task details from the user
		System.out.print("Enter task title: ");
		String title = scanner.nextLine();

		System.out.print("Enter task description: ");
		String description = scanner.nextLine();

		System.out.print("Enter task due date (yyyy-mm-dd): ");
		LocalDate dueDate = LocalDate.parse(scanner.nextLine());

		System.out.println("Select task priority: ");
		int priorityChoice = 1;
		for (Priority priority : Priority.values()) {
			System.out.println(priorityChoice + ". " + priority);
			priorityChoice++;
		}
		int selectedPriority = scanner.nextInt();
		scanner.nextLine(); 
		Priority priority = Priority.values()[selectedPriority - 1];
		// Check if there are any projects to associate with the task
		List<Project> projects = projectService.getAllProjects();
		if (projects.isEmpty()) {
			System.out.println("No projects available. Creating task without a project.");
			taskService.createTask(title, description, dueDate, priority);
		} else {
			System.out.println("Select the project to associate the task with: ");
			int projectChoice = 1;
			for (Project project : projects) {
				System.out.println(projectChoice + ". " + project.getName());
				projectChoice++;
			}
			int selectedProject = scanner.nextInt();
			scanner.nextLine(); 
			Project project = projects.get(selectedProject - 1);
			taskService.createTask(title, description, dueDate, priority, project);
		}
		System.out.println("Task created successfully!");
	}
	// Method to mark a task as completed
	private static void markTaskAsCompleted(Scanner scanner, TaskService taskService) {
		System.out.print("Enter task ID to mark as completed: ");
		int taskId = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character after reading the integer input

		Task task = taskService.getTaskById(taskId);
		if (task == null) {
			System.out.println("Task not found with ID: " + taskId);
		} else {
			taskService.markTaskAsCompleted(task);
			System.out.println("Task marked as completed: " + task.getTitle());
		}
	}
	// Method to create a new project
	private static void createProject(Scanner scanner, ProjectService projectService) {
		System.out.print("Enter project name: ");
		String projectName = scanner.nextLine();

		projectService.createProject(projectName);
		System.out.println("Project created successfully: " + projectName);
	}
	// Method to view tasks in a specific project
	private static void viewTasksByProject(Scanner scanner, ProjectService projectService) {
		System.out.print("Enter project name to view tasks: ");
		String projectName = scanner.nextLine();

		List<Task> tasks = projectService.getAllTasksInProject(projectName);
		if (tasks.isEmpty()) {
			System.out.println("No tasks found for the project: " + projectName);
		} else {
			System.out.println("Tasks in project: " + projectName);
			displayTasks(tasks);
		}
	}
	// Method to view tasks by priority
	private static void viewTasksByPriority(Scanner scanner, TaskService taskService) {
		System.out.println("Select task priority to view tasks: ");
		int priorityChoice = 1;
		for (Priority priority : Priority.values()) {
			System.out.println(priorityChoice + ". " + priority);
			priorityChoice++;
		}
		int selectedPriority = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character after reading the integer input
		Priority priority = Priority.values()[selectedPriority - 1];

		List<Task> tasks = taskService.getTasksByPriority(priority);
		if (tasks.isEmpty()) {
			System.out.println("No tasks found with priority: " + priority);
		} else {
			System.out.println("Tasks with priority: " + priority);
			displayTasks(tasks);
		}
	}
	// Method to display task details
	private static void displayTasks(List<Task> tasks) {
		for (Task task : tasks) {
			System.out.println("ID: " + task.getId());
			System.out.println("Title: " + task.getTitle());
			System.out.println("Description: " + task.getDescription());
			System.out.println("Due Date: " + task.getDueDate());
			System.out.println("Priority: " + task.getPriority());
			System.out.println("Completed: " + (task.isCompleted() ? "Yes" : "No"));
			System.out.println("---------------------------");
		}
	}
}
