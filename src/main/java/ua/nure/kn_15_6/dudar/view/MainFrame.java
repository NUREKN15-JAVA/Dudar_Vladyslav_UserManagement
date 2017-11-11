package ua.nure.kn_15_6.dudar.view;

import ua.nure.kn_15_6.dudar.Constants;
import ua.nure.kn_15_6.dudar.User;
import ua.nure.kn_15_6.dudar.db.DaoFactory;
import ua.nure.kn_15_6.dudar.db.UserDao;
import ua.nure.kn_15_6.dudar.util.Messages;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainFrame extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 400;
    private final UserDao dao;

    private JPanel contentPanel;
    private JPanel browsePanel;
    private AddPanel addPanel;
    private EditPanel editPanel;

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public MainFrame() throws HeadlessException {
        super();
        dao = DaoFactory.getInstance().getUserDao();
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Messages.getString("user_management"));
        this.setContentPane(getContentPanel());

        contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
    }

    public JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
        }
        return contentPanel;
    }

    public JPanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel) browsePanel).initTable();
        return browsePanel;
    }

    public JPanel getEditPanel() {
        if (editPanel == null) {
            editPanel = new EditPanel(this);
        }
        return editPanel;
    }

    public void showAddPanel() {
        showPanel(getAddPanel());
    }

    public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }

    public void showEditPanel(User user) {
        ((EditPanel) getEditPanel()).setUser(user);
        showPanel(getEditPanel());
    }

    private void showPanel(JPanel panel) {
        if (!Arrays.asList(getContentPanel().getComponents()).contains(panel)) {
            getContentPanel().add(panel, BorderLayout.CENTER);
        }
        getContentPanel().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }

    public AddPanel getAddPanel() {
        if (addPanel == null) {
            addPanel = new AddPanel(this);
        }
        return addPanel;
    }

    public UserDao getDao() {
        return dao;
    }

    public void showDeleteDialog(User selectedUser) {
        JFrame deleteFrame = new JFrame();
        deleteFrame.setName("deleteFrame");
        if (JOptionPane.showConfirmDialog(deleteFrame,
                Messages.getString("BrowsePanel.delete_q") + " " +
                        selectedUser.getFirstName() + " " + selectedUser.getLastName() + "?",
                Messages.getString("BrowsePanel.delete_title"),
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            try {
                getDao().delete(selectedUser);
                getBrowsePanel();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
