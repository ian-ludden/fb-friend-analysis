package gui;

import gui.managers.FriendManager;
import org.jfree.data.time.TimeSeriesCollection;
import util.FriendCategory;

import javax.swing.*;

/**
 * This class controls the Facebook friend analyzer tool.
 *
 * Created by Ian Ludden on 1/19/17.
 */
public class FacebookFriendAnalyzer {
    private static FacebookFriendAnalyzer instance = null;
    private FacebookFriendAnalyzerUI analyzerUI;
    private FriendCategory friendCategory;

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
        analyzer.analyzerUI = new FacebookFriendAnalyzerUI(analyzer);



        // TODO: Display the results in some pretty format.
    }

    public void setFriendManager(FriendManager friendManager) {
        this.friendManager = friendManager;
    }

    public FriendManager getFriendManager() {
        return friendManager;
    }

    public void setFriendCategory(FriendCategory newCategory) {
        this.friendCategory = newCategory;
    }

    public TimeSeriesCollection getTimeSeriesCollection() {
        // TODO: Add parameter for bin width (?)
        return null;
    }
}
