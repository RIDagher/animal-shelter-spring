package com.animalshelter.behavioral.StrategyPattern;


import com.animalshelter.domain.animals.Animal;

import java.util.List;

public interface SearchStrategy {
    List<Animal> search(List<Animal> animals);
}