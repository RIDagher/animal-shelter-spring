package com.animalshelter.creational.AnimalsFactory;

import com.animalshelter.domain.animals.*;
import com.animalshelter.domain.animals.enums.*;

/**
 * Concrete factory for creating Bird objects
 */
public class BirdFactory extends AnimalFactory {

    @Override
    public Animal createAnimal(String name, int age, Sex sex, String breed, Size size, String color, boolean canFly, String beakType) {
        return createBird(name, age, sex, breed, size, color, canFly, beakType);
    }

    /**
     * Creates a Bird with all attributes
     * @param name Bird's name
     * @param age Bird's age
     * @param sex Bird's sex
     * @param breed Bird's breed
     * @param size Bird's size
     * @param color Bird's color
     * @param canFly Whether the bird can fly
     * @param beakType Type of beak
     * @return Created Bird
     */
    public Bird createBird(String name, int age, Sex sex, String breed, Size size, String color, boolean canFly, String beakType) {
        validateAttributes(name, age, breed, color);
        if (beakType == null || beakType.trim().length() < 3) {
            throw new IllegalArgumentException("Beak type cannot be empty");
        }

        return new Bird(name, age, sex, breed, size, color, canFly, beakType);
    }
}