package com.animalshelter.creational.AdoptionsFactory;

import com.animalshelter.domain.animals.Animal;
import com.animalshelter.domain.adoptions.Adopter;
import java.util.List;

/**
 * Factory for creating Adopter objects
 */
public class AdopterFactory {

    /**
     * Creates a basic Adopter with just a name
     * @param name Adopter's full name
     * @return Created Adopter
     */
    public Adopter createAdopter(String name) {
        validateName(name);
        return new Adopter(name);
    }

    /**
     * Creates an Adopter with a predefined list of adopted animals
     * @param name Adopter's full name
     * @param adoptedAnimals List of already adopted animals
     * @return Created Adopter
     */
    public Adopter createAdopter(String name, List<Animal> adoptedAnimals) {
        validateName(name);
        Adopter adopter = new Adopter(name);
        if (adoptedAnimals != null) {
            for (Animal animal : adoptedAnimals) {
                if (animal != null && animal.isAdopted()) {
                    adopter.addAdoptedAnimal(animal);
                }
            }
        }
        return adopter;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Adopter name cannot be null or empty");
        }
    }
}