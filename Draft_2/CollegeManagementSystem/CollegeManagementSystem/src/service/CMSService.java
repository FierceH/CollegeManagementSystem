package service;

import java.util.ArrayList;
import model.Unit;
import model.UnitOffering;
import model.Instructor;
import model.Student;
import model.Enrolment;

public class CMSService {
    private DataStore dataStore;

    public CMSService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void addUnit(Unit unit) {
        dataStore.getUnits().add(unit);
    }

    public ArrayList<Unit> viewUnits() {
        return dataStore.getUnits();
    }

    public void addUnitOffering(UnitOffering unitOffering) {
        dataStore.getUnitOfferings().add(unitOffering);
    }

    public ArrayList<UnitOffering> viewUnitOfferings() {
        return dataStore.getUnitOfferings();
    }

    public void registerInstructor(Instructor instructor) {
        dataStore.getInstructors().add(instructor);
    }

    public void registerStudent(Student student) {
        dataStore.getStudents().add(student);
    }

    public void enrolStudent(Enrolment enrolment) {
        dataStore.getEnrolments().add(enrolment);
    }

    public ArrayList<Enrolment> filterEnrolmentsByStudent(String studentID) {
        ArrayList<Enrolment> result = new ArrayList<>();

        for (Enrolment enrolment : dataStore.getEnrolments()) {
            if (enrolment.getStudent().getStudentID().equalsIgnoreCase(studentID)) {
                result.add(enrolment);
            }
        }

        return result;
    }

    public ArrayList<Enrolment> filterEnrolmentsByOffering(String offeringID) {
        ArrayList<Enrolment> result = new ArrayList<>();

        for (Enrolment enrolment : dataStore.getEnrolments()) {
            if (enrolment.getUnitOffering().getOfferingID().equalsIgnoreCase(offeringID)) {
                result.add(enrolment);
            }
        }

        return result;
    }
}