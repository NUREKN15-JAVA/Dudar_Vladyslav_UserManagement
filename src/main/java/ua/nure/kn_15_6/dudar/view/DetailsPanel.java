package ua.nure.kn_15_6.dudar.view;

import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ua.nure.kn_15_6.dudar.Constants.formatter;

public class DetailsPanel extends JPanel implements ActionListener {
    private final MainFrame parent;
    private JPanel fieldPanel;
    private JPanel buttonPanel;
    private JButton cancelButton;
    private JLabel idLabel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel dateOfBirthLabel;
    private User user;

    public DetailsPanel(MainFrame frame) {
        this.parent = frame;
        initialize();
    }

    private void initialize() {
        this.setName("detailsPanel");
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH);
    }

    public JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(4, 2));

            addLablePair(fieldPanel, Messages.getString("DetailsPanel.id"), getLabel("idLabel"));
            addLablePair(fieldPanel, Messages.getString("DetailsPanel.first_name"), getLabel("firstNameLabel"));
            addLablePair(fieldPanel, Messages.getString("DetailsPanel.last_name"), getLabel("lastNameLabel"));
            addLablePair(fieldPanel, Messages.getString("DetailsPanel.date_of_birth"), getLabel("dateOfBirthLabel"));
        }
        return fieldPanel;
    }

    private void addLablePair(JPanel panel, String labelText, JLabel textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    public JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();

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
            case "cancelButton":
                button.setText(Messages.getString("DetailsPanel.cancel"));
                button.setActionCommand("cancel");
                if (cancelButton != null)
                    button = cancelButton;
                else cancelButton = button;
                break;
        }
        return button;
    }

    private JLabel getLabel(String name) {
        JLabel label = new JLabel();
        label.setName(name);
        label.setSize(100, 80);
        switch (name) {
            case "idLabel":
                if (idLabel != null)
                    return idLabel;
                else idLabel = label;
                break;
            case "firstNameLabel":
                if (firstNameLabel != null)
                    return firstNameLabel;
                else firstNameLabel = label;
                break;
            case "lastNameLabel":
                if (lastNameLabel != null)
                    return lastNameLabel;
                else lastNameLabel = label;
                break;
            case "dateOfBirthLabel":
                if (dateOfBirthLabel != null)
                    return dateOfBirthLabel;
                else dateOfBirthLabel = label;
                break;
        }
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "cancel":
                this.setVisible(false);
                parent.showBrowsePanel();
                break;
        }
    }

    public void setUser(User user) {
        this.user = user.copy();

        getLabel("idLabel").setText(String.valueOf(user.getId()));
        getLabel("firstNameLabel").setText(user.getFirstName());
        getLabel("lastNameLabel").setText(user.getLastName());
        getLabel("dateOfBirthLabel").setText(formatter.format(user.getBirthDate()));
    }
}
