package com.animalshelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

/**
 * Bird subclass.
 * Added object attributes are a boolean for whether the bird can fly and a String for the type of beak the bird has.
 * Default and Parameterized constructors for object instantiation.
 * Getters and setter for all object attributes.
 * Implements the two methods adopt() and returnToShelter() from the Adoptable interface.
 * Defines the abstract method displayInfo() from the abstract superclass Animal.
 */
@Entity
@DiscriminatorValue("BIRD")
public class Bird extends Animal implements Adoptable {
    private boolean canFly;
    private String beakType;

    /**
     * Default Bird Constructor with no parameters.
     */
    public Bird() {}

    /**
     * Bird Constructor with parameters, which uses the Animal superclass constructor.
     * @param aName
     * @param aAge
     * @param aSex
     * @param aBreed
     * @param aSize
     * @param aColor
     * @param aCanFly
     * @param aBeakType
     */
    public Bird(String aName, int aAge, Sex aSex, String aBreed, Size aSize, String aColor, boolean aCanFly, String aBeakType) {
        super(aName, Species.Bird, aAge, aSex, aBreed, aSize, aColor);
        setCanFly(aCanFly);
        setBeakType(aBeakType);
    }

    /**
     * Method to know whether the Bird can fly.
     * @return boolean canFly
     */
    public boolean isCanFly() {
        return canFly;
    }

    /**
     * Method to set whether the Bird can fly.
     * @param canFly
     */
    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    /**
     * Method to get the Bird's beak type.
     * @return String beakType
     */
    public String getBeakType() {return beakType;}
    public void setBeakType(String aBeakType) {
        if (!aBeakType.isEmpty()) {
            this.beakType = aBeakType;
        } else {
            throw new IllegalArgumentException("Beak Type cannot be empty");
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
        System.out.println("Bird [ID: " + getAnimalId() +
                ", Name: " + getName() +
                ", Age: " + (getAge() == -1 ? "Unknown" : getAge()) +
                ", Gender: " + getAnimalSex() +
                ", Breed: " + getBreed() +
                ", Can Fly: " + (canFly ? "Yes" : "No") +
                ", Status: " + (isAdopted() ? "Adopted" : "Available") +
                ", Medical Records: " + getMedicalRecord().getMedicalEntries().size() +
                "]");
    }

}