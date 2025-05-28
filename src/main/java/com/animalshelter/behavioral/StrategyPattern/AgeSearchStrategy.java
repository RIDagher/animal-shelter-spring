package com.animalshelter.behavioral.StrategyPattern;

import com.animalshelter.domain.animals.Animal;

import java.util.List;
import java.util.stream.Collectors;

public class AgeSearchStrategy implements SearchStrategy {

    private int maxAge;

    public AgeSearchStrategy(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public List<Animal> search(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.getAge() > maxAge).collect(Collectors.toList());
    }

}
