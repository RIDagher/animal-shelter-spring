package com.animalshelter.behavioral.StrategyPattern;

import com.animalshelter.domain.animals.Animal;

import java.util.List;
import java.util.stream.Collectors;

public class NameSearchStrategy  implements SearchStrategy {

    private final String name;

    public NameSearchStrategy(String name) {
        this.name = name;
    }

    @Override
    public List<Animal> search(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }
}
