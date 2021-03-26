package assets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import main.Company;
import main.CompanyDatabaseHandler;

public class registerFormController {
    @FXML
    private AnchorPane form;

    // Form Fields

    @FXML
    private TextField companyName;
    @FXML
    private TextField GSTIn;
    @FXML
    private TextField authorizedSignatory;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private TextField line1;
    @FXML
    private TextField line2;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    @FXML
    private TextField zip;

    public void cancel(ActionEvent event) {
        Stage currentFormWindow = (Stage) form.getScene().getWindow();
        currentFormWindow.close();
    }

    public void register(ActionEvent event) {
        try {

            Company obj = new Company();
            obj.setCompanyName(this.companyName.getText());
            obj.setGSTNumber(this.GSTIn.getText());
            obj.setPhoneNumber(this.phoneNumber.getText());
            obj.setAddress(this.line1.getText(), this.line2.getText());
            obj.setLocation(this.city.getText(), this.state.getText(), this.zip.getText());
            obj.setEmail(this.email.getText());
            obj.setAuthorizedSignatory(this.authorizedSignatory.getText());

            // Adding Company to database

            CompanyDatabaseHandler compDB = new CompanyDatabaseHandler();

            compDB.addCompanyDB(obj);

            // String Name = obj.getCompanyName();
            // String Address = obj.getAddressLine1() + "\n" + obj.getAddressLine2() + "\n" + obj.getCity() + ", "
            //         + obj.getState() + "-" + obj.getZip();
            // String Invoice = "GST In: " + obj.getGSTNumber();
            // String Contact = "Phone no. : " + obj.getPhoneNumber() + "\nEmail: " + obj.getEmail();

            // System.out.println(Name + "\n" + Address + "\n" + Invoice + "\n" + Contact);s

        } catch (Exception e) {
            e.getMessage();
        } finally {
            Stage currentFormWindow = (Stage) form.getScene().getWindow();
            currentFormWindow.close();
        }
    }
}