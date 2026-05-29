import model.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
    private Unit unit;

    @BeforeEach
    void setUp() {
        unit = new Unit("BN231", "Software Development Skills and Tools", 15, "OOP assignment");
    }

    @Test
    void validUnitStoresDetails() {
        assertEquals("BN231", unit.getCode());
        assertEquals("Software Development Skills and Tools", unit.getName());
        assertEquals(15, unit.getCredits());
    }

    @Test
    void zeroCreditsThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Unit("BN231", "Software Development Skills and Tools", 0, "OOP assignment"));
    }

    @Test
    void duplicatePrerequisiteIsNotAddedTwice() {
        unit.addPrerequisite("BN111");
        unit.addPrerequisite("bn111");

        assertEquals(1, unit.getPrerequisites().size());
        assertTrue(unit.getPrerequisites().contains("BN111"));
    }
}
