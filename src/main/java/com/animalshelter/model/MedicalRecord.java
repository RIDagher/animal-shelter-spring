package com.animalshelter.model;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class MedicalRecord {
    private List<MedicalEntry> medicalEntries;

    public MedicalRecord() {
       medicalEntries = new ArrayList<>();
    }

    public List<MedicalEntry> getMedicalEntries() {
        return medicalEntries;
    }

    public void addMedicalEntry(String description, String vetName) {
        medicalEntries.add(new MedicalEntry(description, vetName, LocalDate.now()));
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
