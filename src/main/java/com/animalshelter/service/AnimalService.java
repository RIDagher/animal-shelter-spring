package com.animalshelter.service;

import com.animalshelter.domain.animals.Animal;
import com.animalshelter.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.animalshelter.behavioral.StrategyPattern.SearchStrategy;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> searchAnimals(SearchStrategy strategy) {
        List<Animal> animals = animalRepository.findAll();
        return strategy.search(animals);
    }

    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }
}