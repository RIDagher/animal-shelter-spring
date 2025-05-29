package com.animalshelter.behavioral.StrategyPattern;

import com.animalshelter.domain.animals.Animal;
import com.animalshelter.domain.animals.enums.Species;

import java.util.List;
import java.util.stream.Collectors;

public class SpeciesSearchStrategy implements SearchStrategy {
    private final Species species;

    public SpeciesSearchStrategy(final Species species) {
        this.species = species;
    }

    @Override
    public List<Animal> search(final List<Animal> animals) {
        return animals.stream().filter(animal -> species.equals(animal.getSpecies())).collect(Collectors.toList());
    }

}