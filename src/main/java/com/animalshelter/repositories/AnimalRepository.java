package com.animalshelter.repositories;

import com.animalshelter.model.Animal;
import com.animalshelter.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByAnimalSpecies(Species animalSpecies);
    Optional<Animal> findByAnimalName(String animalName);
}
