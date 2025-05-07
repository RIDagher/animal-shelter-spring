package com.animalshelter.repositories;

import com.animalshelter.model.MedicalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalEntryRepository extends JpaRepository<MedicalEntry, Long> {

}