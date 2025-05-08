package com.animalshelter.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Volunteer class to create Volunteer's who help the Animal Shelter.
 * Object attributes to describe the Volunteer.
 * Default and Parameterized constructors for object instantiation.
 * Getters and setter for all object attributes.
 */
@Entity
@Table(name = "volunteers")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long volunteerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Transient
    private List<String> tasks = new ArrayList<>();

    @Transient
    private List<String> schedule = new ArrayList<>();

    /**
     * Default Volunteer Constructor without parameters.
     */
    public Volunteer() {}

    /**
     * Volunteer Constructor with parameters.
     * @param name
     * @param email
     * @param phone
     */
    public Volunteer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Method to assign a new task to a volunteer.
     * @param task Task description
     */
    public void assignTask(String task) {
        tasks.add(task);
        System.out.println("Task assigned to " + name + ": " + task );
    }

    /**
     * Method to add a new schedule entry.
     * @param scheduleEntry Day or shift the volunteer is available
     */
    public void assignSchedule(String scheduleEntry) {
        schedule.add(scheduleEntry);
    }

    /**
     * Method to get a Volunteer's name.
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set a Volunteer's name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get a Volunteer's name.
     * @return String name
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to set a Volunteer's email.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get a Volunteer's name.
     * @return String name
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Method to set a Volunteer's phone.
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Method to get a Volunteer's name.
     * @return String name
     */
    public List<String> getTasks() {
        return tasks;
    }

    /**
     * Method to get a Volunteer's name.
     * @return String name
     */
    public List<String> getAvailableSchedules() {
        return schedule;
    }

    /**
     * Method to display all information on a Volunteer.
     */
    public void displayInfo() {
        System.out.println("Volunteer: " + name);
        System.out.println("Email: " + email + "| Phone: " + phone);
        System.out.println("Available days: " + String.join(", ", schedule));
        if (tasks.isEmpty()) {
            System.out.println("No assigned tasks");
        } else {
            System.out.println("Tasks: ");
            for (String task : tasks) {
                System.out.println(" - " + task);
            }
        }
    }
}
