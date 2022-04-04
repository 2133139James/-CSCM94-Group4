package cscm12.cafe94;


import javafx.beans.property.SimpleStringProperty;

/**
 * Superclass for tickets.
 */
public class Ticket {


    private SimpleStringProperty main;
    private SimpleStringProperty side;
    private SimpleStringProperty drink;

    public Ticket(String main, String side, String drink) {
        this.main = new SimpleStringProperty(main);
        this.side = new SimpleStringProperty(side);
        this.drink = new SimpleStringProperty(drink);
    }

    public SimpleStringProperty getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = new SimpleStringProperty(main);
    }

    public SimpleStringProperty getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = new SimpleStringProperty(side);
    }

    public SimpleStringProperty getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = new SimpleStringProperty(drink);
    }
}
