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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            /*
            // Create lists of animals
            List<Animal> animals = new ArrayList<>();

            // Birds
            animals.add(new Bird("Snowie", 6, Sex.Female, "Pigeon",
                    Size.Small, "Grey", true, "Curved"));
            animals.add(new Bird("Polly", 2, Sex.Male, "Parrot",
                    Size.Medium, "Green", true, "Hooked"));
            animals.add(new Bird("Tweety", 1, Sex.Male, "Canary",
                    Size.Small, "Yellow", true, "Short"));
            animals.add(new Bird("Hawk", 4, Sex.Female, "Falcon",
                    Size.Large, "Brown", true, "Sharp"));

            // Cats
            animals.add(new Cat("Whiskers", 3, Sex.Male, "Siamese",
                    Size.Medium, "White", true, "Playful"));
            animals.add(new Cat("Mittens", 2, Sex.Female, "Tabby",
                    Size.Small, "Orange", true, "Friendly"));
            animals.add(new Cat("Shadow", 5, Sex.Male, "Persian",
                    Size.Medium, "Black", false, "Shy"));
            animals.add(new Cat("Luna", 1, Sex.Female, "Ragdoll",
                    Size.Medium, "Grey", true, "Calm"));

            // Dogs
            animals.add(new Dog("Buddy", 4, Sex.Male, "Labrador",
                    Size.Large, "Golden", true, "Medium"));
            animals.add(new Dog("Max", 2, Sex.Male, "Beagle",
                    Size.Medium, "Brown", true, "Loud"));
            animals.add(new Dog("Bella", 3, Sex.Female, "Poodle",
                    Size.Small, "White", false, "High"));
            animals.add(new Dog("Rocky", 5, Sex.Male, "Bulldog",
                    Size.Medium, "Brindle", true, "Low"));

            // Save all animals in a batch
            animalRepo.saveAll(animals);
             */
        };
    }

}
