package service;

import model.Enrolment;
import model.Instructor;
import model.Student;
import model.Unit;
import model.UnitOffering;

import java.util.ArrayList;

public class EnrolmentService {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<Enrolment> enrolments = new ArrayList<>();

    public boolean addStudent(Student student) {
        for (Student existingStudent : students) {
            if (existingStudent.getStudentId().equalsIgnoreCase(student.getStudentId())) {
                return false;
            }
        }

        students.add(student);
        return true;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Enrolment enrolStudent(String studentId, String unitCode, String semester) {
        Student selectedStudent = null;
        Unit selectedUnit = null;

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                selectedStudent = student;
                break;
            }
        }

        for (Unit unit : units) {
            if (unit.getCode().equalsIgnoreCase(unitCode)) {
                selectedUnit = unit;
                break;
            }
        }

        if (selectedStudent == null || selectedUnit == null) {
            throw new IllegalArgumentException("Student or unit not found.");
        }

        for (Enrolment enrolment : enrolments) {
            if (enrolment.getStudent().getStudentId().equalsIgnoreCase(studentId)
                    && enrolment.getUnitOffering().getUnit().getCode().equalsIgnoreCase(unitCode)) {
                throw new IllegalStateException("Student is already enrolled in this unit.");
            }
        }

        Instructor defaultInstructor = new Instructor("INS000", "Default Instructor");
        UnitOffering offering = new UnitOffering("OFF-" + unitCode, semester, 2026, 30, selectedUnit, defaultInstructor);

        Enrolment enrolment = new Enrolment(
                "ENR" + (enrolments.size() + 1),
                "2026-02-10",
                "Active",
                selectedStudent,
                offering
        );

        enrolments.add(enrolment);
        return enrolment;
    }

    public ArrayList<Enrolment> getEnrolmentsByStudent(String studentId) {
        ArrayList<Enrolment> result = new ArrayList<>();

        for (Enrolment enrolment : enrolments) {
            if (enrolment.getStudent().getStudentId().equalsIgnoreCase(studentId)) {
                result.add(enrolment);
            }
        }

        return result;
    }
}