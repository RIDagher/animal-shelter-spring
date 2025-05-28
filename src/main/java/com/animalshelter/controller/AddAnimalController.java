package com.animalshelter.controller;


import com.animalshelter.domain.animals.Animal;
import com.animalshelter.domain.animals.Bird;
import com.animalshelter.domain.animals.Cat;
import com.animalshelter.domain.animals.Dog;
import com.animalshelter.domain.animals.enums.Sex;
import com.animalshelter.domain.animals.enums.Size;
import com.animalshelter.domain.animals.enums.Species;
import com.animalshelter.service.AnimalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AddAnimalController {

    private final ApplicationContext springContext;
    @FXML private TextField nameField;
    @FXML private Spinner<Integer> ageSpinner;
    @FXML private ComboBox<Species> speciesCombo;
    @FXML private CheckBox trainedCheckbox;
    @FXML private TextField barkVolumeField;
    @FXML private CheckBox litterBoxCheckbox;
    @FXML private TextField temperamentField;
    @FXML private CheckBox canFlyCheckbox;
    @FXML private TextField beakTypeField;
    @FXML private ComboBox<Sex> sexCombo;
    @FXML private TextField breedField;
    @FXML private ComboBox<Size> sizeCombo;
    @FXML private TextField colorField;
    @FXML private VBox dogFields;
    @FXML private VBox catFields;
    @FXML private VBox birdFields;

    private final AnimalService animalService;

    @Autowired
    public AddAnimalController(AnimalService animalService, ApplicationContext springContext) {
        this.animalService = animalService;
        this.springContext = springContext;
    }

    @FXML
    public void initialize() {
        speciesCombo.getItems().setAll(Species.values());
        sexCombo.getItems().setAll(Sex.values());
        sizeCombo.getItems().setAll(Size.values());
        ageSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1));

        dogFields.setVisible(false);
        catFields.setVisible(false);
        birdFields.setVisible(false);

        speciesCombo.valueProperty().addListener((obs, oldVal, newVal) -> updateSpeciesFields(newVal));
    }

    @FXML
    private void handleSave() {
        String name = nameField.getText();
        int age = ageSpinner.getValue();
        Species species = speciesCombo.getValue();
        Sex sex = sexCombo.getValue();
        String breed = breedField.getText();
        Size size = sizeCombo.getValue();
        String color = colorField.getText();

        if (name.isEmpty() || species == null || sex == null || breed.isEmpty() || size == null || color.isEmpty()) {
            showAlert("Please fill in all required fields.");
            return;
        }

        Animal animal;
        switch (species) {
            case Dog -> {
                boolean isTrained = trainedCheckbox.isSelected();
                String barkVolume = barkVolumeField.getText();
                if (barkVolume.isEmpty()) {
                    showAlert("Please enter bark volume for dogs.");
                    return;
                }
                animal = new Dog(name, age, sex, breed, size, color, isTrained, barkVolume);
            }
            case Cat -> {
                boolean isLitterBoxTrained = litterBoxCheckbox.isSelected();
                String temperament = temperamentField.getText();
                if (temperament.isEmpty()) {
                    showAlert("Please enter temperament for cats.");
                    return;
                }
                animal = new Cat(name, age, sex, breed, size, color, isLitterBoxTrained, temperament);
            }
            case Bird -> {
                boolean canFly = canFlyCheckbox.isSelected();
                String beakType = beakTypeField.getText();
                if (beakType.isEmpty()) {
                    showAlert("Please enter beak type for birds.");
                    return;
                }
                animal = new Bird(name, age, sex, breed, size, color, canFly, beakType);
            }
            default -> {
                showAlert("Invalid species selected.");
                return;
            }
        }

        animalService.saveAnimal(animal);
        showAlert("Animal added successfully!");
        clearForm();
    }

    private void updateSpeciesFields(Species species) {
        dogFields.setVisible(species == Species.Dog);
        catFields.setVisible(species == Species.Cat);
        birdFields.setVisible(species == Species.Bird);
    }



    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void clearForm() {
        nameField.clear();
        ageSpinner.getValueFactory().setValue(1);
        speciesCombo.getSelectionModel().clearSelection();
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

    @FXML
    private void goToAddAnimal(ActionEvent event) throws IOException {
        loadAndShowScene("/fxml/AddAnimalView.fxml", event);
    }

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
