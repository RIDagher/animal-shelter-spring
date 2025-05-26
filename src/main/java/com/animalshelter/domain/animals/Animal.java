package com.animalshelter.domain.animals;

import com.animalshelter.domain.animals.enums.Sex;
import com.animalshelter.domain.animals.enums.Size;
import com.animalshelter.domain.animals.enums.Species;
import com.animalshelter.domain.medical.MedicalEntry;
import com.animalshelter.domain.medical.MedicalRecord;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animal_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "animals")
public abstract class Animal implements Serializable {
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

    @Column(nullable = false)
    private String animalColor;

    @Column(nullable = false)
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

<<<<<<< Updated upstream:src/main/java/com/animalshelter/model/Animal.java
=======
    /**
     * Method to get the animal's ID.
     * @return Long animalId
     */
    public Long getAnimalId() {
        return animalId;
    }
    public Long getId() {return animalId;}

    /**
     * Method to set the animal's ID.
     * @param id
     */
    public void setAnimalId(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Animal ID must be positive");
        } else {
            this.animalId = id;
        }
    }

    /**
     * Method to get the animal's medical record.
     * @return MedicalRecord medicalRecord
     */
>>>>>>> Stashed changes:src/main/java/com/animalshelter/domain/animals/Animal.java
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
