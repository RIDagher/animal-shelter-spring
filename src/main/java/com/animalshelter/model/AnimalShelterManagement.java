package com.animalshelter.model;

import java.text.Normalizer;

public class AnimalShelterManagement {
    public static void main(String[] args) {



        Dog max = new Dog("Max", 1, Sex.Male, "Labrador", Size.Medium, "Black", true, "Medium");


        max.getMedicalRecord().addMedicalEntry("rabies vaccine", "John Smith");
        max.getMedicalRecord().addMedicalEntry("deworm treatment", "John Smith");
        max.displayInfo();


        max.getMedicalRecord().listMedicalRecords();

        Shelter shelter = new Shelter();
        shelter.addAnimal(max);

        shelter.listAnimals();
//        max.adopt();

        max.displayInfo();

        Volunteer volunteer = new Volunteer("James", "james@email.com", "555-555-5555");
        volunteer.displayInfo();
        volunteer.assignTask("Walk the dog");
        volunteer.assignSchedule("Monday");
        volunteer.displayInfo();

        AdoptionForm form1 = new AdoptionForm("John", "Smith", "john@email.com", "555-555-555", "124 St",
                "Montreal", "Qc", "H2H H2H", "House", true, max);

        form1.displayForm();
        form1.approve();


    }
}
