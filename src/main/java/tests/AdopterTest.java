package tests;

import com.animalshelter.domain.adoptions.Adopter;
import com.animalshelter.domain.animals.*;
import com.animalshelter.domain.animals.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class AdopterTest {

    private Adopter adopter;
    private Animal adoptedAnimal;

    @BeforeEach
    void setUp() {
        adopter = new Adopter("John Doe");
        adoptedAnimal = new Dog("Buddy", 3, Sex.Male, "Labrador", Size.Large, "Golden", true, "Medium");
        adoptedAnimal.setAdopted(true);
    }

    @Test
    void testAdopterCreation() {
        assertEquals("John Doe", adopter.getName());
        assertTrue(adopter.getAdoptedAnimals().isEmpty());
    }

    @Test
    void setName_ValidName_ShouldUpdate() {
        adopter.setName("Jane Smith");
        assertEquals("Jane Smith", adopter.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void setName_InvalidName_ShouldThrow(String name) {
        assertThrows(IllegalArgumentException.class, () -> adopter.setName(name));
    }

    @Test
    void addAdoptedAnimal_ValidAnimal_ShouldAdd() {
        adopter.addAdoptedAnimal(adoptedAnimal);
        assertEquals(1, adopter.getAdoptedAnimals().size());
        assertEquals(adoptedAnimal, adopter.getAdoptedAnimals().get(0));
    }

    @Test
    void addAdoptedAnimal_NotAdoptedAnimal_ShouldThrow() {
        Animal notAdopted = new Cat("Whiskers", 2, Sex.Female, "Siamese", Size.Medium, "White", true, "Playful");
        assertThrows(IllegalArgumentException.class, () -> adopter.addAdoptedAnimal(notAdopted));
    }

    @Test
    void addAdoptedAnimal_NullAnimal_ShouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> adopter.addAdoptedAnimal(null));
    }

    @Test
    void removeAdoptedAnimal_ExistingAnimal_ShouldRemove() {
        adopter.addAdoptedAnimal(adoptedAnimal);
        adopter.removeAdoptedAnimal(adoptedAnimal);
        assertTrue(adopter.getAdoptedAnimals().isEmpty());
    }

    @Test
    void displayAdoptedAnimals_EmptyList_ShouldPrintMessage() {
        assertDoesNotThrow(() -> adopter.displayAdoptedAnimals());
    }

    @Test
    void displayAdoptedAnimals_WithAnimals_ShouldPrintInfo() {
        adopter.addAdoptedAnimal(adoptedAnimal);
        assertDoesNotThrow(() -> adopter.displayAdoptedAnimals());
    }
}