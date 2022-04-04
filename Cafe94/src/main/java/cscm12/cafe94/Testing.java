package cscm12.cafe94;

import javafx.collections.ObservableList;

import java.sql.Array;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Testing {


    public static void main(String[] args){

        KitchenHandler handler = new KitchenHandler();
        handler.changeSpecial(3);

        ObservableList<KitchenTicket> results = handler.getTickets();

        System.out.println(results.size());


        DateTimeHelper helper = new DateTimeHelper();
        LocalDateTime date =  DateTimeHelper.convert(2023, 04 ,14, 12, 40 );
        Booking booking = new Booking( 10, 1, 3,
                8, date);

        System.out.println(booking.checkTimeslot());

        booking.approveBooking();

        System.out.println(booking.checkTimeslot());

        booking.book();





    }


}
