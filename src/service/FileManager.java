package service;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String DATA_FOLDER = "data/";

    public FileManager() {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    // -------------------------
    // SAVE METHODS
    // -------------------------

    public void saveUnits(ArrayList<Unit> units) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FOLDER + "units.txt"))) {
            for (Unit unit : units) {
                writer.println(
                        unit.getUnitCode() + "|" +
                                unit.getUnitName() + "|" +
                                unit.getCredits() + "|" +
                                unit.getDescription() + "|" +
                                String.join(";", unit.getPrerequisites())
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving units: " + e.getMessage());
        }
    }

    public void saveStudents(ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FOLDER + "students.txt"))) {
            for (Student student : students) {
                writer.println(
                        student.getStudentID() + "|" +
                                student.getStudentName() + "|" +
                                student.getEmail()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public void saveInstructors(ArrayList<Instructor> instructors) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FOLDER + "instructors.txt"))) {
            for (Instructor instructor : instructors) {
                writer.println(
                        instructor.getInstructorID() + "|" +
                                instructor.getInstructorName()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving instructors: " + e.getMessage());
        }
    }

    public void saveUnitOfferings(ArrayList<UnitOffering> unitOfferings) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FOLDER + "unitOfferings.txt"))) {
            for (UnitOffering offering : unitOfferings) {
                writer.println(
                        offering.getOfferingID() + "|" +
                                offering.getSemester() + "|" +
                                offering.getYear() + "|" +
                                offering.getCapacity() + "|" +
                                offering.getUnit().getUnitCode() + "|" +
                                offering.getInstructor().getInstructorID()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving unit offerings: " + e.getMessage());
        }
    }

    public void saveEnrolments(ArrayList<Enrolment> enrolments) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FOLDER + "enrolments.txt"))) {
            for (Enrolment enrolment : enrolments) {
                writer.println(
                        enrolment.getEnrolmentID() + "|" +
                                enrolment.getEnrolmentDate() + "|" +
                                enrolment.getStatus() + "|" +
                                enrolment.getStudent().getStudentID() + "|" +
                                enrolment.getUnitOffering().getOfferingID()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving enrolments: " + e.getMessage());
        }
    }

    // -------------------------
    // LOAD METHODS
    // -------------------------

    public ArrayList<Unit> loadUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        File file = new File(DATA_FOLDER + "units.txt");

        if (!file.exists()) {
            return units;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);

                if (parts.length >= 5) {
                    Unit unit = new Unit(
                            parts[0],
                            parts[1],
                            Integer.parseInt(parts[2]),
                            parts[3]
                    );

                    if (!parts[4].isEmpty()) {
                        String[] prerequisites = parts[4].split(";");

                        for (String prerequisite : prerequisites) {
                            unit.addPrerequisite(prerequisite);
                        }
                    }

                    units.add(unit);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading units: " + e.getMessage());
        }

        return units;
    }

    public ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        File file = new File(DATA_FOLDER + "students.txt");

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);

                if (parts.length >= 3) {
                    Student student = new Student(parts[0], parts[1], parts[2]);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }

        return students;
    }

    public ArrayList<Instructor> loadInstructors() {
        ArrayList<Instructor> instructors = new ArrayList<>();
        File file = new File(DATA_FOLDER + "instructors.txt");

        if (!file.exists()) {
            return instructors;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);

                if (parts.length >= 2) {
                    Instructor instructor = new Instructor(parts[0], parts[1]);
                    instructors.add(instructor);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading instructors: " + e.getMessage());
        }

        return instructors;
    }

    public ArrayList<UnitOffering> loadUnitOfferings(ArrayList<Unit> units, ArrayList<Instructor> instructors) {
        ArrayList<UnitOffering> unitOfferings = new ArrayList<>();
        File file = new File(DATA_FOLDER + "unitOfferings.txt");

        if (!file.exists()) {
            return unitOfferings;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);

                if (parts.length >= 6) {
                    Unit unit = findUnitByCode(units, parts[4]);
                    Instructor instructor = findInstructorByID(instructors, parts[5]);

                    if (unit != null && instructor != null) {
                        UnitOffering offering = new UnitOffering(
                                parts[0],
                                parts[1],
                                Integer.parseInt(parts[2]),
                                Integer.parseInt(parts[3]),
                                unit,
                                instructor
                        );

                        unitOfferings.add(offering);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading unit offerings: " + e.getMessage());
        }

        return unitOfferings;
    }

    public ArrayList<Enrolment> loadEnrolments(ArrayList<Student> students, ArrayList<UnitOffering> unitOfferings) {
        ArrayList<Enrolment> enrolments = new ArrayList<>();
        File file = new File(DATA_FOLDER + "enrolments.txt");

        if (!file.exists()) {
            return enrolments;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);

                if (parts.length >= 5) {
                    Student student = findStudentByID(students, parts[3]);
                    UnitOffering offering = findOfferingByID(unitOfferings, parts[4]);

                    if (student != null && offering != null) {
                        Enrolment enrolment = new Enrolment(
                                parts[0],
                                parts[1],
                                parts[2],
                                student,
                                offering
                        );

                        enrolments.add(enrolment);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading enrolments: " + e.getMessage());
        }

        return enrolments;
    }

    // -------------------------
    // HELPER SEARCH METHODS
    // -------------------------

    private Unit findUnitByCode(ArrayList<Unit> units, String unitCode) {
        for (Unit unit : units) {
            if (unit.getUnitCode().equalsIgnoreCase(unitCode)) {
                return unit;
            }
        }
        return null;
    }

    private Instructor findInstructorByID(ArrayList<Instructor> instructors, String instructorID) {
        for (Instructor instructor : instructors) {
            if (instructor.getInstructorID().equalsIgnoreCase(instructorID)) {
                return instructor;
            }
        }
        return null;
    }

    private Student findStudentByID(ArrayList<Student> students, String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }

    private UnitOffering findOfferingByID(ArrayList<UnitOffering> unitOfferings, String offeringID) {
        for (UnitOffering offering : unitOfferings) {
            if (offering.getOfferingID().equalsIgnoreCase(offeringID)) {
                return offering;
            }
        }
        return null;
    }
}