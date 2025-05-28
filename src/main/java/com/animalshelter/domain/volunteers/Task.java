package com.animalshelter.domain.volunteers;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "volunteerId")
    private Volunteer volunteer;

    public Task() {};

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public void setVolunteer(Volunteer volunteer) {
        if (volunteer != null) {
            this.volunteer = volunteer;
            volunteer.assignTask(Task.this);
        } else {
            throw new IllegalArgumentException("Volunteer cannot be null");
        }
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && description.trim().length() > 3) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        if (id != null) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    @Override
    public String toString() {
        return description + (completed ? " (Done)" : " (Pending)");
    }

}