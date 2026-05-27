package model;

public class UnitOffering {
    private String offeringID;
    private String semester;
    private int year;
    private int capacity;
    private Unit unit;
    private Instructor instructor;

    public UnitOffering(String offeringID, String semester, int year, int capacity, Unit unit, Instructor instructor) {
        this.offeringID = offeringID;
        this.semester = semester;
        this.year = year;
        this.capacity = capacity;
        this.unit = unit;
        this.instructor = instructor;
    }

    public String getOfferingID() {
        return offeringID;
    }

    public String getSemester() {
        return semester;
    }

    public int getYear() {
        return year;
    }

    public int getCapacity() {
        return capacity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void assignInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return instructor;
    }
}