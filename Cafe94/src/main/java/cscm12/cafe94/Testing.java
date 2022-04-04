package cscm12.cafe94;

import java.time.LocalDateTime;

public class Testing {


    public static void main(String[] args){

        KitchenHandler handler = new KitchenHandler();
        handler.changeSpecial(3);

        System.out.println(handler.getTickets());


        DateTimeHelper helper = new DateTimeHelper();
        LocalDateTime date =  DateTimeHelper.convert(2023, 04 ,14, 12, 40 );
        Booking booking = new Booking( 1, 1, 3,
                8, date);

        System.out.println(booking.checkTimeslot());




    }


}
