package com.animalshelter.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animal_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;

    @Column(nullable = false)
    private String animalName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Species animalSpecies;

    @Column(nullable = false)
    private int animalAge;

    @Enumerated(EnumType.STRING)
    private Sex animalSex;

    @Column(nullable = false)
    private String animalBreed;

    @Enumerated(EnumType.STRING)
    private Size animalSize;

    private String animalColor;

    private boolean isAdopted;

    @Embedded
    private MedicalRecord medicalRecord = new MedicalRecord();

    // Default constructor without parameters
    public Animal() {}

    // Constructor with parameters
    public Animal(String aName, Species aSpecies, int aAge, Sex aSex, String aBreed, Size aSize, String aColor) {
        setName(aName);
        setSpecies(aSpecies);
        setAge(aAge);
        setAnimalSex(aSex);
        setBreed(aBreed);
        setSize(aSize);
        setColor(aColor);

        setAdopted(false);
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public String getName() {
        return animalName;
    }
    public void setName(String animalName) {
        if(!animalName.isEmpty()){
            this.animalName = animalName;
        } else {
            throw new IllegalArgumentException("Animal Name cannot be empty");
        }
    }

    public Species getSpecies() {
        return animalSpecies;
    }
    public void setSpecies(Species aSpecies) {
        if(aSpecies != null){
            this.animalSpecies = aSpecies;
        } else {
            throw new IllegalArgumentException("Animal Species cannot be empty");
        }
    }

    public int getAge() {
        return animalAge;
    }
    public void setAge(int animalAge) {
        if(animalAge >= -1){
            this.animalAge = animalAge;
        } else {
            throw new IllegalArgumentException("Animal Age cannot be negative (except -1 for unknown age)");
        }
    }

    public Sex getAnimalSex() {
        return animalSex;
    }
    public void setAnimalSex(Sex animalSex) {
        if(animalSex != null){
            this.animalSex = animalSex;
        } else {
            throw new IllegalArgumentException("Animal Gender cannot be empty");
        }
    }

    public String getBreed() {
        return animalBreed;
    }
    public void setBreed(String animalBreed) {
        if(!animalBreed.isEmpty()){
            this.animalBreed = animalBreed;
        } else {
            throw new IllegalArgumentException("Animal Breed cannot be empty");
        }
    }

    public Size getSize() {
        return animalSize;
    }
    public void setSize(Size aSize) {
        if(aSize != null){
            this.animalSize = aSize;
        } else {
            throw new IllegalArgumentException("Animal Size must be either; Small, Medium, or Large");
        }
    }

    public String getColor() {
        return animalColor;
    }
    public void setColor(String aColor) {
        if(!aColor.isEmpty()){
            this.animalColor = aColor;
        } else {
            throw new IllegalArgumentException("Animal Color cannot be empty");
        }
    }

    public boolean isAdopted() {
        return isAdopted;
    }
    public void setAdopted(boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    public Long getAnimalId() {
        return animalId;
    }

    // Abstract Method
    public abstract void displayInfo();
}
