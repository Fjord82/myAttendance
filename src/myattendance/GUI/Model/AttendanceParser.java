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
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;
import myattendance.GUI.Controller.StudentMainOverviewController;
import myattendance.GUI.Controller.TeacherAttendanceOverviewController;
import myattendance.MyAttendance;

public class AttendanceParser
{

    BLLFacade bllFacade = BLLFacade.getInstance();

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
    public void changeView(String title, String path, User user) throws IOException
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyAttendance.class.getResource(path));
        AnchorPane page = (AnchorPane) loader.load();

        if (user != null)
        {
            if (!user.IsTeacher())
            {
                StudentMainOverviewController controller = loader.<StudentMainOverviewController>getController();
                controller.setUser(user);

            } else
            {
                TeacherAttendanceOverviewController controller = loader.<TeacherAttendanceOverviewController>getController();
                controller.setUser(user);
            }
        }

        Stage dialogStage = new Stage();
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setTitle(title);

        dialogStage.show();
    }

    public void tryLogin(String login, String pass, Stage stage)
    {
        User user = bllFacade.getUser(login, pass);

        if (user != null)
        {
            try
            {
                if (!user.IsTeacher())
                {
                    changeView("Homepage", "GUI/View/StudentMainOverview.fxml", user);
                } else
                {
                    changeView("Homepage", "GUI/View/TeacherAttendanceOverview.fxml", user);
                }
                stage.close();
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
