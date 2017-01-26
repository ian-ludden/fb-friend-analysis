package gui.managers;

import gui.Friend;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class manages the lists of friends.
 *
 * Created by Ian Ludden on 1/20/17.
 */
public class FriendManager {
    private Set<Friend> currentFriends;
    private Set<Friend> deniedFriendRequests;
    private Set<Friend> sentFriendRequests;
    private Set<Friend> removedFriends;

    public FriendManager(File friendsHtmlFile) {
        if (friendsHtmlFile == null) {
            return;
        }

        try {
            Document friendsDoc = Jsoup.parse(friendsHtmlFile, "UTF-8", "");
            this.currentFriends = new TreeSet<>();
            this.deniedFriendRequests = new TreeSet<>();
            this.sentFriendRequests = new TreeSet<>();
            this.removedFriends = new TreeSet<>();

            Element mainDiv = friendsDoc.select("div.contents div").get(0);
            for (int i = 0; i * 2 + 1 < mainDiv.children().size(); i++) {
                Element header = mainDiv.children().get(i * 2);
                Element list = mainDiv.children().get(i * 2 + 1);
                Elements friendItems = list.children();
                switch (header.text()) {
                    case "Friends":
                        for (Element friendItem : friendItems) {
                            this.currentFriends.add(
                                    new Friend(friendItem.text()));
                        }
                        break;
                    case "Sent Friend Requests":
                        for (Element friendItem : friendItems) {
                            this.sentFriendRequests.add(
                                    new Friend(friendItem.text()));
                        }
                        break;
                    case "Removed Friends":
                        for (Element friendItem : friendItems) {
                            this.removedFriends.add(
                                    new Friend(friendItem.text()));
                        }
                        break;
                    case "Deleted Friend Requests":
                        for (Element friendItem : friendItems) {
                            this.deniedFriendRequests.add(
                                    new Friend(friendItem.text()));
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Set<Friend> getCurrentFriends() {
        return currentFriends;
    }

    public Set<Friend> getDeniedFriendRequests() {
        return deniedFriendRequests;
    }

    public Set<Friend> getSentFriendRequests() {
        return sentFriendRequests;
    }

    public Set<Friend> getRemovedFriends() {
        return removedFriends;
    }
}
