package com.animalshelter.controller;

import com.animalshelter.model.Volunteer;
import com.animalshelter.repositories.VolunteerRepository;
import com.animalshelter.service.VolunteerService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VolunteerController {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private VolunteerService volunteerService;

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
    private ListView<String> volunteerList;

    @FXML
    public void initialize() {
        loadVolunteers();


        addVolunteerButton.setOnAction(e -> {
            String volunteerName = nameField.getText();
            String volunteerEmail = emailField.getText();
            String volunteerPhone = phoneField.getText();

            if (!volunteerName.isEmpty() && !volunteerEmail.isEmpty() && !volunteerPhone.isEmpty()) {
                Volunteer newVolunteer = new Volunteer(volunteerName, volunteerEmail, volunteerPhone);
                volunteerService.addVolunteer(newVolunteer);
                loadVolunteers();
                nameField.clear();
                emailField.clear();
                phoneField.clear();

            }
        });
    }

    private void loadVolunteers() {
        volunteerList.getItems().clear();

        try {
            List<Volunteer> volunteers = volunteerService.getAllVolunteers();

            if (volunteers.isEmpty()) {
                volunteerList.getItems().add("No volunteers found");
            } else {
                for (Volunteer volunteer : volunteers) {
                    volunteerList.getItems().add(
                            volunteer.getName() + " | " + volunteer.getEmail() + " | " + volunteer.getPhone()
                    );
                }
            }
        } catch (Exception e) {
            volunteerList.getItems().add("Failed to load volunteers: " + e.getMessage());
        }

    }

//    private void findVolunteerByEmail(String email) {
//        volunteerService.getVolunteerByEmail(email);
//        if (volunteerService.getVolunteerByEmail(email) != null) {
//            volunteerList.getItems().clear();
//        } else {
//            volunteerList.getItems().add("No volunteers found");
//        }
//    }
}
