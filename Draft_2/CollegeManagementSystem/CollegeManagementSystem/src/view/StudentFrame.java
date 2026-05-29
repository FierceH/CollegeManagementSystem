package view;

import service.CMSService;

import javax.swing.*;

public class StudentFrame extends JFrame {

    public StudentFrame(CMSService cmsService) {

        setTitle("Student Management");
        setSize(400, 300);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}