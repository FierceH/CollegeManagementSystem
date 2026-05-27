package mainapp;

import model.*;
import service.*;

public class Main {
    public static void main(String[] args) {
        DataStore dataStore = new DataStore();
        CMSService cmsService = new CMSService(dataStore);
        ReportService reportService = new ReportService();
        FileManager fileManager = new FileManager();

        // -----------------------------
        // Units
        // -----------------------------
        Unit unit1 = new Unit("BN101", "Introduction to Networking", 3,
                "Introduces basic networking concepts, devices, IP addressing, and network communication.");

        Unit unit2 = new Unit("BN102", "Programming Fundamentals", 3,
                "Covers problem-solving, algorithms, and basic programming using Java.");

        Unit unit3 = new Unit("BN201", "Database Systems", 3,
                "Introduces database concepts, SQL queries, normalization, and database design.");
        unit3.addPrerequisite("BN102");

        Unit unit4 = new Unit("BN202", "Internetworking Technologies", 3,
                "Covers routing, switching, VLANs, and network device configuration.");
        unit4.addPrerequisite("BN101");

        Unit unit5 = new Unit("BN203", "Cybersecurity Fundamentals", 3,
                "Introduces cybersecurity concepts, threats, vulnerabilities, and security controls.");
        unit5.addPrerequisite("BN101");

        Unit unit6 = new Unit("BN204", "Web Application Development", 3,
                "Covers HTML, CSS, JavaScript, and dynamic web application development.");
        unit6.addPrerequisite("BN102");
        unit6.addPrerequisite("BN201");

        Unit unit7 = new Unit("BN205", "Operating Systems", 3,
                "Explains operating system concepts including processes, memory, and file systems.");

        Unit unit8 = new Unit("BN301", "Cloud Computing", 3,
                "Introduces cloud service models, virtualization, and cloud deployment concepts.");
        unit8.addPrerequisite("BN202");
        unit8.addPrerequisite("BN205");

        Unit unit9 = new Unit("BN302", "Software Engineering", 3,
                "Covers software development life cycle, design principles, testing, and version control.");
        unit9.addPrerequisite("BN102");

        cmsService.addUnit(unit1);
        cmsService.addUnit(unit2);
        cmsService.addUnit(unit3);
        cmsService.addUnit(unit4);
        cmsService.addUnit(unit5);
        cmsService.addUnit(unit6);
        cmsService.addUnit(unit7);
        cmsService.addUnit(unit8);
        cmsService.addUnit(unit9);

        // -----------------------------
        // Instructors
        // -----------------------------
        Instructor instructor1 = new Instructor("INS001", "Dr. Michael Perera");
        Instructor instructor2 = new Instructor("INS002", "Ms. Sarah Lim");
        Instructor instructor3 = new Instructor("INS003", "Mr. Daniel Fernando");
        Instructor instructor4 = new Instructor("INS004", "Dr. Kevin Silva");
        Instructor instructor5 = new Instructor("INS005", "Ms. Amanda Lee");
        Instructor instructor6 = new Instructor("INS006", "Mr. Jason Tan");
        Instructor instructor7 = new Instructor("INS007", "Dr. Rachel Kumar");
        Instructor instructor8 = new Instructor("INS008", "Mr. Andrew Collins");
        Instructor instructor9 = new Instructor("INS009", "Dr. Emily Watson");

        cmsService.registerInstructor(instructor1);
        cmsService.registerInstructor(instructor2);
        cmsService.registerInstructor(instructor3);
        cmsService.registerInstructor(instructor4);
        cmsService.registerInstructor(instructor5);
        cmsService.registerInstructor(instructor6);
        cmsService.registerInstructor(instructor7);
        cmsService.registerInstructor(instructor8);
        cmsService.registerInstructor(instructor9);

        // -----------------------------
        // Unit Offerings
        // -----------------------------
        UnitOffering offering1 = new UnitOffering("OFF001", "T1", 2026, 30, unit1, instructor1);
        UnitOffering offering2 = new UnitOffering("OFF002", "T1", 2026, 30, unit2, instructor2);
        UnitOffering offering3 = new UnitOffering("OFF003", "T2", 2026, 30, unit3, instructor3);
        UnitOffering offering4 = new UnitOffering("OFF004", "T2", 2026, 30, unit4, instructor4);
        UnitOffering offering5 = new UnitOffering("OFF005", "T2", 2026, 30, unit5, instructor5);
        UnitOffering offering6 = new UnitOffering("OFF006", "T3", 2026, 30, unit6, instructor6);
        UnitOffering offering7 = new UnitOffering("OFF007", "T3", 2026, 30, unit7, instructor7);
        UnitOffering offering8 = new UnitOffering("OFF008", "T1", 2027, 30, unit8, instructor8);
        UnitOffering offering9 = new UnitOffering("OFF009", "T1", 2027, 30, unit9, instructor9);

        cmsService.addUnitOffering(offering1);
        cmsService.addUnitOffering(offering2);
        cmsService.addUnitOffering(offering3);
        cmsService.addUnitOffering(offering4);
        cmsService.addUnitOffering(offering5);
        cmsService.addUnitOffering(offering6);
        cmsService.addUnitOffering(offering7);
        cmsService.addUnitOffering(offering8);
        cmsService.addUnitOffering(offering9);

        // -----------------------------
        // Students and Enrolments
        // -----------------------------
        addStudentsAndEnrolments(cmsService, offering1, 1, 5);
        addStudentsAndEnrolments(cmsService, offering2, 6, 10);
        addStudentsAndEnrolments(cmsService, offering3, 11, 15);
        addStudentsAndEnrolments(cmsService, offering4, 16, 20);
        addStudentsAndEnrolments(cmsService, offering5, 21, 25);
        addStudentsAndEnrolments(cmsService, offering6, 26, 30);
        addStudentsAndEnrolments(cmsService, offering7, 31, 35);
        addStudentsAndEnrolments(cmsService, offering8, 36, 40);
        addStudentsAndEnrolments(cmsService, offering9, 41, 45);

        // -----------------------------
        // Reports
        // -----------------------------
        System.out.println(reportService.generateStudentEnrolmentReport(dataStore.getEnrolments()));
        System.out.println(reportService.generateClassAllocationReport(dataStore.getEnrolments()));

        // -----------------------------
        // Save data to files
        // -----------------------------
        fileManager.saveUnits(dataStore.getUnits());
        fileManager.saveStudents(dataStore.getStudents());
        fileManager.saveInstructors(dataStore.getInstructors());
        fileManager.saveUnitOfferings(dataStore.getUnitOfferings());
        fileManager.saveEnrolments(dataStore.getEnrolments());

        System.out.println("Data saved to files successfully.");
    }

    private static void addStudentsAndEnrolments(CMSService cmsService, UnitOffering offering, int start, int end) {
        for (int i = start; i <= end; i++) {
            String studentID = String.format("ST%03d", i);
            String enrolmentID = String.format("ENR%03d", i);

            Student student = new Student(studentID, "Student " + i, "student" + i + "@example.com");
            Enrolment enrolment = new Enrolment(enrolmentID, "2026-02-10", "Active", student, offering);

            cmsService.registerStudent(student);
            cmsService.enrolStudent(enrolment);
        }
    }
}