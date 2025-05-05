package com.animalshelter.repositories;

import com.animalshelter.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByAnimalName(String name);
}
