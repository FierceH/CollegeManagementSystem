package service;

import java.util.ArrayList;
import model.Enrolment;

public class ReportService {

    public String generateStudentEnrolmentReport(ArrayList<Enrolment> enrolments) {
        StringBuilder report = new StringBuilder();

        report.append("Student Enrolment Report\n");
        report.append("--------------------------------\n");

        for (Enrolment enrolment : enrolments) {
            report.append("Offering ID: ")
                    .append(enrolment.getUnitOffering().getOfferingID())
                    .append(" | Unit: ")
                    .append(enrolment.getUnitOffering().getUnit().getUnitName())
                    .append(" | Student: ")
                    .append(enrolment.getStudent().getStudentName())
                    .append(" | Status: ")
                    .append(enrolment.getStatus())
                    .append("\n");
        }

        return report.toString();
    }

    public String generateClassAllocationReport(ArrayList<Enrolment> enrolments) {
        StringBuilder report = new StringBuilder();

        report.append("Class Allocation Report\n");
        report.append("--------------------------------\n");

        for (Enrolment enrolment : enrolments) {
            report.append("Offering ID: ")
                    .append(enrolment.getUnitOffering().getOfferingID())
                    .append(" | Unit: ")
                    .append(enrolment.getUnitOffering().getUnit().getUnitName())
                    .append(" | Instructor: ")
                    .append(enrolment.getUnitOffering().getInstructor().getInstructorName())
                    .append(" | Student: ")
                    .append(enrolment.getStudent().getStudentName())
                    .append("\n");
        }

        return report.toString();
    }
}