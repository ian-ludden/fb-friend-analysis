package gui;

import gui.managers.FriendManager;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import util.FriendCategory;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class controls the Facebook friend analyzer tool.
 *
 * Created by Ian Ludden on 1/19/17.
 */
public class FacebookFriendAnalyzer {
    private static FacebookFriendAnalyzer instance = null;
    private FacebookFriendAnalyzerUI analyzerUI;
    private FriendCategory friendCategory = FriendCategory.CURRENT_FRIENDS;

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

        System.out.println("Getting dates...");
        Date[] dates = getDatesFromFriends();
        if (dates == null) {
            return null;
        }

        System.out.println("Building weekCounts map...");
        // Build map of weeks to dates
        // TODO: Allow for bins of other sizes, e.g. day or month
        Map<Week, Integer> weekCounts = new HashMap<>();
        for (Date date : dates) {
            Week newWeek = new Week(date);
            int newValue = weekCounts.containsKey(newWeek)
                    ? 1 + weekCounts.get(newWeek)
                    : 1;
            weekCounts.put(newWeek, newValue);
        }

        System.out.println("Building time series...");
        // Add week counts to a TimeSeries
        TimeSeries ts = new TimeSeries("Week Series");
        for (Week week : weekCounts.keySet()) {
            ts.addOrUpdate(week, weekCounts.get(week));
        }

        System.out.println("Returning time series...");
        return new TimeSeriesCollection(ts);
    }

    private Date[] getDatesFromFriends() {
        Set<Friend> friendSet;
        switch(this.friendCategory) {
            case CURRENT_FRIENDS:
                friendSet = friendManager.getCurrentFriends();
                break;
            case REMOVED_FRIENDS:
                friendSet = friendManager.getRemovedFriends();
                break;
            case SENT_FRIEND_REQUESTS:
                friendSet = friendManager.getSentFriendRequests();
                break;
            case DELETED_FRIEND_REQUESTS:
                friendSet = friendManager.getDeniedFriendRequests();
                break;
            default:
                System.out.println("Invalid category.");
                return null;
        }

        Date[] dates = new Date[friendSet.size()];

        int index = 0;
        for (Friend friend : friendSet) {
            dates[index++] = friend.getStartDate();
        }

        return dates;
    }
}
