package com.animalshelter.repositories;

import com.animalshelter.domain.animals.Animal;
import com.animalshelter.domain.animals.enums.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByAnimalSpecies(Species animalSpecies);
    Optional<Animal> findByAnimalName(String animalName);
}
