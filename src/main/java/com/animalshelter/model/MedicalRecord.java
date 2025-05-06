package com.animalshelter.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class MedicalRecord {
    @ElementCollection
    @CollectionTable(
            name = "animal_medical_entries",
            joinColumns = @JoinColumn(name = "animalId"))
    private List<MedicalEntry> medicalEntries = new ArrayList<>();

    public MedicalRecord() {}

    public List<MedicalEntry> getMedicalEntries() {
        return medicalEntries;
    }

    public void addMedicalEntry(MedicalEntry medicalEntry) {
        medicalEntries.add(medicalEntry);
    }

    public void listMedicalRecords() {
        if(!medicalEntries.isEmpty()) {
            for (MedicalEntry medicalEntry : medicalEntries) {
                System.out.println("Medical History: ");
                medicalEntry.displayEntry();
            }
        } else {
            System.out.println("No medical records found");
        }
    }
}

