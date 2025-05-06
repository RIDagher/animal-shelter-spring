package com.animalshelter.controller;

import com.animalshelter.model.Animal;
import com.animalshelter.model.Species;
import com.animalshelter.repositories.AnimalRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;

@Component
public class AnimalController {

    @FXML
    private Button btnCats;
    @FXML
    private Button btnDogs;
    @FXML
    private Button btnBirds;
    @FXML
    private GridPane animalGrid;

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
    private void goToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AnimalView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void initialize() {
        loadAnimals("All");
    }

    @Autowired
    private AnimalRepository animalRepository;


    private void loadAnimals(String type) {
        animalGrid.getChildren().clear();

        List<Animal> animals;

        if (type.equals("All")) {
            animals = animalRepository.findAll();
        } else {
            Species species = Species.valueOf(type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase());
            animals = animalRepository.findByAnimalSpecies(species);
        }

        int col = 0;
        int row = 0;

        for (int i = 0; i < Math.min(animals.size(), 6); i++) {

            Animal animal = animals.get(i);

            VBox card = new VBox();
            card.getStyleClass().add("animal-card");
            card.setSpacing(5);

            Label name = new Label(animal.getName());
            Label age = new Label("Age: " + animal.getAge());

            card.getChildren().addAll(name, age);
            animalGrid.add(card, col, row);

            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }
}