package ua.nure.kn_15_6.dudar.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPanel extends JPanel implements ActionListener {
    private final MainFrame parent;
    private JPanel fieldPanel;
    private JPanel buttonPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dateOfBirthField;

    public AddPanel(MainFrame frame) {
        this.parent = frame;
        initialize();
    }

    private void initialize() {
        this.setName("addPanel");
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH);
    }

    public JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));

            addLabeledField(fieldPanel, "First name", getField("firstNameField"));
            addLabeledField(fieldPanel, "Last name", getField("lastNameField"));
            addLabeledField(fieldPanel, "Date of birth", getField("dateOfBirthField"));
        }
        return fieldPanel;
    }

    private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    public JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();

            getButtonPanel().add(getButton("okButton"));
            getButtonPanel().add(getButton("cancelButton"));
        }
        return buttonPanel;
    }

    private JButton getButton(String name) {
        JButton button = new JButton();
        button.setName(name);
        button.setSize(100, 50);
        button.addActionListener(this);
        switch (name) {
            case "okButton":
                button.setText("Add");
                button.setActionCommand("ok");
                if (okButton != null)
                    button = okButton;
                break;
            case "cancelButton":
                button.setText("Cancel");
                button.setActionCommand("cancel");
                if (cancelButton != null)
                    button = cancelButton;
                break;
        }
        return button;
    }

    private JTextField getField(String name) {
        JTextField field = new JTextField();
        field.setName(name);
        field.setSize(200, 80);
        switch (name) {
            case "firstNameField":
                if (firstNameField != null)
                    field = firstNameField;
                break;
            case "lastNameField":
                if (lastNameField != null)
                    field = lastNameField;
                break;
            case "dateOfBirthField":
                if (dateOfBirthField != null)
                    field = dateOfBirthField;
                break;
        }
        return field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ok":
                break;
            case "cancel":
                break;
        }
    }
}
