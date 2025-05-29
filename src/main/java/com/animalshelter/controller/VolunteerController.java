package com.animalshelter.controller;

import com.animalshelter.domain.volunteers.Volunteer;
import com.animalshelter.repositories.VolunteerRepository;
import com.animalshelter.service.VolunteerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    @FXML private ListView<Volunteer> volunteerList;

    /**
     * Initializes the Volunteer view with default behavior.
     */
    @FXML
    public void initialize() {
        loadVolunteers();
        setUpSearchHandler();
        setupSelectionListener();
        resetForm();

        addVolunteerButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
                Volunteer newVolunteer = new Volunteer(name, email, phone);
                try {
                    volunteerService.addVolunteer(newVolunteer);
                    loadVolunteers();
                    clearForm();
                } catch (RuntimeException ex) {
                    volunteerList.getItems().clear();
                    volunteerList.getItems().add(new Volunteer("Error: " + ex.getMessage(), "", ""));
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
            List<Volunteer> volunteers = volunteerService.getAllVolunteers();
            volunteerList.getItems().addAll(volunteers);
        } catch (Exception e) {
            System.out.println("Error loading volunteers: " + e.getMessage());
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
                                () -> volunteerList.getItems().add(new Volunteer("No volunteer found", "", ""))
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
            volunteerList.getItems().add(new Volunteer("Enter a Name or Email to search", "", ""));
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
     * Clears form fields for a clean input.
     */
    private void clearForm() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
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