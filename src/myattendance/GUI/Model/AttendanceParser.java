package myattendance.GUI.Model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;
import myattendance.GUI.Controller.AttendanceCorrectionController;
import myattendance.GUI.Controller.StudentAbsenceOverviewController;
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
    public void changeView(String title, String path, User user, boolean modifyAttendance) throws IOException
    {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MyAttendance.class.getResource(path));
        AnchorPane page = (AnchorPane) loader.load();

        if (user != null)
        {
            if (!user.IsTeacher())
            {
                if (modifyAttendance)
                {
                    AttendanceCorrectionController controller = loader.<AttendanceCorrectionController>getController();
                    controller.setUser(user);
                } else
                {
                    StudentMainOverviewController controller = loader.<StudentMainOverviewController>getController();
                    controller.setUser(user);
                }

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

        if (modifyAttendance)
        {
            dialogStage.showAndWait();
        } else
        {
            dialogStage.show();
        }

    }
    
    public void changeToAbsenceOverview(String title, String path, User user)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MyAttendance.class.getResource(path));
            AnchorPane page = (AnchorPane) loader.load();
            
            StudentAbsenceOverviewController controller = loader.<StudentAbsenceOverviewController>getController();
            controller.setUser(user);
            
            Stage dialogStage = new Stage();
            dialogStage.initOwner(stage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            
            
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setTitle(title);
            dialogStage.show();
        } catch (IOException ex)
        {
            Logger.getLogger(AttendanceParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
                    changeView("Homepage", "GUI/View/StudentMainOverview.fxml", user, false);
                } else
                {
                    changeView("Homepage", "GUI/View/TeacherAttendanceOverview.fxml", user, false);
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

    public boolean establishServerConnection()
    {
        return bllFacade.establishServerConnection();
    }

    public void deleteAbsenceFromDB(User user, Day day)
    {
        bllFacade.deleteAbsenceFromDB(user, day);
    }

    public void writeAbsencesIntoDB(User user, Day day)
    {
        bllFacade.writeAbsencesIntoDB(user, day);
    }

    public List<Day> getAbsentDays(User user)
    {
        return bllFacade.getAbsentDays(user);
    }

    public boolean canAddAbsence(User user, Day clickedDay)
    {
        for (Day individualDay : getAbsentDays(user))
        {
            if (clickedDay.getDateInTime().equals(individualDay.getDateInTime()))
            {
                return false;
            }
        }
        return true;
    }

}
