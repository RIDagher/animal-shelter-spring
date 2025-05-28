package tests;

import com.animalshelter.domain.animals.*;
import com.animalshelter.domain.animals.enums.*;
import com.animalshelter.domain.medical.MedicalEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class MedicalEntryTest {

    private MedicalEntry medicalEntry;
    private Animal animal;

    @BeforeEach
    void setUp() {
        animal = new Dog("Buddy", 3, Sex.Male, "Labrador", Size.Large, "Golden", true, "Medium");
        medicalEntry = new MedicalEntry("Annual checkup", "Smith", LocalDate.now(), animal);
    }

    @Test
    void testMedicalEntryCreation() {
        assertEquals("Annual checkup", medicalEntry.getDescription());
        assertEquals("Smith", medicalEntry.getVeteranName());
        assertEquals(LocalDate.now(), medicalEntry.getDate());
        assertEquals(animal, medicalEntry.getAnimal());
    }

    @Test
    void displayEntry_ShouldNotThrow() {
        assertDoesNotThrow(() -> medicalEntry.displayEntry());
    }

    @Test
    void medicalEntry_WithNullDescription_ShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new MedicalEntry(null, "Smith", LocalDate.now(), animal));
    }

    @Test
    void medicalEntry_WithNullVetName_ShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new MedicalEntry("Checkup", null, LocalDate.now(), animal));
    }

    @Test
    void medicalEntry_WithNullDate_ShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new MedicalEntry("Checkup", "Smith", null, animal));
    }

    @Test
    void medicalEntry_WithNullAnimal_ShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> new MedicalEntry("Checkup", "Smith", LocalDate.now(), null));
    }
}