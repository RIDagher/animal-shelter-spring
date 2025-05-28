package com.animalshelter.domain.volunteers;

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
        setName(name);
        setEmail(email);
        setPhone(phone);
    }

    /**
     * Assign a new task to a volunteer
     * @param task Task description
     */
    public void assignTask(Task task) {
        if (task != null) {
            tasks.add(task);
            System.out.println("Task assigned to " + name + ": " + task );
        } else {
            throw new IllegalArgumentException("Task cannot be null");
        }
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
        if (name != null && name.length() > 3) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (email != null && email.length() > 3 && email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email cannot be empty");
        }
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        if (phone != null && phone.length() > 3) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Phone cannot be empty");
        }
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