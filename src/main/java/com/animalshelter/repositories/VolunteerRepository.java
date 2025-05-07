package com.animalshelter.repositories;

import com.animalshelter.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Optional<Volunteer> findVolunteerByEmail(String volunteerEmail);

    List<Volunteer> findVolunteersByNameContainingIgnoreCase(String name);
}
