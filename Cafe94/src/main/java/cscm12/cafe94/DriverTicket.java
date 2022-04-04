package cscm12.cafe94;


/** Container class for driver tickets.
 * @author Paul Norman
 * @version 1.0
 */
public class DriverTicket {

    private int orderID;
    private String main;
    private String side;
    private String drink;
    private String deliveryAddress;
    private String driverName;

    public DriverTicket(int orderID, String main, String side,
                        String drink, String deliveryAddress, String driverName) {
        this.orderID = orderID;
        this.main = main;
        this.side = side;
        this.drink = drink;
        this.deliveryAddress = deliveryAddress;
        this.driverName = driverName;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
