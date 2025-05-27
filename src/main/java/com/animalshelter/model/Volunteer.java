package com.animalshelter.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> schedule = new ArrayList<>();

    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public Volunteer() {}

    public Volunteer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Assign a new task to a volunteer
     * @param task Task description
     */
    public void assignTask(Task task) {
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

    public List<Task> getTasks() {
        return tasks;
    }


    public List<String> getAvailableSchedules() {
        return schedule;
    }

    @Override
    public String toString() {
    return name + " | " + email + " | " + phone;
    }
}
