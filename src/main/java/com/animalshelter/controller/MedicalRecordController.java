package com.animalshelter.controller;

import com.animalshelter.model.Animal;
import com.animalshelter.model.MedicalEntry;
import com.animalshelter.repositories.AnimalRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MedicalRecordController {

    @FXML private ComboBox<String> animalSelector;
    @FXML private Label nameLabel;
    @FXML private Label speciesLabel;
    @FXML private Label ageLabel;
    @FXML private Label breedLabel;
    @FXML private Label healthStatusLabel;
    @FXML private ListView<String> vaccinationList;
    @FXML private ListView<String> treatmentList;


    @Autowired
    private AnimalRepository animalRepository;


    @FXML
    public void initialize() {
        List<Animal> animals = animalRepository.findAll();
        for (Animal animal : animals) {
            animalSelector.getItems().add(animal.getName());
        }

        animalSelector.setOnAction(e -> {
            String selectedName = animalSelector.getValue();
            Animal animal = animalRepository.findAll()
                    .stream()
                    .filter(a -> a.getName().equals(selectedName))
                    .findFirst()
                    .orElse(null);

            if (animal != null) {
                nameLabel.setText(animal.getName());
                speciesLabel.setText(animal.getSpecies().toString());
                ageLabel.setText(String.valueOf(animal.getAge()));
                breedLabel.setText(animal.getBreed());
                healthStatusLabel.setText("Unknown");

                vaccinationList.getItems().setAll(
                        animal.getMedicalRecord().getMedicalEntries()
                                .stream()
                                .filter(m -> m.getDescription().toLowerCase().contains("vaccine"))
                                .map(m -> m.getDate() + " - " + m.getDescription() + " (by " + m.getVeteranName() + ")")
                                .toList()
                );

                treatmentList.getItems().setAll(
                        animal.getMedicalRecord().getMedicalEntries()
                                .stream()
                                .filter(m -> !m.getDescription().toLowerCase().contains("vaccine"))
                                .map(m -> m.getDate() + " - " + m.getDescription() + " (by " + m.getVeteranName() + ")")
                                .toList()
                );

            }
        });
    }



    private void loadAnimalData(String animalName) {
        animalRepository.findByAnimalName(animalName).ifPresent(animal -> {
            nameLabel.setText(animal.getName());
            speciesLabel.setText(animal.getSpecies().toString());
            ageLabel.setText(String.valueOf(animal.getAge()));
            breedLabel.setText(animal.getBreed());
            healthStatusLabel.setText(animal.isAdopted() ? "Adopted" : "Available");

            vaccinationList.getItems().clear();
            treatmentList.getItems().clear();

            for (MedicalEntry entry : animal.getMedicalRecord().getMedicalEntries()) {
                String display = entry.getDate() + " - " + entry.getDescription() + " (by " + entry.getVeteranName() + ")";
                if (entry.getDescription().toLowerCase().contains("vaccine")) {
                    vaccinationList.getItems().add(display);
                } else {
                    treatmentList.getItems().add(display);
                }
            }
        });
    }


    @FXML
    private void handleAddVaccination() {
        System.out.println("Add vaccination clicked");
    }

    @FXML
    private void handleAddTreatment() {
        System.out.println("Add treatment clicked");
    }

    @FXML
    private void goToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AnimalView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void goToAdoptions() {
        System.out.println("Go to Adoptions");
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logged out");
    }
}
