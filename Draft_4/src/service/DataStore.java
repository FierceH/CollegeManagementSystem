package service;

import java.util.ArrayList;
import model.Unit;
import model.UnitOffering;
import model.Instructor;
import model.Student;
import model.Enrolment;

public class DataStore {
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<UnitOffering> unitOfferings = new ArrayList<>();
    private ArrayList<Instructor> instructors = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Enrolment> enrolments = new ArrayList<>();

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<UnitOffering> getUnitOfferings() {
        return unitOfferings;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Enrolment> getEnrolments() {
        return enrolments;
    }
}