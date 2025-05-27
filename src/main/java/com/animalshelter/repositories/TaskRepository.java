package com.animalshelter.repositories;

import com.animalshelter.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByVolunteerVolunteerId(Long volunteerId);
}
