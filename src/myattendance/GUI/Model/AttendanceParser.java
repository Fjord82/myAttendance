package myattendance.GUI.Model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import myattendance.BE.Student;
import myattendance.BLL.LoginCheckManager;
import myattendance.GUI.Controller.StudentMainOverviewController;
import myattendance.MyAttendance;

public class AttendanceParser
{

    LoginCheckManager loginCheckManager = new LoginCheckManager();

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
    public void changeView(String title, String path, Student student) throws IOException
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyAttendance.class.getResource(path));

        if (student != null)
        {
            StudentMainOverviewController controller = loader.getController();
            controller.setStudent(student);
        }

        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setTitle(title);

        dialogStage.show();
    }

    public void getStudent(String login, String pass, Scene scene)
    {
        Student student = loginCheckManager.getStudent(login, pass);

        if (student != null)
        {
            try
            {
                changeView("Homepage", "GUI/View/StudentMainOverview.fxml", student);
            } catch (IOException ex)
            {
                Logger.getLogger(AttendanceParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong Login");
            alert.setContentText("Wrong username or password. Try again.");

            alert.showAndWait();
        }

    }

}
