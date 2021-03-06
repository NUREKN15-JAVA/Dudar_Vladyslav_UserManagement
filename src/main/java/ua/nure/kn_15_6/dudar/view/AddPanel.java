package ua.nure.kn_15_6.dudar.view;

import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddPanel extends JPanel implements ActionListener {
    private static final Color BG_COLOR = Color.WHITE;
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

            addLabeledField(fieldPanel, Message.getString("AddPanel.first_name"), getField("firstNameField"));
            addLabeledField(fieldPanel, Message.getString("AddPanel.last_name"), getField("lastNameField"));
            addLabeledField(fieldPanel, Message.getString("AddPanel.date_of_birth"), getField("dateOfBirthField"));
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
                button.setText(Message.getString("AddPanel.ok"));
                button.setActionCommand("ok");
                if (okButton != null)
                    button = okButton;
                else okButton = button;
                break;
            case "cancelButton":
                button.setText(Message.getString("AddPanel.cancel"));
                button.setActionCommand("cancel");
                if (cancelButton != null)
                    button = cancelButton;
                else cancelButton = button;
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
                    return firstNameField;
                else firstNameField = field;
                break;
            case "lastNameField":
                if (lastNameField != null)
                    return lastNameField;
                else lastNameField = field;
                break;
            case "dateOfBirthField":
                if (dateOfBirthField != null)
                    return dateOfBirthField;
                else dateOfBirthField = field;
                break;
        }
        return field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "ok":
                User user = new User();
                user.setFirstName(getField("firstNameField").getText());
                user.setLastName(getField("lastNameField").getText());
                try {
                    String date = getField("dateOfBirthField").getText();
                    user.setBirthDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy")));
                    parent.getDao().create(user);
                    clearFields();

                    this.setVisible(false);
                    parent.showBrowsePanel();
                } catch (DateTimeParseException e1) {
                    getField("dateOfBirthField").setBackground(Color.RED);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "cancel":
                this.setVisible(false);
                clearFields();
                parent.showBrowsePanel();
                break;
        }
    }

    private void clearFields() {
        firstNameField.setText("");
        firstNameField.setBackground(BG_COLOR);
        lastNameField.setText("");
        lastNameField.setBackground(BG_COLOR);
        dateOfBirthField.setText("");
        dateOfBirthField.setBackground(BG_COLOR);
    }
}
