package tests;

import com.animalshelter.domain.volunteers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;
    private Volunteer volunteer;

    @BeforeEach
    void setUp() {
        task = new Task("Clean kennels", false);
        volunteer = new Volunteer("Bob Smith", "bob@example.com", "555-5678");
    }

    @Test
    void testTaskCreation() {
        assertEquals("Clean kennels", task.getDescription());
        assertFalse(task.isCompleted());
        assertNull(task.getVolunteer());
    }

    @Test
    void setDescription_Valid_ShouldUpdate() {
        task.setDescription("Feed animals");
        assertEquals("Feed animals", task.getDescription());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void setDescription_Invalid_ShouldThrow(String description) {
        assertThrows(IllegalArgumentException.class, () -> task.setDescription(description));
    }

    @Test
    void setCompleted_ShouldUpdateStatus() {
        task.setCompleted(true);
        assertTrue(task.isCompleted());
    }

    @Test
    void setVolunteer_ShouldUpdateAssociation() {
        task.setVolunteer(volunteer);
        assertEquals(volunteer, task.getVolunteer());
        assertTrue(volunteer.getTasks().contains(task));
    }
}