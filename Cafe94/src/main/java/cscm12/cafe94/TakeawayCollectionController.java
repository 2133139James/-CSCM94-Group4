package cscm12.cafe94;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static cscm12.cafe94.StaffLoginController.staffSession;

public class TakeawayCollectionController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button collectButton;
    @FXML
    private TableView<WaiterTicket> takeawayTickets;
    @FXML
    private TableColumn<WaiterTicket, Number> ID;
    @FXML
    private TableColumn<WaiterTicket, String> main;
    @FXML
    private TableColumn<WaiterTicket, String> side;
    @FXML
    private TableColumn<WaiterTicket, String> drink;

    /**
     * Casts current takeaways to be collected to readable list.
     */
    public void getTicketTable() {
        // factory design pattern
        ///
        WaiterHandler handler = new WaiterHandler();
        ObservableList<WaiterTicket> tickets = handler.getCollections();
        try {
            ID.setCellValueFactory(cellData -> cellData.getValue().getTableNumber());
            main.setCellValueFactory(cellData -> cellData.getValue().getMain());
            side.setCellValueFactory(cellData -> cellData.getValue().getDrink());
            drink.setCellValueFactory(cellData -> cellData.getValue().getSide());
            takeawayTickets.setItems(tickets);
        } catch (NullPointerException n) {
            System.out.println(" ");
        }
    }

    /** Sets takeaway order to IsCollected.
     * @param event Button press.
     */
    public void deliver(ActionEvent event){
        int index = takeawayTickets.getSelectionModel().getSelectedIndex();
        WaiterTicket ticket = takeawayTickets.getItems().get(index);
        ticket.collect();
        getTicketTable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTicketTable();
    }
}
