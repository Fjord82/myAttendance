package myattendance.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import myattendance.BE.Course;
import myattendance.BE.Day;
import myattendance.BE.User;
import org.joda.time.DateTime;

/**
 *
 * @author jeppe
 */
public class DatabaseAccess
{

    private static SQLServerDataSource ds = new SQLServerDataSource();

    public DatabaseAccess()
    {
        setupDataSource();

    }

    private static void setupDataSource()
    {
        ds.setDatabaseName("CS2016B_12_Attendance");
        ds.setUser("CS2016B_12");
        ds.setPassword("CS2016B_12");

        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }

    public User loginQuery(String login, String pass)
    {

        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(""
                    + "SELECT People.PID, People.fname, People.mname, People.lname, People.Teacher, People.lastLogin, Classes.ClassName "
                    + "FROM People, Classes, ClassRelation "
                    + "WHERE People.PID = ClassRelation.PID AND Classes.ClassID = ClassRelation.ClassID AND People.slog=? AND People.spass=?");
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            rs.next();

            User user;
            boolean isTeacher = rs.getBoolean("Teacher");
            String fullName = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("lname");
            int id = rs.getInt("PID");

            if (!isTeacher)
            {

                String className = rs.getString("classname");
                DateTime lastLogin = (new DateTime(rs.getDate("lastLogin")));

                user = new User(id, fullName, className, isTeacher, lastLogin);

            } else
            {

                user = new User(id, fullName, isTeacher);

            }
            return user;

        } catch (SQLException ex)
        {
            System.out.println(ex);
            System.out.println("Connection Error");
            return null;
        }
    }

    public void updateLastLogin(User user)
    {
        DateTime dateTime = new DateTime();
        java.sql.Date date = new java.sql.Date(dateTime.getMillis());
        String sql = "UPDATE People SET LastLogin = ? WHERE PID = ?";

        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            ps.setInt(2, user.getId());

            ps.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DateTime getStartDate()
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT dateInTime FROM Calendar WHERE dateID=1");
            ResultSet rs = ps.executeQuery();
            rs.next();
            Date startDate = rs.getDate("dateInTime");
            DateTime startDateTime = new DateTime(startDate);
            return startDateTime;

        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public Integer totalSchoolDays()
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT dateInTime FROM Calendar WHERE isSchoolDay=1");
            ResultSet rs = ps.executeQuery();
            rs.next();

            int dayNumber = 1;
            while (rs.next())
            {
                // Process the row.
                dayNumber++;
            }

            //Integer totalNoOfDays = rs.getRow();
            return dayNumber;

        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Course> getCourses(int PID)
    {
        List<Course> courseList = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(""
                    + "SELECT c.classID, c.ClassName "
                    + "FROM People p, Classes c, ClassRelation cr "
                    + "WHERE p.PID = cr.PID AND cr.ClassID = c.ClassID AND p.PID =?");

            ps.setInt(1, PID);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("classID");
                String name = rs.getString("className");
                Course course = new Course(id, name);

                courseList.add(course);
            }

            return courseList;

        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public Course fillUsersInCourse(Course course)
    {

        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(""
                    + "SELECT p.fname, p.mname, p.lname, p.PID, p.Teacher, LastLogin "
                    + "FROM People p, Classes c, ClassRelation cr "
                    + "WHERE p.PID = cr.PID AND c.ClassID = cr.ClassID AND p.Teacher = 0 AND c.ClassID=?");
            ps.setInt(1, course.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("PID");
                String name = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("lname");
                boolean isTeacher = rs.getBoolean("Teacher");

                User user = new User(id, name, isTeacher);
                user.setLastLogin(new DateTime(rs.getDate("lastlogin")));

                course.addToUserList(user);

            }
            return course;
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Day getDay(DateTime dateTime)
    {

        java.sql.Date date = new java.sql.Date(dateTime.getMillis());

        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Calendar WHERE dateIntTime=?");
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int dateID = rs.getInt("dateID");
                int weekdayNumber = rs.getInt("weekdayNumber");
                String weekdayName = rs.getString("weekdayName");
                boolean isSchoolDay = rs.getBoolean("isSchoolDay");

                Day day = new Day(dateID, dateTime, weekdayNumber, weekdayName, isSchoolDay);
                return day;
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList getDatesBetweenDays(DateTime startDate, DateTime endDate)
    {
        ArrayList<Integer> datesAbsent = new ArrayList();
        java.sql.Date sDate = new java.sql.Date(startDate.getMillis());
        java.sql.Date eDate = new java.sql.Date(endDate.getMillis());

        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT dateID FROM calendar WHERE isSchoolDay =? AND dateInTime BETWEEN ? AND ?");
            ps.setBoolean(1, true);
            ps.setDate(2, sDate);
            ps.setDate(3, eDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                datesAbsent.add(rs.getInt("dateID"));
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datesAbsent;
    }

    public void writeAbsencesIntoDB(int userID, DateTime startDate, DateTime endDate)
    {
        ArrayList<Integer> datesAbsent = new ArrayList(getDatesBetweenDays(startDate,endDate));
        
        try (Connection con = ds.getConnection())
        {
            for (int i = 0; i < datesAbsent.size()-1; i++)
            {
                PreparedStatement ps = con.prepareStatement("INSERT INTO Absence (PID, dateID) VALUES (?, ?)");
                ps.setInt(1, userID);
                ps.setInt(2,datesAbsent.get(i));
                ps.execute();
                
            }
            

            } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
