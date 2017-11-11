package ua.nure.kn_15_6.dudar.view;

import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
                else addButton = button;
                break;
            case "editButton":
                button.setText(Messages.getString("BrowsePanel.edit"));
                button.setActionCommand("edit");
                if (editButton != null)
                    button = editButton;
                else editButton = button;
                break;
            case "deleteButton":
                button.setText(Messages.getString("BrowsePanel.delete"));
                button.setActionCommand("delete");
                if (deleteButton != null)
                    button = deleteButton;
                else deleteButton = button;
                break;
            case "detailsButton":
                button.setText(Messages.getString("BrowsePanel.details"));
                button.setActionCommand("details");
                if (detailsButton != null)
                    button = detailsButton;
                else deleteButton = button;
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
                if (getUserTable().getSelectedRow() != -1) {
                    this.setVisible(false);
                    parent.showEditPanel(getSelectedUser());
                }
                break;
            case "delete":
                break;
            case "details":
                break;
        }
    }

    private User getSelectedUser() {
        int row = getUserTable().getSelectedRow();
        return ((UserTableModel) getUserTable().getModel()).getUserAt(row);
    }

    public JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.setName("userTable");
        }
        return userTable;
    }

    public void initTable() {
        UserTableModel model = null;
        try {
            model = new UserTableModel(new ArrayList<>(parent.getDao().findAll()));
        } catch (SQLException e) {
            model = new UserTableModel(new ArrayList<>());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        userTable.setModel(model);
    }
}
