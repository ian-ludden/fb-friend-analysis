package gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represents a Facebook Friend.
 *
 * Created by Ian Ludden on 1/20/17.
 */
public class Friend implements Comparable<Friend> {
    private String name;
    private Date startDate;

    /**
     * This Consctructor creates a Friend object from its HTML String
     * representation.
     * @param fullDescription - The name and date of the friend, e.g.,
     *                        "John Doe (Mar 3, 2003)"
     */
    public Friend(String fullDescription) {
        String[] sections = fullDescription.trim().split("\\(");

        String nameData = sections[0].trim();
        int emailStartIndex = nameData.indexOf('[');
        if (emailStartIndex != -1) {
            nameData = nameData.substring(0, emailStartIndex).trim();
        }
        this.name = nameData;

        String dateData = sections[1];
        dateData = dateData.substring(0, dateData.length() - 1);
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        try {
            this.startDate = df.parse(dateData);
        } catch (ParseException ex) {
            // If first parse failed, perhaps year is omitted
            dateData += ", " + Year.now();
            try {
                this.startDate = df.parse(dateData);
            } catch (ParseException ex1) {
                System.err.printf("Unable to parse date: %s%n", dateData);
            }
        }
    }


    /**
     * This Constructor creates a Friend object with the given parameters.
     * @param name - full name of the friend
     * @param startDate - date the friend request was accepted/sent,
     *                  or the date the friend was removed
     */
    public Friend(String name, Date startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public int compareTo(Friend o) {
        if (this.name.equals(o.getName())) {
            return this.startDate.compareTo(o.getStartDate());
        }
        return this.name.compareTo(o.getName());
    }
}
