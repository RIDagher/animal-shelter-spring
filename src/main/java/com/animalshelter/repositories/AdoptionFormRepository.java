package com.animalshelter.repositories;

import com.animalshelter.model.AdoptionForm;
import com.animalshelter.model.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionFormRepository extends JpaRepository<AdoptionForm, Long> {

    // Returns a list of adoption forms based on their status
    List<AdoptionForm> findByStatus(AdoptionStatus status);
}
