package com.animalshelter.service;

import com.animalshelter.model.AdoptionForm;
import com.animalshelter.model.AdoptionStatus;
import com.animalshelter.model.Animal;
import com.animalshelter.repositories.AdoptionFormRepository;
import com.animalshelter.repositories.AnimalRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AdoptionService {

    // Initializing Repositories
    private final AdoptionFormRepository adoptionFormRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public AdoptionService(AdoptionFormRepository adoptionFormRepository,
                           AnimalRepository animalRepository) {
        this.adoptionFormRepository = adoptionFormRepository;
        this.animalRepository = animalRepository;
    }

    // Method to create an adoption form
    public AdoptionForm submitAdoptionForm(String applicantName, String applicantEmail, String applicantPhone,
                                           String applicantAddress, boolean hasOtherPet, Long animalId) {

        // Ensuring the animal exists
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        // Ensuring the animal has not already been adopted
        if (animal.isAdopted()) {
            throw new IllegalStateException("Animal is already adopted");
        }

        // Instantiating an adoption form object
        AdoptionForm form = new AdoptionForm(
                applicantName,
                applicantEmail,
                applicantPhone,
                applicantAddress,
                hasOtherPet,
                animal
        );

        // Saving the adoption form to the table
        return adoptionFormRepository.save(form);
    }

    // Method to approve an adoption form
    public void approveAdoption(Long formId) {
        AdoptionForm form = adoptionFormRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        form.approve();
    }

    // Method to reject an adoption form
    public void rejectAdoption(Long formId) {
        AdoptionForm form = adoptionFormRepository.findById(formId).orElseThrow(() -> new IllegalArgumentException("Form not found"));
        form.reject();
    }

    // Method to get pending adoptions and initialize all the animals when forms load
    public List<AdoptionForm> getPendingAdoptions() {
        return adoptionFormRepository.findByStatus(AdoptionStatus.PENDING)
                .stream()
                .peek(form -> Hibernate.initialize(form.getAnimal()))
                .collect(Collectors.toList());
    }
}