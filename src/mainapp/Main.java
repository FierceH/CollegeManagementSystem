package mainapp;

import model.*;
import service.*;

public class Main {
    public static void main(String[] args) {
        DataStore dataStore = new DataStore();
        CMSService cmsService = new CMSService(dataStore);
        ReportService reportService = new ReportService();
        FileManager fileManager = new FileManager();

        Unit unit1 = new Unit("BN101", "Introduction to Networking", 3,
                "Introduces basic networking concepts and IP addressing.");
        Unit unit2 = new Unit("BN102", "Programming Fundamentals", 3,
                "Covers problem-solving and Java programming.");

        unit2.addPrerequisite("None");

        Instructor instructor1 = new Instructor("INS001", "Dr. Michael Perera");
        Instructor instructor2 = new Instructor("INS002", "Ms. Sarah Lim");

        Student student1 = new Student("ST001", "Student 1", "student1@example.com");
        Student student2 = new Student("ST002", "Student 2", "student2@example.com");

        UnitOffering offering1 = new UnitOffering("OFF001", "T1", 2026, 30, unit1, instructor1);
        UnitOffering offering2 = new UnitOffering("OFF002", "T2", 2026, 30, unit2, instructor2);

        Enrolment enrolment1 = new Enrolment("ENR001", "2026-02-10", "Active", student1, offering1);
        Enrolment enrolment2 = new Enrolment("ENR002", "2026-06-15", "Active", student2, offering2);

        cmsService.addUnit(unit1);
        cmsService.addUnit(unit2);

        cmsService.registerInstructor(instructor1);
        cmsService.registerInstructor(instructor2);

        cmsService.registerStudent(student1);
        cmsService.registerStudent(student2);

        cmsService.addUnitOffering(offering1);
        cmsService.addUnitOffering(offering2);

        cmsService.enrolStudent(enrolment1);
        cmsService.enrolStudent(enrolment2);

        System.out.println(reportService.generateStudentEnrolmentReport(dataStore.getEnrolments()));
        System.out.println(reportService.generateClassAllocationReport(dataStore.getEnrolments()));

        fileManager.saveUnits(dataStore.getUnits());
        fileManager.saveStudents(dataStore.getStudents());
        fileManager.saveInstructors(dataStore.getInstructors());
        fileManager.saveUnitOfferings(dataStore.getUnitOfferings());
        fileManager.saveEnrolments(dataStore.getEnrolments());

        System.out.println("Data saved to files successfully.");
    }
}