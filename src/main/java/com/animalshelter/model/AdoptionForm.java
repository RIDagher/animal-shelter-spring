package com.animalshelter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "adoption_forms")
public class AdoptionForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adoptionId;

    @Column(nullable = false)
    private String applicantName;

    @Column(nullable = false)
    private String applicantEmail;

    @Column(nullable = false)
    private String applicantPhone;

    @Column(nullable = false)
    private String applicantAddress;

    @Column(nullable = false)
    private String homeType;

    @Column(nullable = false)
    private boolean hasOtherPet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animalId", nullable = false)
    private Animal animal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdoptionStatus status;

    // Default constructor without parameters
    public AdoptionForm() {}

    // Constructor
    public AdoptionForm(String firstName, String email, String phone,
                        String address, String homeType, boolean hasOtherPet, Animal animal) {
        setApplicantName(firstName);
        setApplicantEmail(email);
        setApplicantPhone(phone);
        setApplicantAddress(address);
        setHomeType(homeType);
        setHasOtherPet(hasOtherPet);
        setAnimal(animal);
        this.status = AdoptionStatus.PENDING; // default
    }

    // Approve and reject methods
    public void approve() {
        if(animal.isAdopted()) {
            System.out.println("Cannot approve an adoption: " + animal.getName() + " is already adopted.");
        } else {
            status = AdoptionStatus.APPROVED;
            animal.setAdopted(true);
            System.out.println("Adoption approved for " + animal.getName() + ".");
        }

    }

    public void reject() {
        status = AdoptionStatus.REJECTED;
        System.out.println("Adoption rejected for " + animal.getName() + ".");
    }

    // Getters and Setters

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        if (applicantName != null && !applicantName.isEmpty()) {
            this.applicantName = applicantName;
        } else {
            throw new IllegalArgumentException("applicantFirstName cannot be empty.");
        }
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        if (applicantEmail != null && !applicantEmail.isEmpty()) {
            this.applicantEmail = applicantEmail;
        } else {
            throw new IllegalArgumentException("applicantEmail cannot be empty.");
        }

    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        if (applicantPhone != null && !applicantPhone.isEmpty()) {
            this.applicantPhone = applicantPhone;
        } else {
            throw new IllegalArgumentException("applicantPhone cannot be empty.");
        }

    }

    public String getApplicantAddress() {
        return applicantAddress;
    }

    public void setApplicantAddress(String applicantAddress) {
        if (applicantAddress != null && !applicantAddress.isEmpty()) {
            this.applicantAddress = applicantAddress;
        } else {
            throw new IllegalArgumentException("applicantAddress cannot be empty.");
        }
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        if (homeType != null && !homeType.isEmpty()) {
            this.homeType = homeType;
        } else {
            throw new IllegalArgumentException("homeType cannot be empty.");
        }
    }

    public boolean getHasOtherPet() {
        return hasOtherPet;
    }

    public void setHasOtherPet(boolean hasOtherPet) {
        this.hasOtherPet = hasOtherPet;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public AdoptionStatus getStatus() {
        return status;
    }

    public void setStatus(AdoptionStatus status) {
        this.status = status;
    }

    //  display method
    public void displayForm() {
        System.out.println("=== Adoption Form ===");
        System.out.println("Applicant: " + applicantName);
        System.out.println("Email: " + applicantEmail + " | Phone: " + applicantPhone);
        System.out.println("Address: " + applicantAddress);
        System.out.println("Home Type: " + homeType);
        System.out.println("Has Other Pets: " + (hasOtherPet ? "Yes" : "No"));
        System.out.println("Animal Requested: " + animal.getName() + " (ID: " + animal.getAnimalId() + ")");
        System.out.println("Status: " + status);
    }
}