package com.animalshelter.service;

import com.animalshelter.domain.animals.Animal;
import com.animalshelter.domain.medical.MedicalEntry;
import com.animalshelter.repositories.AnimalRepository;
import com.animalshelter.repositories.MedicalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MedicalEntryService {

    // Initializing Repositories
    private final MedicalEntryRepository medicalEntryRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public MedicalEntryService(MedicalEntryRepository medicalEntryRepository, AnimalRepository animalRepository) {
        this.medicalEntryRepository = medicalEntryRepository;
        this.animalRepository = animalRepository;
    }

    // Method to create a new medical entry for a given Animal
    public MedicalEntry createMedicalEntry(Long animalId, String vetName, LocalDate date, String description) {

        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new IllegalArgumentException("Invalid animal ID"));
        // Creating MedicalEntry object
        MedicalEntry entry = new MedicalEntry(description, vetName, date, animal);
        return medicalEntryRepository.save(entry);
    }
}
