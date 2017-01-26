package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * This class provides the view for the Facebook Friend Analyzer.
 *
 * Created by Ian Ludden on 1/20/17.
 */
public class FacebookFriendAnalyzerUI extends JFrame implements ActionListener {
    private JPanel chartPanel;
    private JPanel configPanel;

    public FacebookFriendAnalyzerUI() {
        try {
            this.setIconImage(ImageIO.read(new File("img/MainLogo.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // TODO: Make the control/settings/config panel its own class?
        this.configPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(this.configPanel, BoxLayout.X_AXIS);
        this.configPanel.setLayout(boxLayout);

        Border lineBorder = BorderFactory
                .createLineBorder(Color.BLACK, 2, true);
        TitledBorder configBorder = BorderFactory
                .createTitledBorder(lineBorder, "Configuration Options");

        this.configPanel.setBorder(configBorder);

        // TODO: Make a file chooser (text field with browse button)
        // TODO: Make a combo box to choose which category of friends to view (only enabled if file has been loaded)
        // TODO: Make a "Display Chart" button
        // TODO (future): Have a slider for customizing the width of the histogram bins
        // TODO (future): Allow for multiple files to be loaded
        // TODO (future): Add other random settings that come to mind

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
        // TODO
    }
}
