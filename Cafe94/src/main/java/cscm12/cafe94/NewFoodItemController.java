package cscm12.cafe94;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Controls the create new food item page.
 * @author Paul Norman
 * @version 1.0
 */
public class NewFoodItemController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button createButton;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField time;
    @FXML
    private CheckBox veg;
    @FXML
    private TextField nGuests;
    @FXML
    private TextField day;


    public void createItem(ActionEvent event) {
        FoodItem food = new FoodItem(
                0,
                name.getText(),
                Float.valueOf(price.getText()),
                Integer.valueOf(time.getText()),
                veg.isSelected(),
                false);
        food.uploadItem();
    }
    /**   [switchToChef]
     Switches to chefs page.
     @param event triggers button to go to the fxml upon clicking. */
    @FXML
    public void switchToChef(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Chef.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
