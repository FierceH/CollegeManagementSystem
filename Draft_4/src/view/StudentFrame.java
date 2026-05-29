package view;

import model.Student;
import service.CMSService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StudentFrame extends JFrame {

    private final CMSService cmsService;

    private JTextField studentIdField;
    private JTextField fullNameField;
    private JTextField emailField;

    private JTextArea displayArea;

    public StudentFrame(CMSService cmsService) {

        this.cmsService = cmsService;

        setTitle("Student Management");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialiseUI();

        setVisible(true);
    }

    private void initialiseUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());

        // =========================
        // TOP INPUT PANEL
        // =========================

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        inputPanel.setBorder(
                BorderFactory.createTitledBorder("Add Student")
        );

        JLabel idLabel = new JLabel("Student ID:");
        JLabel nameLabel = new JLabel("Full Name:");
        JLabel emailLabel = new JLabel("Email:");

        studentIdField = new JTextField();
        fullNameField = new JTextField();
        emailField = new JTextField();

        JButton addButton = new JButton("Add Student");

        addButton.addActionListener(e -> addStudent());

        inputPanel.add(idLabel);
        inputPanel.add(studentIdField);

        inputPanel.add(nameLabel);
        inputPanel.add(fullNameField);

        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        // =========================
        // DISPLAY PANEL
        // =========================

        displayArea = new JTextArea();

        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Student Records")
        );

        // =========================
        // REFRESH BUTTON
        // =========================

        JButton refreshButton = new JButton("Refresh Students");

        refreshButton.addActionListener(e -> refreshStudentDisplay());

        // =========================
        // MAIN LAYOUT
        // =========================

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(refreshButton, BorderLayout.SOUTH);

        add(mainPanel);

        refreshStudentDisplay();
    }

    private void addStudent() {

        try {

            String studentId = studentIdField.getText().trim();
            String fullName = fullNameField.getText().trim();
            String email = emailField.getText().trim();

            Student student = new Student(
                    studentId,
                    fullName,
                    email
            );

            boolean added =
                    cmsService.registerStudent(student);

            if (added) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student added successfully."
                );

                clearFields();

                refreshStudentDisplay();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student already exists.",
                        "Duplicate Student",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void refreshStudentDisplay() {

        displayArea.setText("");

        List<Student> students =
                cmsService.getALLStudents();

        if (students.isEmpty()) {

            displayArea.append("No students found.\n");

            return;
        }

        for (Student student : students) {

            displayArea.append(
                    "ID: " + student.getStudentId()
                            + "\nName: " + student.getFullName()
                            + "\nEmail: " + student.getEmail()
                            + "\n--------------------------\n"
            );
        }
    }

    private void clearFields() {

        studentIdField.setText("");
        fullNameField.setText("");
        emailField.setText("");
    }
}