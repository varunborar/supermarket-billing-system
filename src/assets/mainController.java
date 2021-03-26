package assets;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;
import java.awt.Desktop;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import main.Item;
import main.ItemDatabaseHandler;
import main.Company;
import main.CompanyDatabaseHandler;
import main.Customer;
import main.Invoice;
import main.InvoiceGenerator;
import main.InvoiceItem;

public class mainController {

    // FOR INVOICE
    Invoice invoice;
    InvoiceGenerator invoiceGenerator;
    Company comp;
    Customer cust;

    // FXML FIELDS

    @FXML
    private Pane itemPane;
    @FXML
    private TextField itemQuantity;
    @FXML
    private ChoiceBox<String> itemChoice;
    @FXML
    private ChoiceBox<String> paymentMode;
    @FXML
    private TextField customerName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField invoiceNumber;



    @FXML
    private Label dispSubtotal;
    @FXML
    private Label dispDiscount;
    @FXML
    private Label dispTotal;
    @FXML 
    private TextField discountAmount;

    @FXML
    private TableView<InvoiceItem> invoiceTable;
    
    @FXML
    private TableColumn<InvoiceItem, String> S_NO;
    @FXML
    private TableColumn<InvoiceItem, String> ITEM_CODE;
    @FXML
    private TableColumn<InvoiceItem, String> ITEM_NAME;
    @FXML
    private TableColumn<InvoiceItem, String> QUANTITY;
    @FXML
    private TableColumn<InvoiceItem, String> PRICE;


    // Constructor

    public mainController(){
        invoice = new Invoice();
        CompanyDatabaseHandler compDB = new CompanyDatabaseHandler();
        comp = compDB.getCompanyDB();
        cust = new Customer();
        invoiceGenerator = new InvoiceGenerator("C:/users/varun/invoice.pdf");
    }
    
    // On loading

    @FXML
    public void initialize() {
        try{
            ItemDatabaseHandler itemDB = new ItemDatabaseHandler();
            Vector<Item> itemList= itemDB.getAllItemDB();

            itemChoice.getItems().removeAll(itemChoice.getItems());
            for(Item i: itemList)
            {
                itemChoice.getItems().add(i.getItemname());
            }

            paymentMode.getItems().removeAll(paymentMode.getItems());
            paymentMode.getItems().add("Cash");
            paymentMode.getItems().add("Credit Card");
            paymentMode.getItems().add("Debit Card");
            paymentMode.getItems().add("UPI");



            S_NO.setCellValueFactory(new PropertyValueFactory<>("itemSerialNumber"));
            ITEM_CODE.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
            ITEM_NAME.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
            QUANTITY.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            PRICE.setCellValueFactory(new PropertyValueFactory<>("Price"));

        }catch(Exception e)
        {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    
    }


    // File Menu
    public void clear(ActionEvent event) {

    }

    public void quit(ActionEvent event) {
        System.exit(0);
    }

    // Inventory
    public void addItemForm(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addItemForm.fxml"));
            Stage secondaryStage = new Stage();
            Scene scene = new Scene(root);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Company Details");
            secondaryStage.setResizable(false);
            secondaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerForm(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registerForm.fxml"));
            Stage secondaryStage = new Stage();
            Scene scene = new Scene(root);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Add Item");
            secondaryStage.setResizable(false);
            secondaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // About
    public void credits(ActionEvent event) {

    }

    public void github(ActionEvent event) {
        try {

            Desktop browser = Desktop.getDesktop();
            browser.browse(new URI("https://github.com/varunborar/supermarket-billing-system"));
        } catch (IOException | URISyntaxException e) {
            e.getMessage();
        }
    }

    // Item Pane

    public void itemAdd(ActionEvent event)
    {
        try{
        ItemDatabaseHandler itemDB = new ItemDatabaseHandler();
        String selectedItem = itemChoice.getValue();
        Item temp = itemDB.getItemDB(selectedItem);

        Integer Quantity = Integer.parseInt(itemQuantity.getText());

        System.out.println(selectedItem + " : " + Quantity);
        InvoiceItem obj = invoice.addItem(temp, Quantity);
        refresh();

        // Adding to tableview

        invoiceTable.getItems().add(obj);



        }catch(Exception e)
        {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        
    }

    public void refresh()
    {
        initialize();
        subTotal();
    }

    public void clearAll()
    {
        invoice.clearAll();
    }

    public void subTotal()
    {
        dispSubtotal.setText(invoice.subTotal() + "");
    }

    public void total()
    {
        invoice.setDisocunt(Float.parseFloat(discountAmount.getText()));
        dispDiscount.setText(invoice.discount() + "");
        dispTotal.setText(invoice.total() + "");
    }

    public void printBill()
    {
        cust.setName(customerName.getText());
        cust.setpaymod(paymentMode.getValue());
        cust.setContact(phoneNumber.getText());
        cust.setmail(email.getText());


        invoiceGenerator.setCompany(comp);
        invoiceGenerator.setCustomer(cust);
        invoiceGenerator.setInvoiceNumber(invoiceNumber.getText());
        invoiceGenerator.setUpFile();

        Vector<InvoiceItem> itemList = invoice.retItemList();

        for (InvoiceItem e : itemList)
        {
            invoiceGenerator.addItem(e, e.getQuantity(), e.getItemSerialNumber());
        }
        
        
        invoiceGenerator.printItemTable();
        invoiceGenerator.setFooter(invoice.subTotal() + "", invoice.discount() + "", invoice.total() + "");
    }
}
