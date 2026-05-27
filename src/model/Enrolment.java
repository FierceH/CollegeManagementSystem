package model;

public class Enrolment {
    private String enrolmentID;
    private String enrolmentDate;
    private String status;
    private Student student;
    private UnitOffering unitOffering;

    public Enrolment(String enrolmentID, String enrolmentDate, String status, Student student, UnitOffering unitOffering) {
        this.enrolmentID = enrolmentID;
        this.enrolmentDate = enrolmentDate;
        this.status = status;
        this.student = student;
        this.unitOffering = unitOffering;
    }

    public String getEnrolmentID() {
        return enrolmentID;
    }

    public Student getStudent() {
        return student;
    }

    public String getStatus() {
        return status;
    }

    public UnitOffering getUnitOffering() {
        return unitOffering;
    }

    public String getEnrolmentDate() {
        return enrolmentDate;
    }
}