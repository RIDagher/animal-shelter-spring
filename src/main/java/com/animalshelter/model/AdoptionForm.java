package com.animalshelter.model;

import jakarta.persistence.*;

/**
 * AdoptionForm class.
 * Object attributes is all the basic but necessary data to create an entry for an adoption form in the database.
 * Default and Default and Parameterized constructors for object instantiation.
 * Adoption status is pending by default and can later be modified with either setters or with the adopt() and reject() methods.
 * Getters and setters for all object attributes.
 */
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
    private boolean hasOtherPet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animalId", nullable = false)
    private Animal animal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdoptionStatus status;

    /**
     * Default AdoptionForm Constructor without parameters.
     */
    public AdoptionForm() {}

    /**
     * AdoptionForm Constructor with parameters, sets the adoption status to pending by default.
     * @param firstName
     * @param email
     * @param phone
     * @param address
     * @param hasOtherPet
     * @param animal
     */
    public AdoptionForm(String firstName, String email, String phone,
                        String address, boolean hasOtherPet, Animal animal) {
        setApplicantName(firstName);
        setApplicantEmail(email);
        setApplicantPhone(phone);
        setApplicantAddress(address);
        setHasOtherPet(hasOtherPet);
        setAnimal(animal);
        this.status = AdoptionStatus.PENDING;
    }

    /**
     * Method to set the adoption status of an AdoptionForm to APPROVED.
     */
    public void approve() {
        if(animal.isAdopted()) {
            System.out.println("Cannot approve an adoption: " + animal.getName() + " is already adopted.");
        } else {
            status = AdoptionStatus.APPROVED;
            animal.setAdopted(true);
            System.out.println("Adoption approved for " + animal.getName() + ".");
        }

    }

    /**
     * Method to set the adoption status of an AdoptionForm to REJECTED.
     */
    public void reject() {
        status = AdoptionStatus.REJECTED;
        System.out.println("Adoption rejected for " + animal.getName() + ".");
    }

    /**
     * Method to get an AdoptionForm's ID.
     * @return Long adoptionId
     */
    public Long getAdoptionId() {return adoptionId;}

    /**
     * Method to get an AdoptionForm's applicant name.
     * @return String applicantName
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * Method to set an AdoptionForm's applicant name.
     * @param applicantName
     */
    public void setApplicantName(String applicantName) {
        if (applicantName != null && !applicantName.isEmpty()) {
            this.applicantName = applicantName;
        } else {
            throw new IllegalArgumentException("applicantName cannot be empty.");
        }
    }

    /**
     * Method to get an AdoptionForm's applicant email.
     * @return String applicantEmail
     */
    public String getApplicantEmail() {
        return applicantEmail;
    }

    /**
     * Method to set an AdoptionForm's applicant email.
     * @param applicantEmail
     */
    public void setApplicantEmail(String applicantEmail) {
        if (applicantEmail != null && !applicantEmail.isEmpty()) {
            this.applicantEmail = applicantEmail;
        } else {
            throw new IllegalArgumentException("applicantEmail cannot be empty.");
        }

    }

    /**
     * Method to get an AdoptionForm's applicant phone.
     * @return String applicantPhone
     */
    public String getApplicantPhone() {
        return applicantPhone;
    }

    /**
     * Method to set an AdoptionForm's applicant phone.
     * @param applicantPhone
     */
    public void setApplicantPhone(String applicantPhone) {
        if (applicantPhone != null && !applicantPhone.isEmpty()) {
            this.applicantPhone = applicantPhone;
        } else {
            throw new IllegalArgumentException("applicantPhone cannot be empty.");
        }

    }

    /**
     * Method to get an AdoptionForm's applicant address.
     * @return String applicantAddress
     */
    public String getApplicantAddress() {
        return applicantAddress;
    }

    /**
     * Method to set an AdoptionForm's applicant address.
     * @param applicantAddress
     */
    public void setApplicantAddress(String applicantAddress) {
        if (applicantAddress != null && !applicantAddress.isEmpty()) {
            this.applicantAddress = applicantAddress;
        } else {
            throw new IllegalArgumentException("applicantAddress cannot be empty.");
        }
    }

    /**
     * Method to get a boolean of if an AdoptionForm's applicant has another pet.
     * @return boolean hasOtherPet
     */
    public boolean getHasOtherPet() {
        return hasOtherPet;
    }

    /**
     * Method to set if an AdoptionForm's applicant has another pet.
     * @param hasOtherPet
     */
    public void setHasOtherPet(boolean hasOtherPet) {
        this.hasOtherPet = hasOtherPet;
    }

    /**
     * Method to get an AdoptionForm's animal being requested for adoption.
     * @return Animal animal
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Method to set an AdoptionForm's animal requested for adoption.
     * @param animal
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * Method to get an AdoptionForm's approval status.
     * @return AdoptionStatus status
     */
    public AdoptionStatus getStatus() {
        return status;
    }

    /**
     * Method to set an AdoptionForm's approval status.
     * @param status
     */
    public void setStatus(AdoptionStatus status) {
        this.status = status;
    }

    /**
     * Method to display the form's information.
     */
    public void displayForm() {
        System.out.println("=== Adoption Form ===");
        System.out.println("Applicant: " + applicantName);
        System.out.println("Email: " + applicantEmail + " | Phone: " + applicantPhone);
        System.out.println("Address: " + applicantAddress);
        System.out.println("Has Other Pets: " + (hasOtherPet ? "Yes" : "No"));
        System.out.println("Animal Requested: " + animal.getName() + " (ID: " + animal.getAnimalId() + ")");
        System.out.println("Status: " + status);
    }
}