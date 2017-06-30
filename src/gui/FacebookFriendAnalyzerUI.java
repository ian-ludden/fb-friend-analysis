package gui;

import gui.managers.FriendManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.IntervalXYDataset;
import util.FriendCategory;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 * This class provides the view for the Facebook Friend Analyzer.
 *
 * Created by Ian Ludden on 1/20/17.
 */
public class FacebookFriendAnalyzerUI extends JFrame implements ActionListener,
        ItemListener {
    private FacebookFriendAnalyzer analyzer;
    private JPanel chartPanel;
    private ConfigurationPanel configPanel;

    public FacebookFriendAnalyzerUI(FacebookFriendAnalyzer analyzer) {
        this.analyzer = analyzer;

        try {
            this.setIconImage(ImageIO.read(new File("images/MainLogo.png")));
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
        if (e.getActionCommand().equals(ConfigurationPanel.CHOOSE_FILE_COMMAND)) {
            JFileChooser fileChooser = new JFileChooser();
            int returnVal = fileChooser.showOpenDialog(null);

            System.out.println(returnVal);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File friendsFile = fileChooser.getSelectedFile();
                analyzer.setFriendManager(new FriendManager(friendsFile));
                this.configPanel.setFilePathText(friendsFile.getAbsolutePath());
            }
        } else if (e.getActionCommand().equals(ConfigurationPanel.BUILD_CHART_COMMAND)) {
            // TODO
            IntervalXYDataset dataset = analyzer.getTimeSeriesCollection();
            System.out.println("Creating histogram...");
            JFreeChart chart = ChartFactory.createHistogram(
                    "Histogram of Friendship Beginnings",  "Week", "Count",
                    dataset, PlotOrientation.VERTICAL, false, true, false);

            System.out.println("Changing domain axis...");
            XYPlot plot = (XYPlot) chart.getPlot();
            DateAxis dateAxis = new DateAxis();
            dateAxis.setDateFormatOverride(new SimpleDateFormat("MM-dd-yyyy"));
            plot.setDomainAxis(dateAxis);

            System.out.println("Changing tooltips...");
            StandardXYToolTipGenerator generator = new StandardXYToolTipGenerator(
                    "{0}: ({1}, {2})",
                    new SimpleDateFormat("MM-dd-yyyy"),
                    NumberFormat.getInstance()
            );

            plot.getRenderer().setToolTipGenerator(generator);

            ChartPanel innerChartPanel = new ChartPanel(chart);
            this.chartPanel.removeAll();
            this.chartPanel.add(innerChartPanel, BorderLayout.CENTER);
            this.chartPanel.revalidate();
        } else {
            // Do nothing
            System.out.printf("Unrecognized action command:%n\t%s%n",
                    e.getActionCommand());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.analyzer.setFriendCategory((FriendCategory)(e.getItem()));
        }
    }
}
