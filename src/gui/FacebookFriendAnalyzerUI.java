package gui;

import gui.managers.FriendManager;
import util.FriendCategory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

/**
 * This class provides the view for the Facebook Friend Analyzer.
 *
 * Created by Ian Ludden on 1/20/17.
 */
public class FacebookFriendAnalyzerUI extends JFrame implements ActionListener,
        ItemListener {
    private FacebookFriendAnalyzer analyzer;
    private JPanel chartPanel;
    private JPanel configPanel;

    public FacebookFriendAnalyzerUI(FacebookFriendAnalyzer analyzer) {
        this.analyzer = analyzer;

        try {
            this.setIconImage(ImageIO.read(new File("img/MainLogo.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.configPanel = new ConfigurationPanel(this);
        this.chartPanel = new JPanel(new BorderLayout());


        // TODO: Put panels in scroll panes first?
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                this.configPanel, this.chartPanel);

        Dimension minimumSize = new Dimension(200, 100);
        this.configPanel.setMinimumSize(minimumSize);
        this.chartPanel.setMinimumSize(minimumSize);

        splitPane.setPreferredSize(new Dimension(1600, 900));
        splitPane.setDividerSize(5);

        this.getContentPane().add(splitPane);

        this.setTitle("Facebook Friend Analysis");
        this.setMinimumSize(new Dimension(500, 300));
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("CHOOSE_FILE")) {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File friendsFile = fileChooser.getSelectedFile();
                analyzer.setFriendManager(new FriendManager(friendsFile));
            }
        } else if (e.getActionCommand().equals("BUILD_CHART")) {
            // TODO
        } else {
            // Do nothing
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.analyzer.setFriendCategory((FriendCategory)(e.getItem()));
        }
    }
}
