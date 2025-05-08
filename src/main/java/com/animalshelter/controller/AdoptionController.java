package com.animalshelter.controller;

import com.animalshelter.model.AdoptionForm;
import com.animalshelter.model.Animal;
import com.animalshelter.repositories.AnimalRepository;
import com.animalshelter.service.AdoptionService;
import javafx.collections.FXCollections;
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

@Component
public class AdoptionController {

    // Fxml file elements
    @FXML private ComboBox<Animal> animalComboBox;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private CheckBox hasPetsCheckBox;
    @FXML private ListView<AdoptionForm> pendingAdoptionsListView;

    // Initializing Repositories and ApplicationContext
    private final AdoptionService adoptionService;
    private final AnimalRepository animalRepository;
    private final ApplicationContext springContext;

    @Autowired
    public AdoptionController(AdoptionService adoptionService, AnimalRepository animalRepository,
                              ApplicationContext springContext) {
        this.adoptionService = adoptionService;
        this.animalRepository = animalRepository;
        this.springContext = springContext;
    }

    // Loading animal data into the view
    @FXML
    public void initialize() {
        // Only load animals that are not yet adopted
        animalComboBox.setItems(FXCollections.observableArrayList(animalRepository.findByIsAdoptedFalse()));

        // Formating the animals combo box list options
        animalComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null :
                        String.format("%s (%s)", item.getName(), item.getClass().getSimpleName()));
            }
        });

        // Formating the selected option in the animals combo box
        animalComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null :
                        String.format("%s (%s)", item.getName(), item.getClass().getSimpleName()));
            }
        });

        // Refresh the pending adoptions section
        refreshPendingAdoptions();
    }

    // Method to create an adoption form
    @FXML
    private void handleSubmit() {
        // Try catch block for error handling
        try {
            Animal selectedAnimal = animalComboBox.getValue();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            boolean hasPets = hasPetsCheckBox.isSelected();

            // Validate the inputs
            if (selectedAnimal == null) {
                showAlert("Error", "Please select an animal");
                return;
            }
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                showAlert("Error", "Please fill all required fields");
                return;
            }

            // Create the adoption form using AdoptionService class
            AdoptionForm form = adoptionService.submitAdoptionForm(
                    name, email, phone, address, hasPets, selectedAnimal.getId()
            );

            // Successful form submission actions
            clearForm();
            refreshPendingAdoptions();
            showAlert("Success", "Adoption form submitted successfully!");

        } catch (Exception e) {
            // Showing error message in case of failure
            showAlert("Error", "Failed to submit form: " + e.getMessage());
        }
    }

    // Method to approve an adoption that is pending
    @FXML
    private void handleApprove() {
        // Getting the pending adoption
        AdoptionForm selected = pendingAdoptionsListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            adoptionService.approveAdoption(selected.getAdoptionId());
            refreshPendingAdoptions();
            showAlert("Approved", "Adoption approved successfully!");
        } else {
            showAlert("Error", "Please select an adoption to approve");
        }
    }

    // Method to reject an adoption that is pending
    @FXML
    private void handleReject() {
        // Getting the pending adoption
        AdoptionForm selected = pendingAdoptionsListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            adoptionService.rejectAdoption(selected.getAdoptionId());
            refreshPendingAdoptions();
            showAlert("Rejected", "Adoption rejected");
        } else {
            showAlert("Error", "Please select an adoption to reject");
        }
    }

    // Method to refresh the list of pending adoptions
    private void refreshPendingAdoptions() {
        // Getting all the entries of pending adoption forms
        pendingAdoptionsListView.setItems(FXCollections.observableArrayList(adoptionService.getPendingAdoptions()));

        // Updating the content of the pending adoption forms
        pendingAdoptionsListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(AdoptionForm item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%s wants to adopt %s", item.getApplicantName(),item.getAnimal().getName()));
                }
            }
        });
    }

    // Method to clear all the fxml input fields
    private void clearForm() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        hasPetsCheckBox.setSelected(false);
        animalComboBox.getSelectionModel().clearSelection();
    }

    // Method to either show a success message or an error message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //Methods for the navbar
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