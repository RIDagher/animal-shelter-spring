package com.animalshelter.controller;

import com.animalshelter.behavioral.StrategyPattern.NameSearchStrategy;
import com.animalshelter.behavioral.StrategyPattern.SearchStrategy;
import com.animalshelter.behavioral.StrategyPattern.SpeciesSearchStrategy;
import com.animalshelter.domain.animals.Animal;
import com.animalshelter.domain.animals.enums.Species;
import com.animalshelter.repositories.AnimalRepository;
import com.animalshelter.service.AnimalService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Component
public class AnimalController {

    private final AnimalService animalService;
    @FXML private Button btnCats;
    @FXML private Button btnDogs;
    @FXML private Button btnBirds;
    @FXML private GridPane animalGrid;
    @FXML private Button prevButton;
    @FXML private Button nextButton;
    @FXML private TextField searchNameField;

    private final AnimalRepository animalRepository;
    private final ApplicationContext springContext;

    private List<Animal> currentAnimalList;
    private int currentPage = 0;
    private final int itemsPerPage = 4;



    @Autowired
    public AnimalController(AnimalRepository animalRepository, ApplicationContext springContext, AnimalService animalService) {
        this.animalRepository = animalRepository;
        this.springContext = springContext;
        this.animalService = animalService;
    }

    @FXML
    public void initialize() {
        loadAnimals("All");
    }

    @FXML
    private void handleCatClick() {
        loadAnimals("Cat");
    }

    @FXML
    private void handleDogClick() {
        loadAnimals("Dog");
    }

    @FXML
    private void handleBirdClick() {
        loadAnimals("Bird");
    }

    @FXML
    private void handlePreviousPage() {
        if (currentPage > 0) {
            currentPage--;
            updateGrid();
        }
    }

    @FXML
    private void handleNextPage() {
        if ((currentPage + 1) * itemsPerPage < currentAnimalList.size()) {
            currentPage++;
            updateGrid();
        }
    }

    @FXML
    private void handleNameSearch() {
        String query = searchNameField.getText().trim();
        if (!query.isEmpty()) {
            NameSearchStrategy strategy = new NameSearchStrategy(query);
            currentAnimalList = animalService.searchAnimals(strategy);
            currentPage = 0;
            updateGrid();
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

    private void loadAnimals(String type) {
        if (type.equals("All")) {
            currentAnimalList = animalRepository.findAll();
        } else {
            // CONVERTING STRING TO SPECIES TYPE TO FILTER ANIMALS BY TYPE

            Species species = Species.valueOf(type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase());
            SearchStrategy strategy = new SpeciesSearchStrategy(species);
            currentAnimalList = animalRepository.findByAnimalSpecies(species);
        }
        currentPage = 0;
        updateGrid();
    }

    private void updateGrid() {
        animalGrid.getChildren().clear();

        int start = currentPage * itemsPerPage;
        int end = Math.min(start + itemsPerPage, currentAnimalList.size());

        int col = 0;
        int row = 0;

        for (int i = start; i < end; i++) {
            Animal animal = currentAnimalList.get(i);

            VBox card = new VBox();
            card.getStyleClass().add("animal-card");
            card.setSpacing(8);

            Label name = new Label("Name: " + animal.getName());
            Label age = new Label("Age: " + animal.getAge());
            Label species = new Label("Species: " + animal.getSpecies());
            Label breed = new Label("Breed: " + animal.getBreed());
            Label sex = new Label("Sex: " + animal.getAnimalSex());
            Label color = new Label("Color: " + animal.getColor());
            Label adopted = new Label("Status: " + (animal.isAdopted() ? "Adopted" : "Available"));

            name.getStyleClass().add("animal-name");
            adopted.getStyleClass().add("animal-adopted");

            card.getChildren().addAll(name, age, species, breed, sex, color, adopted);
            animalGrid.add(card, col, row);



            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }

        prevButton.setDisable(currentPage == 0);
        nextButton.setDisable(end >= currentAnimalList.size());
    }


}
