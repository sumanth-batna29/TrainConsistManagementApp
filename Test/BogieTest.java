import org.junit.Test;
import static org.junit.Assert.*;

public class BogieTest {

    // ✅ 1. Valid Capacity Creation
    @Test
    public void testException_ValidCapacityCreation() throws Exception {
        PassengerBogie bogie = new PassengerBogie("Sleeper", 72);
        assertNotNull(bogie);
        assertEquals("Sleeper", bogie.getType());
        assertEquals(72, bogie.getCapacity());
    }

    // ✅ 2. Negative Capacity → Exception
    @Test(expected = InvalidCapacityException.class)
    public void testException_NegativeCapacityThrowsException() throws Exception {
        new PassengerBogie("Sleeper", -10);
    }

    // ✅ 3. Zero Capacity → Exception
    @Test(expected = InvalidCapacityException.class)
    public void testException_ZeroCapacityThrowsException() throws Exception {
        new PassengerBogie("Sleeper", 0);
    }

    // ✅ 4. Exception Message Validation
    @Test
    public void testException_ExceptionMessageValidation() {
        try {
            new PassengerBogie("Sleeper", 0);
            fail("Expected InvalidCapacityException");
        } catch (InvalidCapacityException e) {
            assertEquals("Capacity must be greater than zero", e.getMessage());
        }
    }

    // ✅ 5. Object Integrity After Creation
    @Test
    public void testException_ObjectIntegrityAfterCreation() throws Exception {
        PassengerBogie bogie = new PassengerBogie("AC Chair", 50);

        assertEquals("AC Chair", bogie.getType());
        assertEquals(50, bogie.getCapacity());
    }

    // ✅ 6. Multiple Valid Bogies
    @Test
    public void testException_MultipleValidBogiesCreation() throws Exception {
        PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
        PassengerBogie b2 = new PassengerBogie("AC Chair", 50);
        PassengerBogie b3 = new PassengerBogie("First Class", 20);

        assertNotNull(b1);
        assertNotNull(b2);
        assertNotNull(b3);
    }
}