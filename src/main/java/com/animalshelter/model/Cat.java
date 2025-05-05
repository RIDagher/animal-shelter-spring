package com.animalshelter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("CAT")
public class Cat extends Animal implements Adoptable {
    private boolean isLitterBoxTrained;
    private String temperament;

    // Default constructor without parameters
    public Cat() {}

    public Cat(String aName, int aAge, Sex aSex, String aBreed, Size aSize, String aColor, boolean isLitterBoxTrained, String aTemperament) {
        super(aName, Species.Cat, aAge, aSex, aBreed, aSize, aColor);
        setLitterBoxTrained(isLitterBoxTrained);
        setTemperament(aTemperament);
    }

    public boolean isLitterBoxTrained() {
        return isLitterBoxTrained;
    }
    public void setLitterBoxTrained(boolean indoor) {
        this.isLitterBoxTrained = indoor;
    }

    public String getTemperament() {
        return temperament;
    }
    public void setTemperament(String aTemperament) {
        if (!aTemperament.isEmpty()) {
            this.temperament = aTemperament;
        } else {
            throw new IllegalArgumentException("Cat temperament must be either; Small, Medium, or Large");
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

    // Implements Abstract displayInfo method from Animal class
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


