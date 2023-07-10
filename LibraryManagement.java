package com.library.jfx.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a Library Management System application.
 * It allows users to add, update, and delete resources in a library.
 * The application provides a graphical user interface built with JavaFX.
 */
@SuppressWarnings("restriction")
public class LibraryManagement extends Application {

	private TableView<Resource> table;
	private ObservableList<Resource> resourceList;

	private TextField titleField;
	private TextField authorField;
	private ComboBox<String> typeComboBox;
	 private TextField searchField;

	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * Initializes and starts the Library Management application.
	 * @param primaryStage the primary stage for the application
	 */
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Library Management System");

		// Create table and resource list
		table = new TableView<>();
		resourceList = FXCollections.observableArrayList();
		table.setItems(resourceList);

		// Set up table columns
		TableColumn<Resource, String> titleColumn = new TableColumn<>("Title");
		titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

		TableColumn<Resource, String> authorColumn = new TableColumn<>("Author");
		 authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

		TableColumn<Resource, String> typeColumn = new TableColumn<>("Resource");
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

		table.getColumns().addAll(titleColumn, authorColumn, typeColumn);

		// Create buttons and search box
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> addResource());

		Button updateButton = new Button("Update");
		updateButton.setOnAction(e -> updateResource());

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteResource());
		
		Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchResource());

		HBox buttonsBox = new HBox(10);
		buttonsBox.getChildren().addAll(addButton, updateButton, deleteButton);
		
		HBox searchBox = new HBox(10);
        searchField = new TextField();
        searchBox.getChildren().addAll(new Label("Search:"), searchField, searchButton);

        // Create input fields
		Label titleLabel = new Label("Title:");
		titleField = new TextField();

		Label authorLabel = new Label("Author:");
		authorField = new TextField();

		Label typeLabel = new Label("Resource:");
		typeComboBox = new ComboBox<>();
		typeComboBox.getItems().addAll("Book", "Magazine", "CD");

		GridPane inputGridPane = new GridPane();
		inputGridPane.setHgap(10);
		inputGridPane.setVgap(10);
		inputGridPane.setPadding(new Insets(10));

		inputGridPane.add(titleLabel, 0, 0);
		inputGridPane.add(titleField, 1, 0);
		inputGridPane.add(authorLabel, 0, 1);
		inputGridPane.add(authorField, 1, 1);
		inputGridPane.add(typeLabel, 0, 2);
		inputGridPane.add(typeComboBox, 1, 2);

		VBox mainBox = new VBox(10);
		mainBox.getChildren().addAll(table, buttonsBox,searchBox, inputGridPane);

		// Create and show the scene
		Scene scene = new Scene(mainBox, 800, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
		// Load resources from file
		loadResources();
	}
	/**
	 * Adds a new resource to the library.
	 * Retrieves the input values from the fields and creates a new Resource object.
	 * Validates the input fields before adding the resource to the list.
	 * Clears the input fields after adding the resource.
	 */
	private void addResource() {
		String title = titleField.getText();
		String author = authorField.getText();
		String resource = typeComboBox.getValue();

		if (title.isEmpty() || author.isEmpty() || resource == null) {
			showAlert("Error", "Please enter all fields.");
			return;
		}

		Resource newResource = new Resource(title, author, resource);
		resourceList.add(newResource);
		saveResources();
		clearFields();
	}
	/**
	 * Updates the selected resource in the library.
	 * Retrieves the input values from the fields and updates the properties of the selected resource.
	 * Validates the input fields before updating the resource.
	 * Refreshes the table to reflect the changes.
	 * Clears the input fields after updating the resource.
	 */
	private void updateResource() {
		Resource selectedResource = table.getSelectionModel().getSelectedItem();
		if (selectedResource == null) {
			showAlert("Error", "Please select a resource to update.");
			return;
		}

		String newTitle = titleField.getText();
		String newAuthor = authorField.getText();
		String newType = typeComboBox.getValue();

		if (newTitle.isEmpty() || newAuthor.isEmpty() || newType == null) {
			showAlert("Error", "Please enter all fields.");
			return;
		}

		selectedResource.setTitle(newTitle);
		selectedResource.setAuthor(newAuthor);
		selectedResource.setType(newType);

		table.refresh();
		saveResources();
		clearFields();
	}

	/**
	 * Deletes the selected resource from the library.
	 * Retrieves the selected resource from the table and removes it from the resource list.
	 * Saves the changes to the file.
	 * Clears the input fields after deleting the resource.
     */
	private void deleteResource() {
		Resource selectedResource = table.getSelectionModel().getSelectedItem();
		if (selectedResource == null) {
			showAlert("Error", "Please select a resource to delete.");
			return;
		}

		resourceList.remove(selectedResource);
		saveResources();
		clearFields();
	}
	/**
	 * Searches for resources in the library based on the search query.
	 * 	Retrieves the search query from the search field and performs a case-insensitive search.
	 * 	Populates the table with the search results.
	 */
	 private void searchResource() {
	        String query = searchField.getText().toLowerCase();
	        ObservableList<Resource> searchResults = FXCollections.observableArrayList();

	        for (Resource resource : resourceList) {
	            if (resource.getTitle().toLowerCase().contains(query) ||
	                    resource.getAuthor().toLowerCase().contains(query) ||
	                    resource.getType().toLowerCase().contains(query)) {
	                searchResults.add(resource);
	            }
	        }

	        table.setItems(searchResults);
	    }

	 /**
	  *  Clears the input fields and selection in the table.
	  */
	private void clearFields() {
		titleField.clear();
		authorField.clear();
		typeComboBox.getSelectionModel().clearSelection();
		table.getSelectionModel().clearSelection();
	}

	/**
	 * 	Loads the resources from the file and populates the resource list.
	 * Reads each line from the file and creates Resource objects based on the fields.
	 * Adds the resources to the resource list.
	 * Displays an error message if loading fails.
	 */
	private void loadResources() {
		try (BufferedReader reader = new BufferedReader(new FileReader("resources.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fields = line.split(",");
				if (fields.length == 3) {
					Resource resource = new Resource(fields[0], fields[1], fields[2]);
					resourceList.add(resource);
				}
			}
		} catch (IOException e) {
			showAlert("Error", "Failed to load resources.");
		}
	}
	/**
	 * Saves the resources to the file.
	 * Iterates over the resource list and writes each resource's fields to a new line in the file.
	 * Displays an error message if saving fails.
	 */
	private void saveResources() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources.txt"))) {
			for (Resource resource : resourceList) {
				String line = resource.getTitle() + "," + resource.getAuthor() + "," + resource.getType();
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) {
			showAlert("Error", "Failed to save resources.");
		}
	}
	/**
	 * Displays an alert dialog with the specified title and message.
	 * @param title the title of the alert dialog
	 * @param message the message of the alert dialog
	 */
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	/**
	 * 	Represents a resource in the library.
	 *  Each resource has a title, author, and type.
	 */
	public class Resource {
	    private final StringProperty title;
	    private final StringProperty author;
	    private final StringProperty type;
	    /**
	     * Creates a new Resource with the specified title, author, and type.
	     * @param title the title of the resource
	     * @param author the author of the resource
	     * @param type the type of the resource
	     */
	    public Resource(String title, String author, String type) {
	        this.title = new SimpleStringProperty(title);
	        this.author = new SimpleStringProperty(author);
	        this.type = new SimpleStringProperty(type);
	    }

	    public String getTitle() {
	        return title.get();
	    }

	    public void setTitle(String title) {
	        this.title.set(title);
	    }

	    public StringProperty titleProperty() {
	        return title;
	    }

	    public String getAuthor() {
	        return author.get();
	    }

	    public void setAuthor(String author) {
	        this.author.set(author);
	    }

	    public StringProperty authorProperty() {
	        return author;
	    }

	    public String getType() {
	        return type.get();
	    }

	    public void setType(String type) {
	        this.type.set(type);
	    }

	    public StringProperty typeProperty() {
	        return type;
	    }
	}

}
