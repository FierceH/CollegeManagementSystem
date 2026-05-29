package view;

import model.Unit;
import service.CMSService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UnitFrame extends JFrame {

    private final CMSService cmsService;

    private JTextField codeField;
    private JTextField nameField;
    private JTextField creditsField;
    private JTextArea descriptionArea;

    private JTextArea displayArea;

    public UnitFrame(CMSService cmsService) {

        this.cmsService = cmsService;

        setTitle("Unit Management");
        setSize(700, 550);
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
                BorderFactory.createTitledBorder("Add Unit")
        );

        JLabel codeLabel = new JLabel("Unit Code:");
        JLabel nameLabel = new JLabel("Unit Name:");
        JLabel creditsLabel = new JLabel("Credits:");
        JLabel descriptionLabel = new JLabel("Description:");

        codeField = new JTextField();
        nameField = new JTextField();
        creditsField = new JTextField();

        descriptionArea = new JTextArea(3, 20);

        JScrollPane descriptionScroll =
                new JScrollPane(descriptionArea);

        JButton addButton = new JButton("Add Unit");

        addButton.addActionListener(e -> addUnit());

        inputPanel.add(codeLabel);
        inputPanel.add(codeField);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        inputPanel.add(creditsLabel);
        inputPanel.add(creditsField);

        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionScroll);

        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        // =========================
        // DISPLAY AREA
        // =========================

        displayArea = new JTextArea();

        displayArea.setEditable(false);

        JScrollPane displayScroll =
                new JScrollPane(displayArea);

        displayScroll.setBorder(
                BorderFactory.createTitledBorder("Units")
        );

        // =========================
        // REFRESH BUTTON
        // =========================

        JButton refreshButton =
                new JButton("Refresh Units");

        refreshButton.addActionListener(
                e -> refreshUnitDisplay()
        );

        // =========================
        // MAIN LAYOUT
        // =========================

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(displayScroll, BorderLayout.CENTER);
        mainPanel.add(refreshButton, BorderLayout.SOUTH);

        add(mainPanel);

        refreshUnitDisplay();
    }

    private void addUnit() {

        try {

            String code = codeField.getText().trim();
            String name = nameField.getText().trim();

            int credits = Integer.parseInt(
                    creditsField.getText().trim()
            );

            String description =
                    descriptionArea.getText().trim();

            Unit unit = new Unit(
                    code,
                    name,
                    credits,
                    description
            );

           boolean added = cmsService.addUnit(unit);

            if (added) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unit added successfully."
                );

                clearFields();

                refreshUnitDisplay();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unit code already exists.",
                        "Duplicate Unit",
                        JOptionPane.WARNING_MESSAGE
                );
            }
            clearFields();

            refreshUnitDisplay();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Credits must be a number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void refreshUnitDisplay() {

        displayArea.setText("");

        List<Unit> units = cmsService.viewUnits();

        if (units.isEmpty()) {

            displayArea.append("No units found.\n");

            return;
        }

        for (Unit unit : units) {

            displayArea.append(
                    "Code: " + unit.getCode()
                            + "\nName: " + unit.getName()
                            + "\nCredits: " + unit.getCredits()
                            + "\nDescription: "
                            + unit.getDescription()
                            + "\n-------------------------\n"
            );
        }
    }

    private void clearFields() {

        codeField.setText("");
        nameField.setText("");
        creditsField.setText("");
        descriptionArea.setText("");
    }
}