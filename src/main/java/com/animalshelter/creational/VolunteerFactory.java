package com.animalshelter.creational;

import com.animalshelter.domain.volunteers.Volunteer;
import java.util.List;

/**
 * Factory for creating Volunteer objects
 */
public class VolunteerFactory {

    /**
     * Creates a basic Volunteer with contact information
     * @param name Volunteer's full name
     * @param email Valid email address
     * @param phone Phone number
     * @return Created Volunteer
     */
    public Volunteer createVolunteer(String name, String email, String phone) {
        validateContactInfo(name, email, phone);
        return new Volunteer(name, email, phone);
    }

    /**
     * Creates a Volunteer with initial schedule entries
     * @param name Volunteer's full name
     * @param email Valid email address
     * @param phone Phone number
     * @param schedule List of schedule entries
     * @return Created Volunteer
     */
    public Volunteer createVolunteerWithSchedule(String name, String email, String phone, List<String> schedule) {
        Volunteer volunteer = createVolunteer(name, email, phone);
        if (schedule != null) {
            for (String entry : schedule) {
                if (entry != null && !entry.trim().isEmpty()) {
                    volunteer.assignSchedule(entry);
                }
            }
        }
        return volunteer;
    }

    private void validateContactInfo(String name, String email, String phone) {
        if (name == null || name.trim().length() < 3) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (email == null || email.trim().length() < 5 || !email.trim().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (phone == null || phone.trim().length() < 3) {
            throw new IllegalArgumentException("Phone cannot be null or empty");
        }
    }
}