package com.animalshelter.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Animal abstract superclass.
 * Object attributes that are universally shared and relevant to animals.
 * Default and Parameterized constructors for object instantiation.
 * Getters and setter for all object attributes.
 * Abstract method to display information on the Animal.
 */
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

    // DEPRECATED WITH DATABASE INTEGRATION
    @Transient
    private MedicalRecord medicalRecord = new MedicalRecord(this);

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MedicalEntry> medicalEntries = new ArrayList<>();

    /**
     * Default Animal Constructor without parameters.
     */
    public Animal() {}

    /**
     * Animal Constructor with parameters, set the animal's adopted status to false by default.
     * @param aName
     * @param aSpecies
     * @param aAge
     * @param aSex
     * @param aBreed
     * @param aSize
     * @param aColor
     */
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

    /**
     * Method to get the animal's ID.
     * @return Long animalId
     */
    public Long getAnimalId() {
        return animalId;
    }
    public Long getId() {return animalId;}

    /**
     * Method to get the animal's medical record.
     * @return MedicalRecord medicalRecord
     */
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    /**
     * Method to get all the animal's medical entries.
     * @return List<MedicalEntry> medicalEntries
     */
    public List<MedicalEntry> getMedicalEntries() {
        return medicalEntries;
    }

    /**
     * Method to get the animal's nickname.
     * @return String animalName
     */
    public String getName() {
        return animalName;
    }

    /**
     * Method to set the animal's nickname.
     * @param animalName
     */
    public void setName(String animalName) {
        if(!animalName.isEmpty()){
            this.animalName = animalName;
        } else {
            throw new IllegalArgumentException("Animal Name cannot be empty");
        }
    }

    /**
     * Method to get the animal's species.
     * @return Species animalSpecies
     */
    public Species getSpecies() {
        return animalSpecies;
    }

    /**
     * Method to set the animal's species.
     * @param aSpecies
     */
    public void setSpecies(Species aSpecies) {
        if(aSpecies != null){
            this.animalSpecies = aSpecies;
        } else {
            throw new IllegalArgumentException("Animal Species cannot be empty");
        }
    }

    /**
     * Method to get the animal's age.
     * @return int animalAge
     */
    public int getAge() {
        return animalAge;
    }

    /**
     * Method to set the animal's age.
     * @param animalAge
     */
    public void setAge(int animalAge) {
        if(animalAge >= -1){
            this.animalAge = animalAge;
        } else {
            throw new IllegalArgumentException("Animal Age cannot be negative (except -1 for unknown age)");
        }
    }

    /**
     * Method to get the animal's sex.
     * @return Sex animalSex
     */
    public Sex getAnimalSex() {
        return animalSex;
    }

    /**
     * Method to set the animal's sex.
     * @param animalSex
     */
    public void setAnimalSex(Sex animalSex) {
        if(animalSex != null){
            this.animalSex = animalSex;
        } else {
            throw new IllegalArgumentException("Animal Gender cannot be empty");
        }
    }

    /**
     * Method to get the animal's breed.
     * @return String animalBreed
     */
    public String getBreed() {
        return animalBreed;
    }

    /**
     * Method to set the animal's breed.
     * @param animalBreed
     */
    public void setBreed(String animalBreed) {
        if(!animalBreed.isEmpty()){
            this.animalBreed = animalBreed;
        } else {
            throw new IllegalArgumentException("Animal Breed cannot be empty");
        }
    }

    /**
     * Method to get the animal's size.
     * @return Size animalSize
     */
    public Size getSize() {
        return animalSize;
    }

    /**
     * Method to set the animal's size.
     * @param aSize
     */
    public void setSize(Size aSize) {
        if(aSize != null){
            this.animalSize = aSize;
        } else {
            throw new IllegalArgumentException("Animal Size must be either; Small, Medium, or Large");
        }
    }

    /**
     * Method to get the animal's color.
     * @return String animalColor
     */
    public String getColor() {
        return animalColor;
    }

    /**
     * Method to set the animal's color.
     * @param aColor
     */
    public void setColor(String aColor) {
        if(!aColor.isEmpty()){
            this.animalColor = aColor;
        } else {
            throw new IllegalArgumentException("Animal Color cannot be empty");
        }
    }

    /**
     * Method to get the animal's adoption status.
     * @return boolean isAdopted
     */
    public boolean isAdopted() {
        return isAdopted;
    }

    /**
     * Method to set the animal's adoption status.
     * @param isAdopted
     */
    public void setAdopted(boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    /**
     * Abstract method to display information on the animal.
     */
    public abstract void displayInfo();
}
