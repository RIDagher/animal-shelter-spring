package com.animalshelter.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MedicalEntry {
    private String description;
    private String vetName;
    private LocalDate date;

    public MedicalEntry(String description, String veteranName, LocalDate date) {
        this.description = description;
        this.vetName = veteranName;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public String getVeteranName() {
        return vetName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void displayEntry() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("- " + getDescription() + " | Date: " + getDate().format(formatter) + " | Vet: Dr. " + getVeteranName());
    }
}
