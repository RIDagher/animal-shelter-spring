package com.animalshelter.controller;

import com.animalshelter.model.Animal;
import com.animalshelter.model.MedicalEntry;
import com.animalshelter.repositories.AnimalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MedicalFormController {

    @FXML private ComboBox<Animal> animalComboBox;
    @FXML private TextField vetNameField;
    @FXML private DatePicker datePicker;
    @FXML private TextArea descriptionArea;

    @Autowired
    private AnimalRepository animalRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @FXML
    public void initialize() {
        // Load all animals into the combo box
        animalComboBox.setItems(FXCollections.observableArrayList(animalRepository.findAll()));

        animalComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " (" + item.getClass().getSimpleName() + ")");
                }
            }
        });

        // Set button cell to display selected item properly
        animalComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Animal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " (" + item.getClass().getSimpleName() + ")");
                }
            }
        });

        // Set default date to today
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleSave() {
        try {
            Animal selectedAnimal = animalComboBox.getSelectionModel().getSelectedItem();
            String vetName = vetNameField.getText();
            LocalDate date = datePicker.getValue();
            String description = descriptionArea.getText();

            // Validate inputs
            if (selectedAnimal == null) {
                showAlert("Error", "Please select an animal");
                return;
            }
            if (vetName == null || vetName.trim().isEmpty()) {
                showAlert("Error", "Please enter veterinarian name");
                return;
            }
            if (date == null) {
                showAlert("Error", "Please select a date");
                return;
            }

            // Reattach the animal to the current session
            Animal managedAnimal = entityManager.merge(selectedAnimal);

            // Create new medical entry
            MedicalEntry entry = new MedicalEntry();
            entry.setDescription(description);
            entry.setVetName(vetName);
            entry.setDate(date);

            // Add to animal's medical records
            managedAnimal.getMedicalRecord().addMedicalEntry(entry);
            animalRepository.save(selectedAnimal);

            // Close the form
            ((Stage) animalComboBox.getScene().getWindow()).close();

        } catch (Exception e) {
            showAlert("Error", "Failed to save medical record: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        ((Stage) animalComboBox.getScene().getWindow()).close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}