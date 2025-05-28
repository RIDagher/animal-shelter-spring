package tests;

import com.animalshelter.domain.animals.*;
import com.animalshelter.domain.animals.enums.*;
import com.animalshelter.domain.medical.MedicalEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;


class AnimalTest {

    private Bird testBird;
    private Cat testCat;
    private Dog testDog;

    @BeforeEach
    void setUp() {
        testBird = new Bird("Polly", 2, Sex.Male, "Parrot", Size.Medium, "Green", true, "Hooked");
        testCat = new Cat("Whiskers", 3, Sex.Male, "Siamese", Size.Medium, "White", true, "Playful");
        testDog = new Dog("Buddy", 3, Sex.Male, "Labrador", Size.Large, "Golden", true, "Medium");
    }

    // Generic Animal tests
    @Test
    void testAnimalCreation() {
        assertNotNull(testDog);
        assertEquals("Buddy", testDog.getName());
        assertEquals(Species.Dog, testDog.getSpecies());
        assertEquals(3, testDog.getAge());
        assertEquals(Sex.Male, testDog.getAnimalSex());
        assertEquals("Labrador", testDog.getBreed());
        assertEquals(Size.Large, testDog.getSize());
        assertEquals("Golden", testDog.getColor());
        assertFalse(testDog.isAdopted());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void setName_ShouldThrowForEmptyName(String name) {
        assertThrows(IllegalArgumentException.class, () -> testDog.setName(name));
    }

    @Test
    void setAge_ShouldThrowForEmptyAge() {
        testDog.setAge(-1);
        assertEquals(-1, testDog.getAge());
    }

    @Test
    void setAge_ShouldThrowForNegativeBelowOne() {
        assertThrows(IllegalArgumentException.class, () -> testDog.setAge(-2));
    }

    @Test
    void setSpecies_ShouldThrowForNull() {
        assertThrows(IllegalArgumentException.class, () -> testDog.setSpecies(null));
    }

    @Test
    void setAnimalSex_ShouldThrowForNull() {
        assertThrows(IllegalArgumentException.class, () -> testDog.setAnimalSex(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void setBreed_ShouldThrowForEmptyBreed(String breed) {
        assertThrows(IllegalArgumentException.class, () -> testDog.setBreed(breed));
    }

    @Test
    void setSize_ShouldThrowForNull() {
        assertThrows(IllegalArgumentException.class, () -> testDog.setSize(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void setColor_ShouldThrowForEmptyColor(String color) {
        assertThrows(IllegalArgumentException.class, () -> testDog.setColor(color));
    }

    @Test
    void medicalEntries_ShouldBeEmptyByDefault() {
        assertTrue(testDog.getMedicalEntries().isEmpty());
    }

    @Test
    void medicalEntries_ShouldAcceptNewEntries() {
        MedicalEntry entry = new MedicalEntry();
        testDog.getMedicalEntries().add(entry);
        assertEquals(1, testDog.getMedicalEntries().size());
    }

    @Test
    void setAnimalId_ShouldThrowForNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> testDog.setAnimalId(0));
        assertThrows(IllegalArgumentException.class, () -> testDog.setAnimalId(-1));
    }

    // Bird Specific tests
    @Test
    void testBirdCreation() {
        assertTrue(testBird.isCanFly());
        assertEquals("Hooked", testBird.getBeakType());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void setBeakType_ShouldThrowForEmpty(String beakType) {
        assertThrows(IllegalArgumentException.class, () -> testBird.setBeakType(beakType));
    }

    @Test
    void birdAdopt_ShouldChangeStatus() {
        testBird.adopt();
        assertTrue(testBird.isAdopted());
    }

    @Test
    void birdReturnToShelter_ShouldChangeStatus() {
        testBird.adopt();
        testBird.returnToShelter();
        assertFalse(testBird.isAdopted());
    }

    // Cat Specific Tests
    @Test
    void testCatCreation() {
        assertTrue(testCat.isLitterBoxTrained());
        assertEquals("Playful", testCat.getTemperament());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void setTemperament_ShouldThrowForEmpty(String temperament) {
        assertThrows(IllegalArgumentException.class, () -> testCat.setTemperament(temperament));
    }

    @Test
    void catAdopt_ShouldChangeStatus() {
        testCat.adopt();
        assertTrue(testCat.isAdopted());
    }

    // Dog Specific Tests
    @Test
    void testDogCreation() {
        assertTrue(testDog.isTrained());
        assertEquals("Medium", testDog.getBarkVolume());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void setBarkVolume_ShouldThrowForEmpty(String volume) {
        assertThrows(IllegalArgumentException.class, () -> testDog.setBarkVolume(volume));
    }

    @Test
    void dogAdopt_ShouldChangeStatus() {
        testDog.adopt();
        assertTrue(testDog.isAdopted());
    }
}