package com.animalshelter.controller;

import com.animalshelter.model.Volunteer;
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

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Button addVolunteerButton;

    @FXML
    private Button searchVolunteerButton;

    @FXML
    private Button resetButton;

    @FXML
    private ListView<String> volunteerList;

    @FXML
    public void initialize() {
        loadVolunteers();
        setUpSearchHandler();
        resetForm();


        addVolunteerButton.setOnAction(e -> {
            String volunteerName = nameField.getText();
            String volunteerEmail = emailField.getText();
            String volunteerPhone = phoneField.getText();

            if (!volunteerName.isEmpty() && !volunteerEmail.isEmpty() && !volunteerPhone.isEmpty()) {
                Volunteer newVolunteer = new Volunteer(volunteerName, volunteerEmail, volunteerPhone);
                try {
                    volunteerService.addVolunteer(newVolunteer);
                    loadVolunteers();
                    nameField.clear();
                    emailField.clear();
                    phoneField.clear();

                    // Return Error to user if email already exists in database
                } catch (RuntimeException exception) {
                    volunteerList.getItems().clear();
                    volunteerList.getItems().add("Error: " + exception.getMessage());
                }
            }
        });
    }

    // loadVolunteers method to display all volunteers
    private void loadVolunteers() {
        volunteerList.getItems().clear();

        try {
            List<Volunteer> volunteers = volunteerService.getAllVolunteers();

            if (volunteers.isEmpty()) {
                volunteerList.getItems().add("No volunteers found");
            } else {
                for (Volunteer volunteer : volunteers) {
                    String display = String.format(
                            "👤 %-20s\n📧 %-25s\n📞 %s",
                            volunteer.getName(),
                            volunteer.getEmail(),
                            volunteer.getPhone()
                    );
                    volunteerList.getItems().add(display);
                }
            }
        } catch (Exception e) {
            volunteerList.getItems().add("Failed to load volunteers: " + e.getMessage());
        }
    }


    // setUpSearchHandler method the search a volunteer by name and by email
    private void setUpSearchHandler() {
        searchVolunteerButton.setOnAction(e -> {
            volunteerList.getItems().clear();


            if (!emailField.getText().isEmpty()) {
                // Search by email
                volunteerService.getVolunteerByEmail(emailField.getText()).ifPresent(volunteer -> {
                    nameField.setText(volunteer.getName());
                    emailField.setText(volunteer.getEmail());
                    phoneField.setText(volunteer.getPhone());
                    String display = String.format(         "👤 %-20s\n📧 %-25s\n📞 %s",
                            volunteer.getName(),
                            volunteer.getEmail(),
                            volunteer.getPhone()
                    );
                    volunteerList.getItems().add(display);

                });
            } else if (!nameField.getText().isEmpty()) {
                // Search by name
                List<Volunteer> result = volunteerService.getVolunteerByName(nameField.getText());
                if (result.isEmpty()) {
                    volunteerList.getItems().add("No volunteers found");
                } else {
                    for (Volunteer volunteer : result) {
                        String display = String.format(         "👤 %-20s\n📧 %-25s\n📞 %s",
                                volunteer.getName(),
                                volunteer.getEmail(),
                                volunteer.getPhone()
                        );
                        volunteerList.getItems().add(display);
                    }
                }
            } else {
                volunteerList.getItems().add("Enter a Name or Email to search for volunteers");
            }
        });
    }

    // resetForm method to reset the volunteer form
    private void resetForm() {

        resetButton.setOnAction(e -> {
            // Clear List
            volunteerList.getItems().clear();

            // Clear Fields
            nameField.clear();
            emailField.clear();
            phoneField.clear();

            // Load Volunteer List
            loadVolunteers();
        });
    }

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

    private void loadAndShowScene(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.show();
    }
}

