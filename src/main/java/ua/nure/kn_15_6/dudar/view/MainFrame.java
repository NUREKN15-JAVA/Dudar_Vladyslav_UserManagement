package ua.nure.kn_15_6.dudar.view;

import ua.nure.kn_15_6.dudar.Constants;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    private JPanel contentPanel;
    private JPanel browsePanel;

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public MainFrame() throws HeadlessException {
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Constants.MAIN_FRAME_NAME);
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
        return browsePanel;
    }
}
