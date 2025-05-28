package com.animalshelter.creational.AnimalsFactory;

import com.animalshelter.domain.animals.Animal;
import com.animalshelter.domain.animals.enums.*;

/**
 * Abstract factory for creating Animal objects
 */
public abstract class AnimalFactory {

    /**
     * Creates an animal with common attributes
     * @param name Animal's name
     * @param age Animal's age
     * @param sex Animal's sex
     * @param breed Animal's breed
     * @param size Animal's size
     * @param color Animal's color
     * @return Created Animal
     */
    public abstract Animal createAnimal(String name, int age, Sex sex, String breed, Size size, String color, boolean boolSpecification, String specification);

    /**
     * Validates animal attributes
     * @param name Animal's name
     * @param age Animal's age
     * @param breed Animal's breed
     * @param color Animal's color
     * @throws IllegalArgumentException if any validation fails
     */
    protected void validateAttributes(String name, int age,
                                            String breed, String color) {
        if (name == null || name.trim().length() < 3) {
            throw new IllegalArgumentException("Animal name cannot be empty");
        }
        if (age < -1) {
            throw new IllegalArgumentException("Animal age cannot be less than -1");
        }
        if (breed == null || breed.trim().length() < 3) {
            throw new IllegalArgumentException("Animal breed cannot be empty");
        }
        if (color == null || color.trim().length() < 3) {
            throw new IllegalArgumentException("Animal color cannot be empty");
        }
    }
}