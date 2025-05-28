package com.animalshelter.creational.AnimalsFactory;

import com.animalshelter.domain.animals.*;
import com.animalshelter.domain.animals.enums.*;

/**
 * Concrete factory for creating Dog objects
 */
public class DogFactory extends AnimalFactory {

    @Override
    public Animal createAnimal(String name, int age, Sex sex, String breed, Size size, String color, boolean isTrained, String barkVolume) {
        return createDog(name, age, sex, breed, size, color, isTrained, barkVolume);
    }

    /**
     * Creates a Dog with all attributes
     * @param name Dog's name
     * @param age Dog's age
     * @param sex Dog's sex
     * @param breed Dog's breed
     * @param size Dog's size
     * @param color Dog's color
     * @param isTrained Whether the dog is trained
     * @param barkVolume Volume of dog's bark
     * @return Created Dog
     */
    public Dog createDog(String name, int age, Sex sex, String breed, Size size, String color, boolean isTrained, String barkVolume) {
        validateAttributes(name, age, breed, color);
        if (barkVolume == null || barkVolume.trim().length() < 3) {
            throw new IllegalArgumentException("Bark volume cannot be empty");
        }

        return new Dog(name, age, sex, breed, size, color, isTrained, barkVolume);
    }
}