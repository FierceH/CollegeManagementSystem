import model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void validStudentStoresDetails() {
        Student student = new Student("MIT241150", "Zilan Boidya", "zilan.boidya@student.mit.edu.au");

        assertEquals("MIT241150", student.getStudentId());
        assertEquals("Zilan Boidya", student.getFullName());
        assertEquals("zilan.boidya@student.mit.edu.au", student.getEmail());
    }

    @Test
    void invalidEmailThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Student("MIT241150", "Zilan Boidya", "invalid-email"));
    }

    @Test
    void updateNameChangesStudentName() {
        Student student = new Student("MIT241150", "Zilan Boidya", "zilan.boidya@student.mit.edu.au");

        student.setFullName("Zilan B");

        assertEquals("Zilan B", student.getFullName());
    }
}
