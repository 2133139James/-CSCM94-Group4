package cscm12.cafe94;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the TableBooking.fxml page.
 * @author Paul Norman
 * @version 1.0
 */
public class BookingController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button bookButton;
    @FXML
    private TextField table;
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField nGuests;
    @FXML
    private TextField day;
    @FXML
    private TextField month;
    @FXML
    private TextField year;
    @FXML
    private TextField hour;
    @FXML
    private TextField minute;
    @FXML
    private TextField cancelField;
    @FXML
    private Label messageText;

    /**
     * Checks database for overlapping bookings, makes booking if there isn't one. Informs user
     */
    public void book(){
        DateTimeHelper helper = new DateTimeHelper();
        Booking newBooking = new Booking(0, 6,Integer.parseInt(nGuests.getText()),
                Integer.parseInt(table.getText()),
                helper.convert(Integer.parseInt(year.getText()),
                        Integer.parseInt(month.getText()),
                        Integer.parseInt(day.getText()),
                        Integer.parseInt(hour.getText()),
                        Integer.parseInt(minute.getText()))
                );
        if (newBooking.checkTimeslot()){
            newBooking.uploadBooking();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Timeslot already booked! Please choose another table.");
            alert.show();
        }
    }


    /**
     * Cancels booking with matching id.
     */
    public void cancel(){
        DateTimeHelper helper = new DateTimeHelper();
        Booking newBooking = new Booking(Integer.parseInt(cancelField.getText()), 0,0,
                Integer.parseInt(table.getText()),
                helper.convert(0,0,0,0,0));
        Booking.deleteBooking();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
