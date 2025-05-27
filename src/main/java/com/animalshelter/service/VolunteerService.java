package com.animalshelter.service;

import com.animalshelter.model.Volunteer;
import com.animalshelter.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public Optional<Volunteer> getVolunteerByEmail(String volunteerEmail) {

        // SQL QUERY TO SEARCH BY EMAIL
        return volunteerRepository.findVolunteerByEmail(volunteerEmail);
    }

    public List<Volunteer> getVolunteerByName(String name) {

        // SQL QUERY TO SEARCH BY NAME
        return volunteerRepository.findVolunteersByNameContainingIgnoreCase(name);
    }

    public Volunteer addVolunteer(Volunteer volunteer) {

        if (volunteerRepository.findVolunteerByEmail(volunteer.getEmail()).isPresent()) {
            throw new RuntimeException("Volunteer already exists");
        }
        return volunteerRepository.save(volunteer);
    }
}