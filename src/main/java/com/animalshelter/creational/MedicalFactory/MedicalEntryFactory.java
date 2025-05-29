package com.animalshelter.creational.MedicalFactory;

import com.animalshelter.domain.animals.Animal;
import com.animalshelter.domain.medical.MedicalEntry;
import java.time.LocalDate;

/**
 * Factory for creating MedicalEntry objects
 */
public class MedicalEntryFactory {

    /**
     * Creates a MedicalEntry with all required fields
     * @param description Description of medical procedure
     * @param vetName Name of veterinarian
     * @param date Date of procedure
     * @param animal Animal this entry belongs to
     * @return Created MedicalEntry
     */
    public MedicalEntry createMedicalEntry(String description, String vetName, LocalDate date, Animal animal) {
        validateFields(description, vetName, date, animal);
        return new MedicalEntry(description, vetName, date, animal);
    }

    private void validateFields(String description, String vetName,
                                LocalDate date, Animal animal) {
        if (description == null || description.trim().length() < 3) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (vetName == null || vetName.trim().length() < 3) {
            throw new IllegalArgumentException("Vet name cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }
        if (animal == null) {
            throw new IllegalArgumentException("Animal cannot be null");
        }
    }
}