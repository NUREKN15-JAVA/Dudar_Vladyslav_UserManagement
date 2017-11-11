package ua.nure.kn_15_6.dudar.view;

import ua.nure.kn_15_6.dudar.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

        buttonPanel.add(getButton("addButton"));
        buttonPanel.add(getButton("editButton"));
        buttonPanel.add(getButton("deleteButton"));
        buttonPanel.add(getButton("detailsButton"));
    }

    private JButton getButton(String name) {
        JButton button = new JButton(name);
        button.setName(name);
        button.setSize(100, 50);
        button.addActionListener(this);
        switch (name) {
            case "addButton":
                button.setText(Messages.getString("BrowsePanel.add"));
                button.setActionCommand("add");
                if (addButton != null)
                    button = addButton;
                break;
            case "editButton":
                button.setText(Messages.getString("BrowsePanel.edit"));
                button.setActionCommand("edit");
                if (editButton != null)
                    button = editButton;
                break;
            case "deleteButton":
                button.setText(Messages.getString("BrowsePanel.delete"));
                button.setActionCommand("delete");
                if (deleteButton != null)
                    button = deleteButton;
                break;
            case "detailsButton":
                button.setText(Messages.getString("BrowsePanel.details"));
                button.setActionCommand("details");
                if (detailsButton != null)
                    button = detailsButton;
                break;
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
        switch (e.getActionCommand()) {
            case "add":
                this.setVisible(false);
                parent.showAddPanel();
                break;
            case "edit":
                break;
            case "delete":
                break;
            case "details":
                break;
        }
    }

    public JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.setName("userTable");
            UserTableModel model = new UserTableModel(new ArrayList<>());
            userTable.setModel(model);
        }
        return userTable;
    }
}
