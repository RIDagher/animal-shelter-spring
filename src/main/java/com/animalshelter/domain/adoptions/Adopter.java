package com.animalshelter.domain.adoptions;

import com.animalshelter.domain.animals.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a person who can adopt animals from the shelter.
 */
public class Adopter {
    private String name;
    private List<Animal> adoptedAnimals;

    /**
     * Adopter constructor with parameters.
     * @param name
     */
    public Adopter(String name) {
        this.name = name;
        this.adoptedAnimals = new ArrayList<>();
    }

    /**
     * Gets the name of the adopter.
     * @return Adopter name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the adopter.
     * @param name
     */
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    /**
     * Gets the list of animals adopted by this adopter.
     * @return list of adopted Animals
     */
    public List<Animal> getAdoptedAnimals() {
        return adoptedAnimals;
    }

    /**
     * Adds an animal to the adopter's list of adopted animals.
     * @param animal
     */
    public void addAdoptedAnimal(Animal animal) {
        if (animal != null && animal.isAdopted()) {
            adoptedAnimals.add(animal);
        } else {
            throw new IllegalArgumentException("Animal must be valid and must be adopted");
        }
    }

    /**
     * Removes an animal from the adopter's list (if returned to shelter).
     * @param animal
     */
    public void removeAdoptedAnimal(Animal animal) {
        adoptedAnimals.remove(animal);
    }

    /**
     * Displays information about all animals adopted by this adopter.
     */
    public void displayAdoptedAnimals() {
        if (adoptedAnimals.isEmpty()) {
            System.out.println(name + " hasn't adopted any animals yet.");
        } else {
            System.out.println(name + "'s adopted animals:");
            for (Animal animal : adoptedAnimals) {
                animal.displayInfo();
            }
        }
    }
}