package view;

import service.CMSService;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {

    private final CMSService cmsService;

    public MainMenuFrame(CMSService cmsService) {
        this.cmsService = cmsService;

        setTitle("College Management System");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialiseUI();

        setVisible(true);
    }

    private void initialiseUI() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel titleLabel = new JLabel(
                "College Management System",
                SwingConstants.CENTER
        );

        JButton studentButton = new JButton("Manage Students");
        JButton unitButton = new JButton("Manage Units");
        JButton enrolmentButton = new JButton("Manage Enrolments");
        JButton reportButton = new JButton("View Reports");

        studentButton.addActionListener(e ->
                new StudentFrame(cmsService));

        unitButton.addActionListener(e ->
                new UnitFrame(cmsService));

        enrolmentButton.addActionListener(e ->
                new EnrolmentFrame(cmsService));

        reportButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Reports feature coming soon."
                ));

        panel.add(titleLabel);
        panel.add(studentButton);
        panel.add(unitButton);
        panel.add(enrolmentButton);
        panel.add(reportButton);

        add(panel);
    }
}