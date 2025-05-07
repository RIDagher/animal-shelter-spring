package com.animalshelter.model;

import java.time.LocalDate;
import java.util.List;

public class MedicalRecord {

    private final Animal animal;

    public MedicalRecord(Animal animal) {
        this.animal = animal;
    }

    public List<MedicalEntry> getMedicalEntries() {
        return animal.getMedicalEntries();
    }

    public void addMedicalEntry(String description, String vetName) {
        MedicalEntry newEntry = new MedicalEntry(description, vetName, LocalDate.now(), animal);
        animal.getMedicalEntries().add(newEntry);
    }

    public void listMedicalRecords() {
        List<MedicalEntry> entries = getMedicalEntries();
        if (!entries.isEmpty()) {
            System.out.println("Medical History:");
            for (MedicalEntry entry : entries) {
                entry.displayEntry();
            }
        } else {
            System.out.println("No medical records found.");
        }
    }
}
