package com.animalshelter;

import com.animalshelter.model.*;
import com.animalshelter.repositories.AnimalRepository;
import com.animalshelter.repositories.MedicalEntryRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
public class AnimalShelterApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(AnimalShelterApplication.class)
                .web(org.springframework.boot.WebApplicationType.NONE)
                .run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AnimalView.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        primaryStage.setTitle("Animal Shelter");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(768);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.close();
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Bean
    public CommandLineRunner demo(AnimalRepository animalRepo, MedicalEntryRepository medicalRepo) {
        return args -> {

        };
    }

}
