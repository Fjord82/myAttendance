package myattendance.GUI.Model;

import java.util.List;
import myattendance.BE.Day;
import myattendance.BE.User;
import myattendance.BLL.BLLFacade;
import org.joda.time.DateTime;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

/**
 *
 * @author meng
 */
public class DateParser
{

    BLLFacade bllFacade = BLLFacade.getInstance();

    private static DateParser instance;

    public static DateParser getInstance()
    {
        if (instance == null)
        {
            instance = new DateParser();
        }
        return instance;
    }

    private DateParser()
    {

    }

    public DateTime getStartDate()
    {
        return bllFacade.getStartDate();
    }

    public Day getDay(DateTime dateTime)
    {
        return bllFacade.getDay(dateTime);
    }

    public void recordAbsence(User user, Day today)
    {
        bllFacade.recordAbsence(user, today);
    }

    public void changeSchoolDay(Day d, int c)
    {
        bllFacade.changeToNonSchoolDay(d, c);
    }

    public List<Day> listNonSchoolDays()
    {
        return bllFacade.listNonSchoolDays();
    }

    // Factory to create Cell of DatePicker
    public Callback<DatePicker, DateCell> getDayCellFactory()
    {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
        {

            @Override
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {

                        super.updateItem(item, empty);

                        for (Day day : listNonSchoolDays())
                        {
                            if (day.getDateInTime().toLocalDate().toString().equals(item.toString()))
                            {
                                setTooltip(new Tooltip("Not school day"));
                                setStyle("-fx-background-color: #C0C0C0;");
                            }
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }

}
