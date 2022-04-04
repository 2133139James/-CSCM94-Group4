package cscm12.cafe94;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * Class for managing customer sit-in bookings.
 * @author Paul Norman
 * @version 1.1
 */
public class Booking {

    private int bookingID;
    private int custID;
    private int numberOfGuests;
    private int tableID;
    private LocalDateTime bookingDate;

    public Booking(int bookingID, int custID, int numberOfGuests, int tableID, String bookingDate) {
        this.bookingID = bookingID;
        this.custID = custID;
        this.numberOfGuests = numberOfGuests;
        this.tableID = tableID;
        this.bookingDate = LocalDateTime.parse(bookingDate);
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getCustID() {
        return this.custID;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getTableID() {
        return tableID;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    // Setters

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * Adds values in Booking object to database.
     */
    public void uploadBooking() {
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.newEntry("Bookings", "CustomerUserID='" + custID +
                            "', numberOfGuests='" + numberOfGuests +
                            "', tableID='" + tableID +
                            "', bookingDate='" + bookingDate + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e) {
            System.out.println("A field is empty.");
        }
    }

    /**
     * Edits the database booking entry with the same bookingID, using values in Booking object.
     */
    public void editBooking() {
        DatabaseHandler handler = new DatabaseHandler();
        try {
            handler.editEntry("Bookings", "bookingID",
                    "bookingID='" + bookingID,
                    "', CustomerUserID='" + custID +
                            "', numberOfGuests='" + numberOfGuests +
                            "', tableID='" + tableID +
                            "', bookingDate='" + bookingDate + "'",
                    "Database Error. Entries may be in incorrect format.");
        } catch (NullPointerException e) {
            System.out.println("A field is empty.");
        }
    }

    /**
     * Deletes booking which matches the object.
     */
    public void deleteBooking() {
        DatabaseHandler handler = new DatabaseHandler();
        String id = String.valueOf(bookingID);
        try {
            handler.deleteEntry("Bookings", "bookingID", id,
                    "May not exist.");
        } catch (Exception e) {
            System.out.println();
        }
    }

    public void approveBooking(){

    }

    /**
     * Checks bookings for a given tableID with a range of one hour before and one hour after. If there
     * are one or more bookings in that timeslot it will return false.
     * @return True if timeslot is free, false otherwise.
     */
    public boolean checkTimeslot() {
        LocalDateTime min = bookingDate.plusHours(-1);
        LocalDateTime max = bookingDate.plusHours(1);
        DatabaseHandler handler = new DatabaseHandler();
        Connection connect = handler.database();
        try {
            String query = "SELECT BookingTime from Booking WHERE TableID = " + tableID +
                    " AND > " + min + " AND < " + max;
            PreparedStatement checkDatabase = connect.prepareStatement(query);
            ResultSet resultSet = checkDatabase.executeQuery();
            if (resultSet.next()) {
                return false;
            }
            return true;
        } catch (NullPointerException n) {
            System.out.println("Missing input.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
