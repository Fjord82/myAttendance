package myattendance.GUI.Controller;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import myattendance.BE.Course;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.GUI.Model.AttendanceParser;
import myattendance.GUI.Model.DateParser;
import myattendance.GUI.Model.TeacherViewModel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TeacherAttendanceOverviewController implements Initializable

{

    /**
     * Gets the singleton instance of AttendanceParser.java.
     */
    AttendanceParser attendanceParser = AttendanceParser.getInstance();
    TeacherViewModel model = new TeacherViewModel();
    DateParser dateParser = DateParser.getInstance();

    User teacher;
    User lastSelectedStudent;
    Day clickedDay;


    Course lastSelectedCourse;

    String filter = "";

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

    Label absenceLabel = new Label();

    DatePicker calendar;

    private Button absenceOverviewButton;
    @FXML
    private TextField txtFldSearchStudent;
    @FXML
    private ComboBox<Course> cBoxClassSelection;
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

    private TableColumn<User, String> tblViewPercentage;

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
        setClickCal();
        updatePresentCounter();

        absenceChart.setTitle("Student Absence");
        tblViewName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tblViewStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        tblViewPercentage.setCellValueFactory(cellData -> cellData.getValue().getAbsencePercentageProperty());


        tblViewStatus.setCellFactory(getCustomCellFactory());

    }

    /**
     *
     * @param user
     */
    public void setUser(User user)
    {
        this.teacher = user;
        lblName.setText("Logged in as: " + user.getName());
        fillComboBox();
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
    private void handleAbsenceOverview(ActionEvent event) throws IOException
    {
        if (tblStatusView.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a student inside the studentlist");

            alert.showAndWait();
        } else
        {
            attendanceParser.changeView("Absence Overview", "GUI/View/AttendanceCorrection.fxml", lastSelectedStudent, true);
        }
    }

    private void fillComboBox()
    {

        cBoxClassSelection.setItems(model.comboBoxContentGet(teacher));
    }

    private void showConstantCalender()
    {
        calendar = new DatePicker(LocalDate.now());

        calendar.setDayCellFactory(dateParser.getDayCellFactory());
        DatePickerSkin datePickerSkin = new DatePickerSkin(calendar);
        Region pop = (Region) datePickerSkin.getPopupContent();

        vBoxSelectionContent.setPadding(new Insets(5));

        vBoxSelectionContent.setSpacing(200);
        vBoxSelectionContent.getChildren().add(pop);
    }

    private void setClickCal()
    {
        calendar.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                //Selects the date the teacher has clicked on from the calendar
                LocalDate localDate = calendar.getValue();
                //Converts local date to absolute time (uses default time zone of the computer)
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                //Converts an instant in time object to a date object
                Date date = Date.from(instant);
                //Creates a new day from the clicked date
                clickedDay = dateParser.getDay(new DateTime(date));
                handleDateSelection();
            }
        });

    }

    public void handleDateSelection()
    {

        if (clickedDay != null)
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm change");
            alert.setHeaderText(null);

            DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
            String strDate = dtf.print(clickedDay.getDateInTime());

            //Bit reference - set to 0 if day is meant to be a non-school day, or 1 if it is a school day
            int c;

            if (clickedDay.isSchoolDay() == true)
            {

                alert.setContentText("Are you sure you want to change " + strDate + " to a non-school day?");
                c = 0;
            } else
            {
                alert.setContentText("Are you sure you want to change " + strDate + " to a school day?");
                c = 1;
            }

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK)
            {
                dateParser.changeSchoolDay(clickedDay, c);
            }

        }

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

        automaticUpdate();

    }

    private void clickStatistics(ActionEvent event)
    {

        vBoxMiddle.getChildren().clear();

        if (tblStatusView.getSelectionModel().getSelectedItem() == null)
        {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setContentText("Please select a class and then point on a student inside the studentlist");

            alert.showAndWait();

        }

        vBoxMiddle.getChildren().add(absenceChart);
        vBoxMiddle.getChildren().add(absenceLabel);
        vBoxMiddle.setAlignment(Pos.CENTER);

    }

    @FXML
    private void clickTableStudents(MouseEvent event)
    {
        if (!tblStatusView.getItems().isEmpty())
        {
            lastSelectedStudent = tblStatusView.getSelectionModel().getSelectedItem();
            clearStatistics();
            pie = false;
            stacked = false;
            line = false;
            updateStatistics();

        }
    }

    private void updateView()
    {
        updateView(getUsersFromModel());
    }
    
    private void updateView(ObservableList<User> users)
    {
        tblStatusView.setItems(users);
        updatePresentCounter();
    }
    
    private ObservableList<User> getUsersFromModel()
    {
        return model.updateList(filter, lastSelectedCourse);
    }

    @FXML
    private void AnchorPaneActionEvent(MouseEvent event)
    {
        model.getClassList();
    }

    @FXML
    private void searchFunction(ActionEvent event)
    {
        refreshStudents();

    }

    @FXML
    private void handleRefreshStudents(MouseEvent event)
    {
        refreshStudents();
    }

    /**
     * Automatically updates the list of students and their status
     */
    private void automaticUpdate()
    {
        // The time between every update in milliseconds
        int delay = 1200000; // 120 seconds


        // Creates a new timer
        Timer timer = new Timer();

        // Creates a new timer schedule
        timer.schedule(new TimerTask()
        {

            @Override
            public void run()
            {
                ObservableList<User> users = getUsersFromModel();
                // Creates a new thread
                Thread t = new Thread()
                {
                    @Override
                    public void run()
                    {
                        updateView(users);
                    }
                };
                Platform.runLater(t);
            }
        }, 0, delay);
    }
    
    public void refreshStudents()
    {
        if (!cBoxClassSelection.getSelectionModel().isEmpty())
        {
            filter = txtFldSearchStudent.getText();
            updateView();
            updatePresentCounter();
        }
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

            absenceChart.setData(model.getPieChartData(lastSelectedStudent));
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
            stackedChart.getData().add(model.getStackedChartData(lastSelectedStudent));

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
            lineChart.getData().add(model.getLineChartData(lastSelectedStudent));

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
    private void searchFieldTyped(KeyEvent event)
    {
        filter = txtFldSearchStudent.getText();

        if (filter != null)
        {
            tblStatusView.setItems(model.filterList(filter, lastSelectedCourse));
        }

    }

    public String getCSSClass(String string)
    {
        String cssClass = "";
        if (string.equalsIgnoreCase("offline"))
        {
            cssClass = "absent";

        } else if (string.equalsIgnoreCase("online"))
        {
            cssClass = "present";
        }
        return cssClass;

    }

    private Callback<TableColumn<User, String>, TableCell<User, String>> getCustomCellFactory()
    {
        return (TableColumn<User, String> param)
                -> 
                {
                    return new TableCell<User, String>()
                    {
                        @Override
                        public void updateItem(final String item, boolean empty)
                        {

                            if (item != null)
                            {
                                setText(item);
                                String warningClass = getCSSClass(item);
                                getStyleClass().clear();
                                getStyleClass().add(warningClass);
                            }
                        }
                    };
        };
    }

    @FXML
    private void onPaginationClicked(MouseEvent event)
    {
        if (lastSelectedStudent != null)
        {
            updateStatistics();
        }
    }
}
