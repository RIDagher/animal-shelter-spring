package com.animalshelter;

import com.animalshelter.model.*;
import com.animalshelter.repositories.AnimalRepository;
import com.animalshelter.repositories.MedicalEntryRepository;
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
    public CommandLineRunner demo(AnimalRepository animalRepo, MedicalEntryRepository medicalRepo) {
        return args -> {
            //System.out.println("Bird Database Creation");


            //Bird parrot = new Bird("J", 6, Sex.Female, "Pigeon",
                    //Size.Small, "Grey", true, "Curved");

            //animalRepo.save(parrot);


            //MedicalEntry entry = new MedicalEntry(
                    //"Vaccinated 2023-01-01",
                    //"Dr. Demers",
                    //LocalDate.of(2023, 1, 1),
                    //parrot
            //);
            //medicalRepo.save(entry);

            //System.out.println("Saved bird: " + parrot.getName());

            // Query all the birds
            System.out.println("\nAll birds in database:");
            animalRepo.findAll().forEach(animal -> {
                if (animal instanceof Bird) {
                    System.out.println(animal.getName() + " (ID: " + animal.getAnimalId() + ")");
                }
            });
        };
    }

}
