package cscm12.cafe94;

public class Testing {


    public static void main(String[] args){

        KitchenHandler handler = new KitchenHandler();
        handler.changeSpecial(3);

        System.out.println(handler.getTickets());

    }


}
