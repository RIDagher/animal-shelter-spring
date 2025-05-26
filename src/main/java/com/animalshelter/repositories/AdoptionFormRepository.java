package com.animalshelter.repositories;

import com.animalshelter.domain.adoptions.AdoptionForm;
import com.animalshelter.domain.adoptions.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdoptionFormRepository extends JpaRepository<AdoptionForm, Long> {

    List<AdoptionForm> findByStatus(AdoptionStatus status);
}
