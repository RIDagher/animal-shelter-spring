package com.animalshelter.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public MedicalEntry() {}

    public MedicalEntry(String description, String vetName, LocalDate date, Animal animal) {
        this.description = description;
        this.vetName = vetName;
        this.date = date;
        this.animal = animal;
    }

    public String getDescription() {
        return description;
    }

    public String getVeteranName() {
        return vetName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void displayEntry() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("- " + getDescription() + " | Date: " + getDate().format(formatter) + " | Vet: Dr. " + getVeteranName());
    }
}
