package assets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class addItemFormController {
    
    @FXML private AnchorPane form;

    public void cancel(ActionEvent event)
    {
        Stage currentFormWindow = (Stage) form.getScene().getWindow();
        currentFormWindow.close();
    }

    public void add(ActionEvent event)
    {
        
    }
}
