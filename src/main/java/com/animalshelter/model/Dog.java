package com.animalshelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

/**
 * Dog subclass.
 * Added object attributes are a boolean for whether the dog is trained and a String for how loud the dog is.
 * Default and Parameterized constructors for object instantiation.
 * Getters and setter for all object attributes.
 * Implements the two methods adopt() and returnToShelter() from the Adoptable interface.
 * Defines the abstract method displayInfo() from the abstract superclass Animal.
 */
@Entity
@DiscriminatorValue("DOG")
public class Dog extends Animal implements Adoptable {
    private boolean isTrained;
    private String barkVolume;

    // Default constructor without parameters
    public Dog() {}

    // Constructor
    public Dog(String aName, int aAge, Sex aSex, String aBreed, Size aSize, String aColor, boolean isTrained, String barkVolume) {
        super(aName, Species.Dog, aAge, aSex, aBreed, aSize, aColor);
        setTrained(isTrained);

    }

    /**
     * Method to get whether a Dog is trained.
     * @return boolean isTrained
     */
    public boolean isTrained() {
        return isTrained;
    }

    /**
     * Method to set whether a Dog is trained.
     * @param trained
     */
    public void setTrained(boolean trained) {
        this.isTrained = trained;
    }

    /**
     * Method to get a Dog's bark volume.
     * @return String barkVolume
     */
    public String getBarkVolume() {return barkVolume;}

    /**
     * Method to set a Dog's bark volume.
     * @param barkVolume
     */
    public void setBarkVolume(String barkVolume) {
        if (!barkVolume.isEmpty()) {
            this.barkVolume = barkVolume;
        } else {
            throw new IllegalArgumentException("Bark Volume cannot be empty");
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
        System.out.println("Dog [ID: " + getAnimalId() +
                ", Name: " + getName() +
                ", Age: " + (getAge() == -1 ? "Unknown" : getAge()) +
                ", Gender: " + getAnimalSex() +
                ", Breed: " + getBreed() +
                ", Is Trained: " + (isTrained ? "Yes" : "No") +
                ", Status: " + (isAdopted() ? "Adopted" : "Available") +
                ", Medical Records: " + getMedicalRecord().getMedicalEntries().size() +
                "]"


        );
    }

}
