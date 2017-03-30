/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.GUI.Controller;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import myattendance.BE.Course;
import myattendance.BE.User;
import myattendance.GUI.Model.AttendanceParser;
import myattendance.GUI.Model.TeacherViewModel;

/**
 * FXML Controller class
 *
 * @author Kristoffers
 */
public class TeacherAttendanceOverviewController implements Initializable
{

    /**
     * Gets the singleton instance of AttendanceParser.java.
     */
    AttendanceParser attendanceParser = AttendanceParser.getInstance();
    TeacherViewModel model = new TeacherViewModel();

    User user;
    User lastSelectedUser;

    Course lastSelectedCourse;

    String filter = "";

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    PieChart absenceChart = new PieChart(pieChartData);

    Label absenceLabel = new Label();

    private Button absenceOverviewButton;
    @FXML
    private TextField txtFldSearchStudent;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<Course> cBoxClassSelection;
    private Button btnLogOut;
    @FXML
    private VBox vBoxSelectionContent;

    @FXML
    private Label labelPresentCounter;
    @FXML
    private TableColumn<User, String> tblViewName;
    @FXML
    private TableColumn<User, String> tblViewStatus;
    @FXML
    private TableView<User> tblStatusView;
    @FXML
    private VBox vBoxStatus;
    @FXML
    private VBox vBoxMiddle;
    @FXML
    private Button btnAbsenceOverview;
    @FXML
    private Button btnLogout;
    @FXML
    private Pagination paginationBtn;
    @FXML
    private Label lblName;
    @FXML
    private MenuBar hiddenMenu;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        showConstantCalender();
        updatePresentCounter();

        absenceChart.setTitle("Student Absence");

        paginationBtn.setVisible(false);
        tblViewName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tblViewStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

    }

    public void setUser(User user)
    {
        this.user = user;
        lblName.setText(user.getName());
        fillComboBox();
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Login", "GUI/View/LoginView.fxml", null);

        // Closes the primary stage
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAbsenceOverview(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Absence Overview", "GUI/View/AttendanceCorrection.fxml", null);

        // Closes the primary stage
        Stage stage = (Stage) btnAbsenceOverview.getScene().getWindow();
        stage.close();
    }

    private void fillComboBox()
    {

        cBoxClassSelection.setItems(model.comboBoxContentGet(user.getId()));
    }

    private void showConstantCalender()
    {

        //Install JFxtra from the internet!!!
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));

        Node popupContent = datePickerSkin.getPopupContent();

        vBoxSelectionContent.setPadding(new Insets(10));

        vBoxSelectionContent.getChildren().add(popupContent);

    }

    /**
     * Updates the counter for how many students are currently present, out of
     * the total amount.
     *
     * Max present is the total number of students in the table. Currently
     * present is the amount of students with the status 'Online'.
     */
    private void updatePresentCounter()
    {
        String labelText = "";
        int maxPresent = 0;
        int currentlyPresent = 0;

        if (lastSelectedCourse != null)
        {
            maxPresent = lastSelectedCourse.getUserList().size();

            for (User u : lastSelectedCourse.getUserList())
            {
                if (u.getStatus().equals("Online"))
                {
                    currentlyPresent++;
                }
            }
        }

        labelText = "Currently Present: " + currentlyPresent + "/" + maxPresent;
        labelPresentCounter.setText(labelText);
    }

    @FXML
    private void clickCBox(ActionEvent event)
    {
        lastSelectedCourse = cBoxClassSelection.getSelectionModel().getSelectedItem();

        updateView();

        txtFldSearchStudent.clear();
        txtFldSearchStudent.requestFocus();
        
    }

    @FXML
    private void clickStatistics(ActionEvent event)
    {

        vBoxMiddle.getChildren().clear();

        if (tblStatusView.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a class and then point on a student inside the studentlist");

            alert.showAndWait();

            paginationBtn.setVisible(false);
        }

        vBoxMiddle.getChildren().add(absenceChart);
        vBoxMiddle.getChildren().add(absenceLabel);
        vBoxMiddle.setAlignment(Pos.CENTER);
        paginationBtn.setVisible(true);

    }

    @FXML
    private void clickTableStudents(MouseEvent event)
    {
        if (!tblStatusView.getItems().isEmpty())
        {
            lastSelectedUser = tblStatusView.getSelectionModel().getSelectedItem();
            chartData();

        }
    }

    private void chartData()
    {
        pieChartData.clear();
//        pieChartData.add(new PieChart.Data("Absence", lastSelectedUser.getAbsentDates()));
//        pieChartData.add(new PieChart.Data("Presence", lastSelectedUser.getPresentDates()));
//        absenceLabel.setText(lastSelectedUser.getName() + " Attendance: "
//                + lastSelectedUser.getPresentDates()
//                + "/"
//                + Math.addExact(lastSelectedUser.getAbsentDates(), lastSelectedUser.getPresentDates()));
    }

    private void updateView()
    {
        tblStatusView.setItems(model.updateList(filter, lastSelectedCourse));
        updatePresentCounter();

    }

    @FXML
    private void AnchorPaneActionEvent(MouseEvent event)
    {
        model.getClassList();
    }

    @FXML
    private void searchFunction(ActionEvent event)
    {
        if (!cBoxClassSelection.getSelectionModel().isEmpty())
        {
            filter = txtFldSearchStudent.getText();
            updateView();
        }

    }

    @FXML
    private void handleRefreshStudents(MouseEvent event)
    {
        if (!cBoxClassSelection.getSelectionModel().isEmpty())
        {
            filter = txtFldSearchStudent.getText();
            updateView();
            updatePresentCounter();
        }
    }

    public void automaticUpdate()
    {
        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                updateView();
            }
        }, 0, 5000);
    }
}
