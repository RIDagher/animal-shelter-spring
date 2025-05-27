package com.animalshelter.repositories;

import com.animalshelter.domain.adoptions.AdoptionForm;
import com.animalshelter.domain.adoptions.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionFormRepository extends JpaRepository<AdoptionForm, Long> {

    // Returns a list of adoption forms based on their status
    List<AdoptionForm> findByStatus(AdoptionStatus status);
}
