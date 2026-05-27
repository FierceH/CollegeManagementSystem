package model;

public class Student {
    private String studentID;
    private String studentName;
    private String email;

    public Student(String studentID, String studentName, String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }

        this.studentID = studentID;
        this.studentName = studentName;
        this.email = email;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getStudentId() {
        return studentID;
    }

    public String getFullName() {
        return studentName;
    }

    public void setFullName(String fullName) {
        this.studentName = fullName;
    }
}