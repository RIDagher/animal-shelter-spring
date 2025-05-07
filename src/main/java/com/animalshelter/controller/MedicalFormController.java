package com.animalshelter.controller;

import com.animalshelter.model.Animal;
import com.animalshelter.repositories.AnimalRepository;
import com.animalshelter.repositories.MedicalEntryRepository;
import com.animalshelter.service.MedicalEntryService;
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
import java.time.LocalDate;
import java.util.List;

@Component
public class MedicalFormController {

    @FXML private ComboBox<Animal> animalComboBox;
    @FXML private TextField vetNameField;
    @FXML private DatePicker datePicker;
    @FXML private TextArea descriptionArea;

    private final MedicalEntryService medicalEntryService;
    private final AnimalRepository animalRepository;
    private final ApplicationContext springContext;

    @Autowired
    public MedicalFormController(MedicalEntryService medicalEntryService, AnimalRepository animalRepository,
                                 ApplicationContext springContext) {
        this.medicalEntryService = medicalEntryService;
        this.animalRepository = animalRepository;
        this.springContext = springContext;
    }

    @FXML
    public void initialize() {
        animalComboBox.setItems(FXCollections.observableArrayList(
                animalRepository.findAll()
        ));

        // Format animal display
        animalComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null :
                        String.format("%s (%s)", item.getName(), item.getClass().getSimpleName()));
            }
        });

        animalComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null :
                        String.format("%s (%s)", item.getName(), item.getClass().getSimpleName()));
            }
        });

        // Set default date to today
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleSave() {
        try {
            Animal selectedAnimal = animalComboBox.getValue();
            String vetName = vetNameField.getText().trim();
            LocalDate date = datePicker.getValue();
            String description = descriptionArea.getText().trim();

            // Validate inputs
            if (selectedAnimal == null) {
                showAlert("Error", "Please select an animal");
                return;
            }
            if (vetName.isEmpty()) {
                showAlert("Error", "Please enter veterinarian name");
                return;
            }
            if (date == null) {
                showAlert("Error", "Please select a date");
                return;
            }

            // Save through service
            medicalEntryService.createMedicalEntry(
                    selectedAnimal.getId(),
                    vetName,
                    date,
                    description
            );

            // Clear form
            vetNameField.clear();
            descriptionArea.clear();
            showAlert("Success", "Medical record saved successfully!");

        } catch (Exception e) {
            showAlert("Error", "Failed to save record: " + e.getMessage());
            e.printStackTrace();
        }
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

    private void loadAndShowScene(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
