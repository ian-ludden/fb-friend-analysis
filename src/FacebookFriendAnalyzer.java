import gui.FacebookFriendAnalyzerUI;
import gui.managers.FriendManager;

import javax.swing.*;
import java.io.File;

/**
 * This class controls the Facebook friend analyzer tool.
 *
 * Created by Ian Ludden on 1/19/17.
 */
public class FacebookFriendAnalyzer {
    private static FacebookFriendAnalyzer instance = null;
    private FacebookFriendAnalyzerUI analyzerUI;
    private FriendManager friendManager;

    private FacebookFriendAnalyzer() {
        // This constructor cannot be accessed outside
    }

    public static FacebookFriendAnalyzer getInstance() {
        if (instance == null) {
            instance = new FacebookFriendAnalyzer();
        }
        return instance;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // TODO: Build the whole thing.
        FacebookFriendAnalyzer analyzer = FacebookFriendAnalyzer.getInstance();
        analyzer.analyzerUI = new FacebookFriendAnalyzerUI();

        // TODO: Move to main GUI (when it exists).
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File friendsFile = chooser.getSelectedFile();
            analyzer.friendManager = new FriendManager(friendsFile);
        }

        // TODO: Display the results in some pretty format.
    }
}
