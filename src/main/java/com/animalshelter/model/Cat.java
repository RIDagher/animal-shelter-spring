package com.animalshelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

/**
 * Cat subclass.
 * Added object attributes are a boolean for whether the cat is litter box trained and a String for the cat's temperament.
 * Default and Parameterized constructors for object instantiation.
 * Getters and setter for all object attributes.
 * Implements the two methods adopt() and returnToShelter() from the Adoptable interface.
 * Defines the abstract method displayInfo() from the abstract superclass Animal.
 */
@Entity
@DiscriminatorValue("CAT")
public class Cat extends Animal implements Adoptable {
    private boolean isLitterBoxTrained;
    private String temperament;

    /**
     * Default Bird Constructor with no parameters.
     */
    public Cat() {}

    /**
     * Cat constructor with parameters, which uses the Animal superclass constructor.
     * @param aName
     * @param aAge
     * @param aSex
     * @param aBreed
     * @param aSize
     * @param aColor
     * @param isLitterBoxTrained
     * @param aTemperament
     */
    public Cat(String aName, int aAge, Sex aSex, String aBreed, Size aSize, String aColor, boolean isLitterBoxTrained, String aTemperament) {
        super(aName, Species.Cat, aAge, aSex, aBreed, aSize, aColor);
        setLitterBoxTrained(isLitterBoxTrained);
        setTemperament(aTemperament);
    }

    /**
     * Method to get whether a Cat is litter box trained.
     * @return boolean isLitterBoxTrained
     */
    public boolean isLitterBoxTrained() {
        return isLitterBoxTrained;
    }

    /**
     * Method to set whether a Cat is litter box trained.
     * @param indoor
     */
    public void setLitterBoxTrained(boolean indoor) {
        this.isLitterBoxTrained = indoor;
    }

    /**
     * Method to get a Cat's temperament.
     * @return String temperament
     */
    public String getTemperament() {
        return temperament;
    }

    /**
     * Method to set a Cat's temperament.
     * @param aTemperament
     */
    public void setTemperament(String aTemperament) {
        if (!aTemperament.isEmpty()) {
            this.temperament = aTemperament;
        } else {
            throw new IllegalArgumentException("Cat temperament must be either; Small, Medium, or Large");
        }
    }

    /**
     * Implementing the method to adopt an animal, from the Adoptable interface.
     */
    @Override
    public void adopt() {
        if (!isAdopted()) {
            setAdopted(true);
            System.out.println(getName() + " (ID: " + getAnimalId() + ")  has been adopted");
        } else {
            System.out.println(getName() + " (ID: " + getAnimalId() + ") is already adopted.");
        }
    }

    /**
     * Implementing the method to return an animal to the shelter, from the Adoptable interface.
     */
    @Override
    public void returnToShelter() {
        if (isAdopted()) {
            setAdopted(false);
            System.out.println(getName() + " (ID: " + getAnimalId() + ")  has been returned");
        } else {
            System.out.println(getName() + " (ID: " + getAnimalId() + ") is already returned");
        }
    }

    /**
     * Abstract method to display information on the Animal.
     */
    @Override
    public void displayInfo() {
        System.out.println("Cat [ID: " + getAnimalId() +
                ", Name: " + getName() +
                ", Age: " + (getAge() == -1 ? "Unknown" : getAge()) +
                ", Gender: " + getAnimalSex() +
                ", Breed: " + getBreed() +
                ", Indoor: " + (isLitterBoxTrained ? "Yes" : "No") +
                ", Status: " + (isAdopted() ? "Adopted" : "Available") +
                ", Medical Records: " + getMedicalRecord().getMedicalEntries().size() +
                "]");

    }
}