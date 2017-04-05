package myattendance.GUI.Controller;

import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;
import myattendance.GUI.Model.AttendanceParser;
import myattendance.GUI.Model.DateParser;
import myattendance.GUI.Model.StudentViewModel;
import org.joda.time.DateTime;

public class StudentMainOverviewController implements Initializable
{

    @FXML
    private VBox vBoxSelectionContent;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button btnLogout;

    @FXML
    private VBox vBoxMiddle;

    AttendanceParser attendanceParser = AttendanceParser.getInstance();
    DateParser dateParser = DateParser.getInstance();
    StudentViewModel model = new StudentViewModel();

    User user = new User();

    Day today;

    @FXML
    private Label lblStudentName;
    @FXML
    private Label lblStudentClass;

    public boolean present = false;
    @FXML
    private Pagination paginationBtn;

    private PieChart absenceChart = new PieChart();

    private CategoryAxis xAxisStacked = new CategoryAxis();
    private NumberAxis yAxisStacked = new NumberAxis();
    private StackedBarChart<String, Number> stackedChart = new StackedBarChart<>(xAxisStacked, yAxisStacked);

    private CategoryAxis xAxisLine = new CategoryAxis();
    private NumberAxis yAxisLine = new NumberAxis();
    private LineChart<String, Number> lineChart = new LineChart<>(xAxisLine, yAxisLine);

    boolean pie = false;
    boolean stacked = false;
    boolean line = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)

    {
        showConstantCalender();
    }

    public void attendenceChecks()
    {
        today = dateParser.getDay(new DateTime());

        dateParser.recordAbsence(user, today);

    }

    private void updateView()
    {
        lblStudentName.setText(user.getName());
        lblStudentClass.setText(user.getsClass());

        Label absenceLabel = new Label("Student Attendance: ");
        absenceLabel.setText("Student Absence: " + user.getAbsentDays().size() + "/" + model.getDaysUptoToday().size());
        vBoxMiddle.getChildren().add(absenceLabel);
        vBoxMiddle.setAlignment(Pos.CENTER);
    }

    public void updateStatistics()
    {

        if (paginationBtn.getCurrentPageIndex() == 0 && pie == false)
        {
            clearStatistics();
            pie = true;
            stacked = false;
            line = false;
            absenceChart.getData().clear();

            absenceChart.setData(model.getPieChartData(user));
            absenceChart.setTitle("Absence");

            vBoxMiddle.getChildren().add(absenceChart);

        } else if (paginationBtn.getCurrentPageIndex() == 1 && stacked == false)
        {
            clearStatistics();
            pie = false;
            stacked = true;
            line = false;

            stackedChart.getData().clear();

            vBoxMiddle.getChildren().add(stackedChart);
            stackedChart.getData().add(model.getStackedChartData(user));
            
            xAxisStacked.setLabel("Day");
            xAxisStacked.setTickMarkVisible(false);
            yAxisStacked.setLabel("Recorded Absences");
            yAxisStacked.setTickUnit(1);
            yAxisStacked.setTickMarkVisible(false);
            stackedChart.setTitle("Absence per day");

        } else if (paginationBtn.getCurrentPageIndex() == 2 && line == false)
        {
            clearStatistics();
            pie = false;
            stacked = false;
            line = true;

            lineChart.getData().clear();

            vBoxMiddle.getChildren().add(lineChart);
            lineChart.getData().add(model.getLineChartData(user));
            
            xAxisLine.setLabel("Month");
            xAxisLine.setTickMarkVisible(false);
            yAxisLine.setLabel("Absent Days");
            yAxisLine.setTickUnit(1);
            yAxisLine.setTickMarkVisible(false);
        }
    }

    public void clearStatistics()
    {
        vBoxMiddle.getChildren().remove(stackedChart);
        vBoxMiddle.getChildren().remove(absenceChart);
        vBoxMiddle.getChildren().remove(lineChart);
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException
    {
        attendanceParser.changeView("Login", "GUI/View/LoginView.fxml", null, false);

        // Closes the primary stage
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlePaginationClick(MouseEvent event)
    {
        updateStatistics();
    }

    private void showConstantCalender()
    {

        //Install JFxtra from the internet!!!
        DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));

        Node popupContent = datePickerSkin.getPopupContent();

        vBoxSelectionContent.setPadding(new Insets(5));

        vBoxSelectionContent.getChildren().add(popupContent);

    }

    public void setUser(User user)
    {
        this.user = user;
        attendenceChecks();
        user.setAbsentDays(model.getAbsentDays(user));
        updateView();
        updateStatistics();
    }

}
