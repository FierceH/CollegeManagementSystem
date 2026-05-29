package service;

import java.util.ArrayList;
import java.util.List;

import model.Unit;
import model.UnitOffering;
import model.Instructor;
import model.Student;
import model.Enrolment;

public class CMSService {
    private final DataStore dataStore;

    public CMSService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public boolean addUnit(Unit unit) {

        for (Unit existingUnit : dataStore.getUnits()) {

            if (existingUnit.getCode()
                    .equalsIgnoreCase(unit.getCode())) {

                return false;
            }
        }

        dataStore.getUnits().add(unit);

        return true;
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

    public List<Student> getALLStudents(){ return dataStore.getStudents();}

    public boolean registerStudent(Student student) {

        for (Student existingStudent : dataStore.getStudents()) {

            if (existingStudent.getStudentID()
                    .equalsIgnoreCase(student.getStudentID())) {

                return false;
            }
        }

        dataStore.getStudents().add(student);

        return true;
    }

    public boolean enrolStudent(Enrolment enrolment) {

        for (Enrolment existingEnrolment
                : dataStore.getEnrolments()) {

            boolean sameStudent =
                    existingEnrolment.getStudent()
                            .getStudentID()
                            .equalsIgnoreCase(
                                    enrolment.getStudent()
                                            .getStudentID()
                            );

            boolean sameOffering =
                    existingEnrolment.getUnitOffering()
                            .getOfferingID()
                            .equalsIgnoreCase(
                                    enrolment.getUnitOffering()
                                            .getOfferingID()
                            );

            if (sameStudent && sameOffering) {

                return false;
            }
        }

        dataStore.getEnrolments().add(enrolment);

        return true;
    }

    public List<Enrolment> getAllEnrolments() {
        return dataStore.getEnrolments();
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