package tests;

import com.animalshelter.domain.volunteers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VolunteerTest {

    private Volunteer volunteer;
    private Task task;

    @BeforeEach
    void setUp() {
        volunteer = new Volunteer("Alice Johnson", "alice@example.com", "555-1234");
        task = new Task("Clean kennels", false);
    }

    @Test
    void testVolunteerCreation() {
        assertEquals("Alice Johnson", volunteer.getName());
        assertEquals("alice@example.com", volunteer.getEmail());
        assertEquals("555-1234", volunteer.getPhone());
        assertTrue(volunteer.getTasks().isEmpty());
        assertTrue(volunteer.getAvailableSchedules().isEmpty());
    }

    @Test
    void assignTask_ShouldAddTask() {
        volunteer.assignTask(task);
        assertEquals(1, volunteer.getTasks().size());
        assertEquals(task, volunteer.getTasks().get(0));
    }

    @Test
    void assignSchedule_ShouldAddSchedule() {
        volunteer.assignSchedule("Monday Morning");
        assertEquals(1, volunteer.getAvailableSchedules().size());
        assertEquals("Monday Morning", volunteer.getAvailableSchedules().get(0));
    }

    @Test
    void setEmail_InvalidFormat_ShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> volunteer.setEmail("email with no at sign"));
    }

    @Test
    void setPhone_InvalidFormat_ShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> volunteer.setPhone("123"));
    }
}