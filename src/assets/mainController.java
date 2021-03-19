package assets;

import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainController {

    // File
    public void clear(ActionEvent event)
    {

    }
    public void quit(ActionEvent event)
    {
        System.exit(0);
    }

    // Inventory
    public void addItemForm(ActionEvent event)
    {
        try{
            
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addItemForm.fxml"));
            Stage secondaryStage = new Stage();
            Scene scene = new Scene(root);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Company Details");
            secondaryStage.setResizable(false);
            secondaryStage.show();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void registerForm(ActionEvent event)
    {
        try{
            
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registerForm.fxml"));
            Stage secondaryStage = new Stage();
            Scene scene = new Scene(root);
            secondaryStage.setScene(scene);
            secondaryStage.setTitle("Add Item");
            secondaryStage.setResizable(false);
            secondaryStage.show();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // About 
    public void credits(ActionEvent event)
    {

    }
    public void github(ActionEvent event)
    {
        try{

        
        Desktop browser = Desktop.getDesktop();
        browser.browse(new URI("https://github.com/varunborar/supermarket-billing-system"));
        }
        catch(IOException|URISyntaxException e)
        {
            e.getMessage();
        }
    }


}
