package com.animalshelter.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * MedicalEntry class to create store an Animal's medical visits.
 * Object attributes to describe the Animal's medical visit, the veterinarian name and the date of the visit.
 * Default and Parameterized constructors for object instantiation.
 * Getters for all object attributes.
 */
@Entity
@Table(name = "animal_medical_entries")
public class MedicalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String vetName;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    /**
     * Default MedicalEntry Constructor without parameters.
     */
    public MedicalEntry() {}

    /**
     * MedicalEntry Constructor with parameters.
     */
    public MedicalEntry(String description, String vetName, LocalDate date, Animal animal) {
        this.description = description;
        this.vetName = vetName;
        this.date = date;
        this.animal = animal;
    }

    /**
     * Method to get a MedicalEntry's description.
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to get a MedicalEntry's veterinarian name.
     * @return String vetName
     */
    public String getVeteranName() {
        return vetName;
    }

    /**
     * Method to get a MedicalEntry's date.
     * @return LocalDate date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Method to get a MedicalEntry's animal.
     * @return Animal animal
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Method to print a MedicalEntry's information.
     */
    public void displayEntry() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("- " + getDescription() + " | Date: " + getDate().format(formatter) + " | Vet: Dr. " + getVeteranName());
    }
}
