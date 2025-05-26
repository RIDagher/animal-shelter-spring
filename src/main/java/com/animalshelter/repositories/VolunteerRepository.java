package com.animalshelter.repositories;

import com.animalshelter.domain.volunteers.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    List<Volunteer> findVolunteerByName(String name);
}
