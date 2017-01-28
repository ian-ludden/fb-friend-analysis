package util;

/**
 * This class enumerates the four categories of friends in the Facebook data.
 *
 * Created by Ian Ludden on 1/28/17.
 */
public enum FriendCategory {
    CURRENT_FRIENDS ("Friends"),
    SENT_FRIEND_REQUESTS ("Sent Friend Requests"),
    REMOVED_FRIENDS ("Removed Friends"),
    DELETED_FRIEND_REQUESTS ("Deleted Friend Requests");

    private final String categoryName;

    FriendCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }
}
