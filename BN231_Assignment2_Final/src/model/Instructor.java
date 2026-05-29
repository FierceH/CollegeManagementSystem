package model;

public class Instructor {
    private String instructorID;
    private String instructorName;

    public Instructor(String instructorID, String instructorName) {
        this.instructorID = instructorID;
        this.instructorName = instructorName;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public String getInstructorName() {
        return instructorName;
    }
}