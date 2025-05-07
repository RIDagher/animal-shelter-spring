package com.animalshelter.repositories;

import com.animalshelter.model.MedicalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalEntryRepository extends JpaRepository<MedicalEntry, Long> {

    List<MedicalEntry> findByAnimalId(Long animalId);
}