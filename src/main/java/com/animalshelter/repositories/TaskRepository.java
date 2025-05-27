package com.animalshelter.repositories;

import com.animalshelter.domain.volunteers.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByVolunteerVolunteerId(Long volunteerId);
}