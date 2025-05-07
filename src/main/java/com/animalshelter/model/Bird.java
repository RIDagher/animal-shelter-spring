package com.animalshelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

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


    public boolean isCanFly() {
        return canFly;
    }
    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public String getBeakType() {return beakType;}
    public void setBeakType(String aBeakType) {
        if (!aBeakType.isEmpty()) {
            this.beakType = aBeakType;
        } else {
            throw new IllegalArgumentException("Beak Type cannot be empty");
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
    public void returnToShelter() {}

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
