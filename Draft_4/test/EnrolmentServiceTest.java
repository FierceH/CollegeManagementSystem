import model.Enrolment;
import model.Student;
import model.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.EnrolmentService;

import static org.junit.jupiter.api.Assertions.*;

public class EnrolmentServiceTest {
    private EnrolmentService service;

    @BeforeEach
    void setUp() {
        service = new EnrolmentService();
        service.addStudent(new Student("MIT241150", "Zilan Boidya", "zilan.boidya@student.mit.edu.au"));
        service.addUnit(new Unit("BN231", "Software Development Skills and Tools", 15, "OOP assignment"));
    }

    @Test
    void addDuplicateStudentReturnsFalse() {
        boolean result = service.addStudent(new Student("MIT241150", "Zilan B", "zilanb@student.mit.edu.au"));

        assertFalse(result);
        assertEquals(1, service.getStudents().size());
    }

    @Test
    void enrolStudentCreatesEnrolmentRecord() {
        Enrolment enrolment = service.enrolStudent("MIT241150", "BN231", "T1 2026");

        assertEquals("MIT241150", enrolment.getStudent().getStudentId());
        assertEquals("BN231", enrolment.getUnit().getCode());
        assertEquals(1, service.getEnrolmentsByStudent("MIT241150").size());
    }

    @Test
    void duplicateEnrolmentThrowsException() {
        service.enrolStudent("MIT241150", "BN231", "T1 2026");

        assertThrows(IllegalStateException.class, () ->
                service.enrolStudent("MIT241150", "BN231", "T1 2026"));
    }
}
