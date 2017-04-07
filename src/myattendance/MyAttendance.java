package myattendance;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MyAttendance extends Application
{

    public Window stage;

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/View/LoginView.fxml"));

        Scene scene = new Scene(root);

        //scene.getStylesheets().add("myattendance/GUI/View/styleSheetLogin.css");
        //scene.getStylesheets().add("file:/Users/Kristoffers/Desktop/School/Projects/myAttendance/styleSheetLogin.css");
        primaryStage.setTitle("Login");

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
