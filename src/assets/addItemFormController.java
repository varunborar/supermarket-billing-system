package assets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import main.Item;
import main.ItemDatabaseHandler;

public class addItemFormController {

    @FXML
    private AnchorPane form;

    @FXML
    private TextField itemID;
    @FXML
    private TextField itemName;
    @FXML
    private TextField itemPrice;

    private String ItemID;
    private String ItemName;
    private String ItemPrice;

    public void cancel(ActionEvent event) {
        Stage currentFormWindow = (Stage) form.getScene().getWindow();
        currentFormWindow.close();
    }

    public void add(ActionEvent event) {
        ItemID = itemID.getText();
        ItemName = itemName.getText();
        ItemPrice = itemPrice.getText();

        // System.out.println("Item ID: " + ItemID + "Item Name: " + ItemName + "Item
        // Price: " + ItemPrice);

        Item item = new Item();
        item.setItemcode(ItemID);
        item.setItemname(ItemName);
        item.setItemprice(ItemPrice);

        // Adding item to data base

        ItemDatabaseHandler ItemDB = new ItemDatabaseHandler();

        ItemDB.addItemDB(item);

        Stage currentFormWindow = (Stage) form.getScene().getWindow();
        currentFormWindow.close();
    }
}
