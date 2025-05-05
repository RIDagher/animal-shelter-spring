package com.animalshelter.model;

public class AdoptionForm {

    private String applicantFirstName;
    private String applicantLastName;
    private String applicantEmail;
    private String applicantPhone;
    private String applicantAddress;
    private String applicantCity;
    private String applicantState;
    private String applicantZip;
    private String homeType;
    private boolean hasOtherPet;

    private Animal animal;
    private AdoptionStatus status;

    // Constructor
    public AdoptionForm(String firstName, String lastName, String email, String phone,
                        String address, String city, String state, String zipCode,
                        String homeType, boolean hasOtherPet, Animal animal) {
        setApplicantFirstName(firstName);
        setApplicantLastName(lastName);
        setApplicantEmail(email);
        setApplicantPhone(phone);
        setApplicantAddress(address);
        setApplicantCity(city);
        setApplicantState(state);
        setApplicantZip(zipCode);
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

    public String getApplicantFirstName() {
        return applicantFirstName;
    }

    public void setApplicantFirstName(String applicantFirstName) {
        if (applicantFirstName != null && !applicantFirstName.isEmpty()) {
            this.applicantFirstName = applicantFirstName;
        } else {
            throw new IllegalArgumentException("applicantFirstName cannot be empty.");
        }
    }

    public String getApplicantLastName() {
        return applicantLastName;
    }

    public void setApplicantLastName(String applicantLastName) {
        if (applicantLastName != null && !applicantLastName.isEmpty()) {
            this.applicantLastName = applicantLastName;
        } else {
            throw new IllegalArgumentException("applicantLastName cannot be empty.");
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

    public String getApplicantCity() {
        return applicantCity;
    }

    public void setApplicantCity(String applicantCity) {
        this.applicantCity = applicantCity;
    }

    public String getApplicantState() {
        return applicantState;
    }

    public void setApplicantState(String applicantState) {
        if (applicantState != null && !applicantState.isEmpty()) {
            this.applicantState = applicantState;
        } else {
            throw new IllegalArgumentException("applicantState cannot be empty.");
        }

    }

    public String getApplicantZip() {
        return applicantZip;
    }

    public void setApplicantZip(String applicantZip) {
        if (applicantZip != null && !applicantZip.isEmpty()) {
            this.applicantZip = applicantZip;
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
        System.out.println("Applicant: " + applicantFirstName + " " + applicantLastName);
        System.out.println("Email: " + applicantEmail + " | Phone: " + applicantPhone);
        System.out.println("Address: " + applicantAddress + ", " + applicantCity + ", " + applicantState + " " + applicantZip);
        System.out.println("Home Type: " + homeType);
        System.out.println("Has Other Pets: " + (hasOtherPet ? "Yes" : "No"));
        System.out.println("Animal Requested: " + animal.getName() + " (ID: " + animal.getAnimalId() + ")");
        System.out.println("Status: " + status);
    }
}