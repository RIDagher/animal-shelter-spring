package com.animalshelter.domain.shelter;

import com.animalshelter.domain.animals.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the registry of all animals in the shelter.
 */
public class AnimalRegistry {
    private List<Animal> animals;
    private static int idCounter = 1;

    /**
     * Default constructor without parameters.
     */
    public AnimalRegistry() {
        this.animals = new ArrayList<>();
    }

    /**
     * Adds a new animal to the registry and assigns it a unique ID.
     * @param animal
     */
    public void addAnimal(Animal animal) {
        if (animal != null) {
            animal.setAnimalId(idCounter++);
            animals.add(animal);
        } else {
            throw new IllegalArgumentException("Animal must be valid");
        }
    }

    /**
     * Removes an animal from the registry by its ID.
     * @param id
     * @return boolean of if the animal was successfully removed
     */
    public boolean removeAnimalById(int id) {
        for (Animal animal : animals) {
            if (animal.getAnimalId() == id) {
                animals.remove(animal);
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for animals by name.
     * @param name
     * @return list of animals matching the search criteria
     */
    public List<Animal> searchByName(String name) {
        List<Animal> results = new ArrayList<>();
        // Making the search term lower case
        String searchTerm = name.toLowerCase();
        
        // Checking if the lower cased animal name matches the search
        for (Animal animal : animals) {
            if (animal.getName().toLowerCase().contains(searchTerm)) {
                results.add(animal);
            }
        }
        return results;
    }

    /**
     * Get all animals in the registry.
     * @return all Animals
     */
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals);
    }

    /**
     * Gets all animals that are available for adoption.
     * @return all Animals not adopted
     */
    public List<Animal> getAvailableAnimals() {
        List<Animal> available = new ArrayList<>();
        for (Animal animal : animals) {
            if (!animal.isAdopted()) {
                available.add(animal);
            }
        }
        return available;
    }
}