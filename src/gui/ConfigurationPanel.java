package gui;

import util.FriendCategory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * This class specifies the panel of settings for the chart.
 * Created by Ian Ludden on 1/28/17.
 */
public class ConfigurationPanel extends JPanel {
    public static final String CHOOSE_FILE_COMMAND = "CHOOSE_FILE";
    public static final String BUILD_CHART_COMMAND = "BUILD_CHART";
    JTextField filePath;

    public ConfigurationPanel(FacebookFriendAnalyzerUI friendAnalyzerUI) {
        super();

        Border lineBorder = BorderFactory
                .createLineBorder(Color.BLACK, 2, true);
        TitledBorder configBorder = BorderFactory
                .createTitledBorder(lineBorder, "Configuration Options");
        this.setBorder(configBorder);

        filePath = new JTextField(30);
        JButton browseButton = new JButton("Browse...");
        browseButton.setActionCommand(CHOOSE_FILE_COMMAND);
        browseButton.addActionListener(friendAnalyzerUI);

        this.add(filePath);
        this.add(browseButton);

        JComboBox<FriendCategory> categoryComboBox = new JComboBox<>();
        categoryComboBox.setModel(
                new DefaultComboBoxModel<>(FriendCategory.values()));
        categoryComboBox.addItemListener(friendAnalyzerUI);
        this.add(categoryComboBox);

        JButton displayChartButton = new JButton("Build Chart");
        displayChartButton.setActionCommand(BUILD_CHART_COMMAND);
        displayChartButton.addActionListener(friendAnalyzerUI);
        this.add(displayChartButton);

        // TODO (future): Have a slider for customizing the width of the histogram bins
        // TODO (future): Allow for multiple files to be loaded
        // TODO (future): Add other random settings that come to mind
    }

    void setFilePathText(String newFilePathText) {
        this.filePath.setText(newFilePathText);
    }
}
