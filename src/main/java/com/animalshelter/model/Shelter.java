package com.animalshelter.model;

import java.util.ArrayList;
import java.util.List;


public class Shelter {
    private List<Animal> animals;


    public Shelter() {
        animals = new ArrayList<>();

    }

    /**
     * Add animal to Shelter
     * @param animal
     */
    public void addAnimal(Animal animal) {

        animals.add(animal);
        System.out.println("Animal added: " + animal.getAnimalId());
    }

    /**
     * Remove animal from shelter
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
     *
     * @param animalId
     * @return animal by ID
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
