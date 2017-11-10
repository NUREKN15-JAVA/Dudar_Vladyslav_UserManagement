package ua.nure.kn_15_6.dudar.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowsePanel extends JPanel implements ActionListener {
    private MainFrame parent;
    private JScrollPane tablePanel;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton detailsButton;
    private JTable userTable;

    public BrowsePanel(MainFrame frame) {
        this.parent = frame;
        initialize();
    }

    private void initialize() {
        this.setName("browsePanel");
        this.setLayout(new BorderLayout());
        this.add(getTablePanel(), BorderLayout.CENTER);
        this.add(getButtonPanel(), BorderLayout.SOUTH);

        buttonPanel.add(getButton("Add"));
        buttonPanel.add(getButton("Edit"));
        buttonPanel.add(getButton("Delete"));
        buttonPanel.add(getButton("Details"));
    }

    private JButton getButton(String text) {
        JButton button = new JButton(text);
        button.setSize(100,50);
        button.addActionListener(this);
        switch (text) {
            case "Add":
                button.setName("addButton");
                if (addButton != null)
                    button = addButton;
                break;
            case "Edit":
                button.setName("editButton");
                if (editButton != null)
                    button = editButton;
                break;
            case "Delete":
                button.setName("deleteButton");
                if (deleteButton != null)
                    button = deleteButton;
                break;
            case "Details":
                button.setName("detailsButton");
                if (detailsButton != null)
                    button = detailsButton;
                break;
            default:
                button.setName(text.toLowerCase().replaceAll(" ", "") + "Button");
        }
        return button;
    }

    public JScrollPane getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    public JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
        }
        return buttonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            switch (((JButton)e.getSource()).getName()) {
                case "addButton":
                    break;
                case "editButton":
                    break;
                case "deleteButton":
                    break;
                case "detailsButton":
                    break;
            }
        }
    }

    public JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.setName("userTable");
        }
        return userTable;
    }
}
