package view;

import service.CMSService;

import javax.swing.*;

public class EnrolmentFrame extends JFrame {

    public EnrolmentFrame(CMSService cmsService) {

        setTitle("Student Management");
        setSize(400, 300);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}