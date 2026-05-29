package mainapp;

import model.Student;
import model.Unit;
import util.SearchSortUtil;

import java.util.ArrayList;

public class AlgorithmDemo {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("ST001", "Student 1", "student1@example.com"));
        students.add(new Student("ST002", "Student 2", "student2@example.com"));
        students.add(new Student("ST003", "Student 3", "student3@example.com"));
        students.add(new Student("ST004", "Student 4", "student4@example.com"));
        students.add(new Student("ST005", "Student 5", "student5@example.com"));

        ArrayList<Unit> units = new ArrayList<>();
        units.add(new Unit("BN101", "Introduction to Networking", 3,
                "Introduces basic networking concepts, devices, IP addressing, and network communication."));
        units.add(new Unit("BN102", "Programming Fundamentals", 3,
                "Covers problem-solving, algorithms, and basic programming using Java."));
        units.add(new Unit("BN201", "Database Systems", 3,
                "Introduces database concepts, SQL queries, normalization, and database design."));
        units.add(new Unit("BN202", "Internetworking Technologies", 3,
                "Covers routing, switching, VLANs, and network device configuration."));
        units.add(new Unit("BN203", "Cybersecurity Fundamentals", 3,
                "Introduces cybersecurity concepts, threats, vulnerabilities, and security controls."));
        units.add(new Unit("BN204", "Web Application Development", 3,
                "Covers HTML, CSS, JavaScript, and dynamic web application development."));
        units.add(new Unit("BN205", "Operating Systems", 3,
                "Explains operating system concepts including processes, memory, and file systems."));
        units.add(new Unit("BN301", "Cloud Computing", 3,
                "Introduces cloud service models, virtualization, and cloud deployment concepts."));
        units.add(new Unit("BN302", "Software Engineering", 3,
                "Covers software development life cycle, design principles, testing, and version control."));

        System.out.println("COLLEGE MANAGEMENT SYSTEM - Q9 SEARCH AND SORT DEMO");
        System.out.println("---------------------------------------------------");

        System.out.println("Original student order:");
        printStudents(students);

        System.out.println("\nInsertion sort by student name:");
        ArrayList<Student> sortedStudents = SearchSortUtil.insertionSortStudentsByName(students);
        printStudents(sortedStudents);

        String targetStudentId = "ST003";
        int foundStudentIndex = SearchSortUtil.linearSearchStudentById(students, targetStudentId);

        System.out.println("\nLinear search for student ID " + targetStudentId + ": index " + foundStudentIndex);

        if (foundStudentIndex != -1) {
            System.out.println("Found student: " + students.get(foundStudentIndex));
        } else {
            System.out.println("Student not found.");
        }

        System.out.println("\nOriginal unit order:");
        printUnits(units);

        System.out.println("\nBubble sort by unit code:");
        ArrayList<Unit> sortedUnits = SearchSortUtil.bubbleSortUnitsByCode(units);
        printUnits(sortedUnits);

        String targetUnitCode = "BN204";
        int foundUnitIndex = SearchSortUtil.binarySearchUnitByCode(sortedUnits, targetUnitCode);

        System.out.println("\nBinary search for unit code " + targetUnitCode + ": index " + foundUnitIndex);

        if (foundUnitIndex != -1) {
            System.out.println("Found unit: " + sortedUnits.get(foundUnitIndex));
        } else {
            System.out.println("Unit not found.");
        }
    }

    private static void printStudents(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println("- " + student);
        }
    }

    private static void printUnits(ArrayList<Unit> units) {
        for (Unit unit : units) {
            System.out.println("- " + unit);
        }
    }
}