package com.animalshelter;

import com.animalshelter.model.*;
import com.animalshelter.repositories.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
public class AnimalShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalShelterApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AnimalRepository repository) {
        return args -> {
            System.out.println("Bird Database Creation");

            // Create and save a bird
            Bird parrot = new Bird("Polly", 2, Sex.Female, "Parrot",
                    Size.Small, "Green", true, "Curved");
            parrot.getMedicalRecord().addMedicalEntry("Vaccinated 2024-01-01", "Dr. Demers");

            repository.save(parrot);
            System.out.println("Saved bird: " + parrot.getName());

            // Query all the birds
            System.out.println("\nAll birds in database:");
            repository.findAll().forEach(animal -> {
                if (animal instanceof Bird) {
                    System.out.println(animal.getName() + " (ID: " + animal.getAnimalId() + ")");
                }
            });
        };
    }
}
