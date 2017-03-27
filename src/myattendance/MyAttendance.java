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
/*
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(final KeyEvent keyEvent)
            {
                if (keyEvent.getCode() == KeyCode.R)
                {
                    
                    AnchorPane group = new AnchorPane();
                    
                    Scene scene = new Scene(group, 200, 200);
                    
                    
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Theme setting");
                    VBox vb = new VBox();
                    
                    
                    HBox hb = new HBox();
                    hb.setPadding(new Insets(20, 10, 20, 10));
                    
                    ComboBox themeBox = new ComboBox();
                            ObservableList<String> comboItems
                            = FXCollections.observableArrayList(" ", "Facebook", "Christmas", "Future");
                            themeBox.setItems(comboItems);
                            themeBox.getSelectionModel().selectFirst();
                            
                    Button btnBack =new Button();
                    btnBack.setText("Back");
                    
                    
                    hb.getChildren().addAll(themeBox, btnBack);
                    vb.getChildren().add(hb);
                    group.getChildren().addAll(vb);
                    
                    if(themeBox.getValue().equals("Facebook"))
                    {
                        scene.getStylesheets().add("myattendance/GUI/View/styleSheetLogin.css");
                    }else if(themeBox.getValue().equals("Christmas"))
                    {
                        scene.getStylesheets().add("myattendance/GUI/View/ThemeSheetEasv.css");
                    }else if(themeBox.getValue().equals("Future"))
                    {
                        scene.getStylesheets().add("myattendance/GUI/View/ThemeSheetEasv_1.css");
                    }

                    System.out.println("R pressed");
                    //Stop letting it do anything else
                    //keyEvent.consume();
                }
            }
        });*/
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
