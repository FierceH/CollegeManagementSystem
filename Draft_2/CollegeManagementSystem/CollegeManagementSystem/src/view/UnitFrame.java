package view;

import service.CMSService;

import javax.swing.*;

public class UnitFrame extends JFrame {

    public UnitFrame(CMSService cmsService) {

        setTitle("Student Management");
        setSize(400, 300);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}