package cscm12.cafe94;

public class KitchenTicket {

    private int referenceNumber;
    private String orderType;
    private String main;
    private String side;
    private String drink;
    private boolean isCooked;

    public KitchenTicket(int referenceNumber, String orderType, String main, String side, String drink,
                         boolean isCooked){
        this.referenceNumber =referenceNumber;
        this.orderType = orderType;
        this.main = main;
        this.side= side;
        this.drink = drink;
        this.isCooked = isCooked;
    }

    public int getReferenceNumber() {
        return referenceNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getMain() {
        return main;
    }

    public String getSide(){
        return side;
    }

    public String getDrink(){
        return drink;
    }

    public boolean getIsCooked() {
        return isCooked;
    }

    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public boolean isCooked() {
        return isCooked;
    }
}
