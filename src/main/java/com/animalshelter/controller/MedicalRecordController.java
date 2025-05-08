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
import org.springframework.context.ApplicationContext;
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

    private final AnimalRepository animalRepository;
    private final ApplicationContext springContext;

    @Autowired
    public MedicalRecordController(AnimalRepository animalRepository, ApplicationContext springContext) {
        this.animalRepository = animalRepository;
        this.springContext = springContext;
    }

    @FXML
    public void initialize() {
        List<Animal> animals = animalRepository.findAll();
        for (Animal animal : animals) {
            animalSelector.getItems().add(animal.getName());
        }
    }

    @FXML
    private void onAnimalSelected(ActionEvent event) {
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
                    animal.getMedicalEntries()
                            .stream()
                            .filter(m -> m.getDescription().toLowerCase().contains("vaccine"))
                            .map(m -> m.getDate() + " - " + m.getDescription() + " (by " + m.getVeteranName() + ")")
                            .toList()
            );

            treatmentList.getItems().setAll(
                    animal.getMedicalEntries()
                            .stream()
                            .filter(m -> !m.getDescription().toLowerCase().contains("vaccine"))
                            .map(m -> m.getDate() + " - " + m.getDescription() + " (by " + m.getVeteranName() + ")")
                            .toList()
            );
        }
    }

    @FXML
    private void handleAddVaccination() {
        System.out.println("Add vaccination clicked");
    }

    @FXML
    private void handleAddTreatment(ActionEvent event) {
        System.out.println("Add treatment clicked!");
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
