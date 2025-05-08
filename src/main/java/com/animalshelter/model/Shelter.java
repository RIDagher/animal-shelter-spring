package com.animalshelter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Shelter class to hold a list of Animals in a list.
 * Only one object attribute which is the list of Animals.
 * Methods to add and remove Animals to and from the Shelter.
 * Methods to list the all Animals in the Shelter and to find an Animal in the shelter given an animalId.
 */
public class Shelter {
    private List<Animal> animals;

    /**
     * Default constructore without parameters.
     */
    public Shelter() {
        animals = new ArrayList<>();
    }

    /**
     * Method to add an Animal to Shelter.
     * @param animal
     */
    public void addAnimal(Animal animal) {
        animals.add(animal);
        System.out.println("Animal added: " + animal.getAnimalId());
    }

    /**
     * Method to remove an Animal from the Shelter.
     * @param animal
     */
    public void removeAnimal(Animal animal) {
        if (animals.remove(animal)) {
            System.out.println("Animal removed: " + animal.getAnimalId());
        } else {
            System.out.println("Animal not found in shelter");
        }
    }

    /**
     * List animals Info in shelter
     */
    public void listAnimals() {
        if(animals.isEmpty()){
            System.out.println("No animals found");
        } else {
            for(Animal animal : animals)
                animal.displayInfo();
        }
    }

    /**
     * Method to return an Animal object with a given animalId.
     * @param animalId
     * @return Animal animal
     */
    public Animal getAnimalById(int animalId) {
        for (Animal animal : animals) {
            if(animal.getAnimalId() == animalId) {
                animal.displayInfo();
                return animal;
            }
        }
        return null;
    }
}
