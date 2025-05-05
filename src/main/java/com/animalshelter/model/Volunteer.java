package com.animalshelter.model;

import java.util.ArrayList;
import java.util.List;


public class Volunteer {
    private String name;
    private String email;
    private String phone;

    private List<String> tasks;
    private List<String> schedule;

    public Volunteer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;

        this.tasks = new ArrayList<>();
        this.schedule = new ArrayList<>();
    }

    /**
     * Assign a new task to a volunteer
     * @param task Task description
     */
    public void assignTask(String task) {
        tasks.add(task);
        System.out.println("Task assigned to " + name + ": " + task );
    }

    /**
     * Add a new schedule entry
     * @param scheduleEntry Day or shift the volunteer is available
     */
    public void assignSchedule(String scheduleEntry) {
        schedule.add(scheduleEntry);
    }


    // getters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getTasks() {
        return tasks;
    }


    public List<String> getAvailableSchedules() {
        return schedule;
    }

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
