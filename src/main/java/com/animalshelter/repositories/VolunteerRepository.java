package com.animalshelter.repositories;

import com.animalshelter.domain.volunteers.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    Optional<Volunteer> findVolunteerByEmail(String volunteerEmail);

    List<Volunteer> findVolunteersByNameContainingIgnoreCase(String name);
}
