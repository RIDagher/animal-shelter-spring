package com.animalshelter.model;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AnimalShelterApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Animal Shelter Management");

        Button listAnimalsButton = new Button("List Animals");
        Button adoptAnimalButton = new Button("Adopt Animal");

    }

    public static void main(String[] args) {
        launch(args); }
}
