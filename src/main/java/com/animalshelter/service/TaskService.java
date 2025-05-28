package com.animalshelter.service;

import com.animalshelter.domain.volunteers.Task;
import com.animalshelter.domain.volunteers.Volunteer;
import com.animalshelter.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.animalshelter.repositories.VolunteerRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    public List<Task> getTasksForVolunteer(Long volunteerId) {
        return taskRepository.findByVolunteerVolunteerId(volunteerId);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Task task) {
        Volunteer volunteer = task.getVolunteer();
        if (volunteer != null) {
            volunteer.getTasks().remove(task);
        }
        taskRepository.delete(task);
    }

    public void assignTaskToVolunteer(Volunteer volunteer, List<String> taskDescriptions) {
        List<Task> newTasks = new ArrayList<>();
        for (String description : taskDescriptions) {
            Task task = new Task(description, false);
            task.setVolunteer(volunteer);
            newTasks.add(task);
        }
        taskRepository.saveAll(newTasks);

    }
}