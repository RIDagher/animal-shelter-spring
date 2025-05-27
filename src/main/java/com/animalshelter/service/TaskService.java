package com.animalshelter.service;

import com.animalshelter.domain.volunteers.Task;
import com.animalshelter.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksForVolunteer(Long volunteerId) {
        return taskRepository.findByVolunteerVolunteerId(volunteerId);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.deleteById(task.getId());
    }
}