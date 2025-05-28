package com.animalshelter.creational.AnimalsFactory;

import com.animalshelter.domain.animals.*;
import com.animalshelter.domain.animals.enums.*;

/**
 * Concrete factory for creating Cat objects
 */
public class CatFactory extends AnimalFactory {

    @Override
    public Animal createAnimal(String name, int age, Sex sex, String breed, Size size, String color, boolean isLitterBoxTrained, String temperament) {
        return createCat(name, age, sex, breed, size, color, isLitterBoxTrained, temperament);
    }

    /**
     * Creates a Cat with all attributes
     * @param name Cat's name
     * @param age Cat's age
     * @param sex Cat's sex
     * @param breed Cat's breed
     * @param size Cat's size
     * @param color Cat's color
     * @param isLitterBoxTrained Whether the cat is litter box trained
     * @param temperament Cat's temperament
     * @return Created Cat
     */
    public Cat createCat(String name, int age, Sex sex, String breed, Size size, String color, boolean isLitterBoxTrained, String temperament) {
        validateAttributes(name, age, breed, color);
        if (temperament == null || temperament.trim().length() < 3) {
            throw new IllegalArgumentException("Temperament cannot be empty");
        }

        return new Cat(name, age, sex, breed, size, color, isLitterBoxTrained, temperament);
    }
}