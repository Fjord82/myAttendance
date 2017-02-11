/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.GUI.Model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import myattendance.MyAttendance;

/**
 *
 * @author Fjord82
 */
public class AttendanceParser
{

    private static AttendanceParser instance;

    public static AttendanceParser getInstance()
    {

        if (instance == null)
        {
            instance = new AttendanceParser();
        }
        return instance;
    }

    private AttendanceParser()
    {

    }

    public Window stage;

    /**
     *
     * @param title
     * @param path
     * @throws IOException
     */
    public void changeView(String title, String path) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyAttendance.class.getResource(path));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setTitle(title);

        dialogStage.show();
    }

}
