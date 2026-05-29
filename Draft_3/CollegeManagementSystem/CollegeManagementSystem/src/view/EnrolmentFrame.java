package view;

import model.Enrolment;
import model.Student;
import model.UnitOffering;
import service.CMSService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EnrolmentFrame extends JFrame {

    private final CMSService cmsService;

    private JComboBox<Student> studentComboBox;
    private JComboBox<UnitOffering> offeringComboBox;

    private JTextField enrolmentIdField;
    private JTextField enrolmentDateField;
    private JTextField statusField;

    private JTextArea displayArea;

    public EnrolmentFrame(CMSService cmsService) {

        this.cmsService = cmsService;

        setTitle("Enrolment Management");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialiseUI();

        setVisible(true);
    }

    private void initialiseUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());

        // =========================
        // INPUT PANEL
        // =========================

        JPanel inputPanel = new JPanel(
                new GridLayout(5, 2, 10, 10)
        );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Create Enrolment"
                )
        );

        JLabel enrolmentIdLabel =
                new JLabel("Enrolment ID:");

        JLabel enrolmentDateLabel =
                new JLabel("Enrolment Date:");

        JLabel statusLabel =
                new JLabel("Status:");

        JLabel studentLabel =
                new JLabel("Student:");

        JLabel offeringLabel =
                new JLabel("Unit Offering:");

        enrolmentIdField = new JTextField();

        enrolmentDateField = new JTextField();

        statusField = new JTextField();

        studentComboBox = new JComboBox<>();

        offeringComboBox = new JComboBox<>();

        loadStudents();

        loadOfferings();

        JButton enrolButton =
                new JButton("Create Enrolment");

        enrolButton.addActionListener(
                e -> createEnrolment()
        );

        inputPanel.add(enrolmentIdLabel);
        inputPanel.add(enrolmentIdField);

        inputPanel.add(enrolmentDateLabel);
        inputPanel.add(enrolmentDateField);

        inputPanel.add(statusLabel);
        inputPanel.add(statusField);

        inputPanel.add(studentLabel);
        inputPanel.add(studentComboBox);

        inputPanel.add(offeringLabel);
        inputPanel.add(offeringComboBox);

        // =========================
        // DISPLAY AREA
        // =========================

        displayArea = new JTextArea();

        displayArea.setEditable(false);

        JScrollPane scrollPane =
                new JScrollPane(displayArea);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Enrolments"
                )
        );

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel = new JPanel();

        JButton refreshButton =
                new JButton("Refresh Enrolments");

        refreshButton.addActionListener(
                e -> refreshDisplay()
        );

        buttonPanel.add(enrolButton);
        buttonPanel.add(refreshButton);

        // =========================
        // MAIN LAYOUT
        // =========================

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        refreshDisplay();
    }

    private void loadStudents() {

        studentComboBox.removeAllItems();

        List<Student> students =
                cmsService.getALLStudents();

        for (Student student : students) {

            studentComboBox.addItem(student);
        }
    }

    private void loadOfferings() {

        offeringComboBox.removeAllItems();

        List<UnitOffering> offerings =
                cmsService.viewUnitOfferings();

        for (UnitOffering offering : offerings) {

            offeringComboBox.addItem(offering);
        }
    }

    private void createEnrolment() {

        try {

            String enrolmentID =
                    enrolmentIdField.getText().trim();

            String enrolmentDate =
                    enrolmentDateField.getText().trim();

            String status =
                    statusField.getText().trim();

            Student selectedStudent =
                    (Student) studentComboBox.getSelectedItem();

            UnitOffering selectedOffering =
                    (UnitOffering) offeringComboBox.getSelectedItem();

            if (selectedStudent == null ||
                    selectedOffering == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student and offering must be selected.",
                        "Selection Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Enrolment enrolment = new Enrolment(
                    enrolmentID,
                    enrolmentDate,
                    status,
                    selectedStudent,
                    selectedOffering
            );

            cmsService.enrolStudent(enrolment);

            JOptionPane.showMessageDialog(
                    this,
                    "Enrolment created successfully."
            );

            clearFields();

            refreshDisplay();

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void refreshDisplay() {

        displayArea.setText("");

        List<Enrolment> enrolments =
                cmsService.getAllEnrolments();

        if (cmsService.filterEnrolmentsByStudent("")
                .isEmpty()) {

            displayArea.append("No enrolments found.\n");

            return;
        }

        for (Enrolment enrolment : enrolments ) {

            displayArea.append(
                    "Enrolment ID: "
                            + enrolment.getEnrolmentID()

                            + "\nStudent: "
                            + enrolment.getStudent()
                            .getStudentName()

                            + "\nOffering: "
                            + enrolment.getUnitOffering()
                            .getOfferingID()

                            + "\nStatus: "
                            + enrolment.getStatus()

                            + "\n---------------------------\n"
            );
        }
    }

    private void clearFields() {

        enrolmentIdField.setText("");
        enrolmentDateField.setText("");
        statusField.setText("");
    }
}