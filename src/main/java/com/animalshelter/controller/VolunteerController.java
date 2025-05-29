package com.animalshelter.controller;

import com.animalshelter.domain.volunteers.Task;
import com.animalshelter.domain.volunteers.Volunteer;
import com.animalshelter.repositories.VolunteerRepository;
import com.animalshelter.service.TaskService;
import com.animalshelter.service.VolunteerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Volunteer Management screen.
 * Handles adding, searching, resetting, selecting, and navigating volunteers.
 */
@Component
public class VolunteerController {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private TaskService taskService;

    private final ApplicationContext springContext;

    @Autowired
    public VolunteerController(ApplicationContext springContext) {
        this.springContext = springContext;
    }

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;

    @FXML private Button addVolunteerButton;
    @FXML private Button searchVolunteerButton;
    @FXML private Button resetButton;

    @FXML private CheckBox cleanCageCheckBox;
    @FXML private CheckBox feedAnimalCheckBox;
    @FXML private CheckBox walkDogCheckBox;

    @FXML private Button assignTasksButton;

    @FXML private ListView<Volunteer> volunteerList;

    @FXML private ListView<Task> taskList;

    /**
     * Initializes the Volunteer view with default behavior.
     */
    @FXML
    public void initialize() {
        loadVolunteers();
        setUpSearchHandler();
        setupSelectionListener();
        setUpAssignTasksHandler();

        resetForm();

        addVolunteerButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
                Volunteer newVolunteer = new Volunteer(name, email, phone);

                try {
                    volunteerService.addVolunteer(newVolunteer);
                    showAlert("Success", "Volunteer added successfully!");
                    loadVolunteers();
                    clearForm();
                } catch (RuntimeException ex) {
                    volunteerList.getItems().clear();
                    showAlert("Error", "Failed to add volunteer: " + ex.getMessage());
                    resetForm();
                }
            }
        });
    }

    /**
     * Loads all volunteers from the database and displays them in the ListView.
     */
    private void loadVolunteers() {
        volunteerList.getItems().clear();

        try {
            // Define how each volunteer is displayed in the list
            volunteerList.setCellFactory(lv -> new ListCell<>() {
                @Override
                protected void updateItem(Volunteer volunteer, boolean empty) {
                    super.updateItem(volunteer, empty);
                    if (empty || volunteer == null) {
                        setText(null);
                    } else {
                        setText(String.format(
                                "%s | %s | %s",
                                volunteer.getName(),
                                volunteer.getEmail(),
                                volunteer.getPhone()
                        ));
                    }
                }
            });

            // Fetch and display the volunteers
            List<Volunteer> volunteers = volunteerService.getAllVolunteers();
            volunteerList.getItems().addAll(volunteers);

        } catch (Exception e) {
            showAlert("Error", "Failed to load volunteers: " + e.getMessage());
            resetForm();
        }
    }

    /**
     * Sets up search functionality by email or name using a single button.
     */
    private void setUpSearchHandler() {
        searchVolunteerButton.setOnAction(e -> {
            volunteerList.getItems().clear();

            String email = emailField.getText().trim();
            String name = nameField.getText().trim();

            // Search by email
            if (!email.isEmpty()) {
                volunteerService.getVolunteerByEmail(email)
                        .map(volunteer -> {
                            populateFormFields(volunteer);
                            return List.of(volunteer);
                        })
                        .ifPresentOrElse(
                                volunteerList.getItems()::addAll,
                                () -> showAlert("Error", "Volunteer not found!")
                        );
                return;
            }

            // Search by name
            if (!name.isEmpty()) {
                List<Volunteer> results = volunteerService.getVolunteerByName(name);
                (results.isEmpty() ? List.of(new Volunteer("No volunteers found", "", "")) : results)
                        .forEach(volunteerList.getItems()::add);
                return;
            }

            // No input
            showAlert("Error", "Enter a name or email address!");
            resetForm();
        });
    }

    /**
     *
     */
    private void loadTasksForVolunteer(Volunteer volunteer) {
        taskList.getItems().clear();
        List<Task> tasks = taskService.getTasksForVolunteer(volunteer.getVolunteerId());
        taskList.getItems().addAll(tasks);

        taskList.setCellFactory(lv -> new ListCell<>() {
            private final Button completeBtn = new Button("Mark Completed");

            {
                completeBtn.setOnAction(e -> {
                    Task task = getItem();
                    taskService.deleteTask(task);
                    taskList.getItems().remove(task);



                });

            }

            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(task.getDescription() + " - Pending");
                    setGraphic(completeBtn);
                }

            }

        });
    }




    /**
     * Sets up a listener that populates the form fields
     * when a volunteer is selected from the ListView.
     */
    private void setupSelectionListener() {
        volunteerList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                populateFormFields(newVal);
                loadTasksForVolunteer(newVal);
            }
        });
    }

    /**
     * Fills the form fields with the selected volunteer's data.
     */
    private void populateFormFields(Volunteer volunteer) {
        nameField.setText(volunteer.getName());
        emailField.setText(volunteer.getEmail());
        phoneField.setText(volunteer.getPhone());
    }

    /**
     * Assigns Tasks to Volunteers
     */
    private void setUpAssignTasksHandler() {
        assignTasksButton.setOnAction(e -> {
            Volunteer selectedVolunteer = volunteerList.getSelectionModel().getSelectedItem();
            if (selectedVolunteer == null) {
                volunteerList.getItems().clear();
                showAlert("Error", "No volunteer selected!");
                resetForm();
                return;
            }
            List<String> selectedTasks = new ArrayList<>();
            if (cleanCageCheckBox.isSelected()) selectedTasks.add("Clean Cage");
            if (feedAnimalCheckBox.isSelected()) selectedTasks.add("Feed Animal");
            if (walkDogCheckBox.isSelected()) selectedTasks.add("Walk Dog");

            if (selectedTasks.isEmpty()) {
                volunteerList.getItems().clear();
                showAlert("Error", "No tasks selected!");
                resetForm();
                return;
            }

            taskService.assignTaskToVolunteer(selectedVolunteer, selectedTasks);
            showAlert("Success", "Task assigned successfully!" + System.lineSeparator() + selectedTasks + selectedVolunteer.getName());
            loadVolunteers();
            clearForm();

        });
    }

    /**
     * Method to either show a success message or an error message
     * @param title
     * @param message
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    /**
     * Clears form fields for a clean input.
     */
    private void clearForm() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        cleanCageCheckBox.setSelected(false);
        feedAnimalCheckBox.setSelected(false);
        walkDogCheckBox.setSelected(false);
    }

    /**
     * Resets the form and refreshes the full list of volunteers.
     */
    private void resetForm() {
        resetButton.setOnAction(e -> {
            clearForm();
            loadVolunteers();
        });
    }



    // -------- Navigation between views -------- //

    @FXML
    private void goToHomePage(ActionEvent event) throws IOException {
        loadAndShowScene("/fxml/AnimalView.fxml", event);
    }

    @FXML
    private void goToAdoptions(ActionEvent event) throws IOException {
        loadAndShowScene("/fxml/AdoptionsView.fxml", event);
    }

    @FXML
    private void goToVolunteers(ActionEvent event) throws IOException {
        loadAndShowScene("/fxml/VolunteerView.fxml", event);
    }

    @FXML
    private void goToMedical(ActionEvent event) throws IOException {
        loadAndShowScene("/fxml/MedicalRecordView.fxml", event);
    }

    @FXML
    private void goToMedicalForm(ActionEvent event) throws IOException {
        loadAndShowScene("/fxml/MedicalFormView.fxml", event);
    }

    @FXML
    private void goToAddAnimal(ActionEvent event) throws IOException {
        loadAndShowScene("/fxml/AddAnimalView.fxml", event);
    }

    /**
     * Helper method to load FXML views while using Spring context for injection.
     */
    private void loadAndShowScene(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setWidth(1024);
        stage.setHeight(768);

        stage.show();
    }
}