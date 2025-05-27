package com.animalshelter.repositories;

import com.animalshelter.domain.medical.MedicalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalEntryRepository extends JpaRepository<MedicalEntry, Long> {

    List<MedicalEntry> findByAnimalId(Long animalId);
}