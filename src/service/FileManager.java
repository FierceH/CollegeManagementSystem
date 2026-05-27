package service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Unit;
import model.Student;
import model.Instructor;
import model.UnitOffering;
import model.Enrolment;

public class FileManager {

    public void saveUnits(ArrayList<Unit> units) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("units.txt"));

            for (Unit unit : units) {
                writer.println(unit.getUnitCode() + "," +
                        unit.getUnitName() + "," +
                        unit.getCredits() + "," +
                        unit.getDescription() + "," +
                        unit.getPrerequisites());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving units: " + e.getMessage());
        }
    }

    public void saveStudents(ArrayList<Student> students) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("students.txt"));

            for (Student student : students) {
                writer.println(student.getStudentID() + "," +
                        student.getStudentName() + "," +
                        student.getEmail());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public void saveInstructors(ArrayList<Instructor> instructors) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("instructors.txt"));

            for (Instructor instructor : instructors) {
                writer.println(instructor.getInstructorID() + "," +
                        instructor.getInstructorName());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving instructors: " + e.getMessage());
        }
    }

    public void saveUnitOfferings(ArrayList<UnitOffering> unitOfferings) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("unitOfferings.txt"));

            for (UnitOffering offering : unitOfferings) {
                writer.println(offering.getOfferingID() + "," +
                        offering.getUnit().getUnitCode() + "," +
                        offering.getSemester() + "," +
                        offering.getYear() + "," +
                        offering.getCapacity() + "," +
                        offering.getInstructor().getInstructorID());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving unit offerings: " + e.getMessage());
        }
    }

    public void saveEnrolments(ArrayList<Enrolment> enrolments) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("enrolments.txt"));

            for (Enrolment enrolment : enrolments) {
                writer.println(enrolment.getEnrolmentID() + "," +
                        enrolment.getEnrolmentDate() + "," +
                        enrolment.getStatus() + "," +
                        enrolment.getStudent().getStudentID() + "," +
                        enrolment.getUnitOffering().getOfferingID());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving enrolments: " + e.getMessage());
        }
    }
}