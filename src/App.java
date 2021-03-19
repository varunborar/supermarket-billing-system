
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        
        try{

            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("App.css").toExternalForm());      //Applying Style Sheets
            mainStage.setTitle("Billing System");
            mainStage.setScene(scene);
            // mainStage.setMaximized(true);       //Opting for full screen mode 
            mainStage.setResizable(false);      //Disabling resizing of window
            mainStage.show();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
