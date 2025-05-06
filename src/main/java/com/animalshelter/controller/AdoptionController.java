package com.animalshelter.controller;

import com.animalshelter.repositories.AdoptionFormRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;

import java.io.IOException;

@Component
public class AdoptionController {

    private ConfigurableApplicationContext springContext;

    @Autowired
    private AdoptionFormRepository adoptionFormRepository;

    @FXML
    private void goToHomePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AnimalView.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
