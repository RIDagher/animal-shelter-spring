package com.animalshelter.domain.medical;

import com.animalshelter.domain.animals.Animal;
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
        setDescription(description);
        setVeteranName(vetName);
        setDate(date);
        setAnimal(animal);
    }

    /**
     * Method to get a MedicalEntry's description.
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to set a MedicalEntry's description
     * @param description
     */
    public void setDescription(String description) {
        if (description != null && description.length() > 3) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Description cannot be empty");
        }
    }

    /**
     * Method to get a MedicalEntry's veterinarian name.
     * @return String vetName
     */
    public String getVeteranName() {
        return vetName;
    }

    public void setVeteranName(String vetName) {
        if (vetName != null && vetName.length() > 3) {
            this.vetName = vetName;
        } else {
            throw new IllegalArgumentException("VetName cannot be empty");
        }
    }

    /**
     * Method to get a MedicalEntry's date.
     * @return LocalDate date
     */
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
        } else {
            throw new IllegalArgumentException("Date cannot be empty");
        }
    }

    /**
     * Method to get a MedicalEntry's animal.
     * @return Animal animal
     */
    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        if (animal != null) {
            this.animal = animal;
        } else {
            throw new IllegalArgumentException("Animal cannot be empty");
        }
    }

    /**
     * Method to print a MedicalEntry's information.
     */
    public void displayEntry() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("- " + getDescription() + " | Date: " + getDate().format(formatter) + " | Vet: Dr. " + getVeteranName());
    }
}
