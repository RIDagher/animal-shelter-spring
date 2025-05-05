package com.animalshelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

// Dog class extends Animal and implements Adoptable interface
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

    public boolean isTrained() {
        return isTrained;
    }
    public void setTrained(boolean trained) {
        this.isTrained = trained;
    }

    public String getBarkVolume() {return barkVolume;}
    public void setBarkVolume(String barkVolume) {
        if (!barkVolume.isEmpty()) {
            this.barkVolume = barkVolume;
        } else {
            throw new IllegalArgumentException("Bark Volume cannot be empty");
        }
    }


    @Override
    public void adopt() {
        if (!isAdopted()) {
            setAdopted(true);
            System.out.println(getName() + " (ID: " + getAnimalId() + ")  has been adopted");
        } else {
            System.out.println(getName() + " (ID: " + getAnimalId() + ") is already adopted.");
        }
    }

    @Override
    public void returnToShelter() {
        if (isAdopted()) {
            setAdopted(false);
            System.out.println(getName() + " (ID: " + getAnimalId() + ")  has been returned");
        } else {
            System.out.println(getName() + " (ID: " + getAnimalId() + ") is already returned");
        }
    }

    // Implements Abstract displayInfo method from Animal class
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
