package assets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class registerFormController {
    @FXML private AnchorPane form;

    // Form Fields

    @FXML private TextField companyName;
    @FXML private TextField GSTIn;
    @FXML private TextField authorizedSignatory;
    @FXML private TextField phoneNumber;
    @FXML private TextField email;
    @FXML private TextField line1;
    @FXML private TextField line2;
    @FXML private TextField city;
    @FXML private TextField state;
    @FXML private TextField zip;

    public void cancel(ActionEvent event)
    {
        Stage currentFormWindow = (Stage) form.getScene().getWindow();
        currentFormWindow.close();
    }
    public void register(ActionEvent event)
    {
        try{

           
        }catch(Exception e)
        {
            e.getMessage();
        }
        finally
        {
            Stage currentFormWindow = (Stage) form.getScene().getWindow();
            currentFormWindow.close();
        }
    }
}