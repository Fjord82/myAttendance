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
import myattendance.BE.User;

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
                    + "SELECT People.PID, People.fname, People.mname, People.lname, People.Teacher, Classes.ClassName "
                    + "FROM People, Classes, ClassRelation "
                    + "WHERE People.PID = ClassRelation.PID AND Classes.ClassID = ClassRelation.ClassID AND People.slog=? AND People.spass=?");
            ps.setString(1, login);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            rs.next();

<<<<<<< HEAD
            System.out.println("Loop Started");
            User user;
            boolean isTeacher = rs.getBoolean("Teacher");
            String fullName = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("lname");
            System.out.println("Conditions start");
=======
            User user;
            boolean isTeacher = rs.getBoolean("Teacher");
            String fullName = rs.getString("fname") + " " + rs.getString("mname") + " " + rs.getString("lname");
            int id = rs.getInt("PID");
>>>>>>> Development

            if (!isTeacher)
            {

                String className = rs.getString("classname");

<<<<<<< HEAD
                user = new User(fullName, className, isTeacher);
            } else
            {

                user = new User(fullName, isTeacher);
=======
                user = new User(id, fullName, className, isTeacher);
            } else
            {

                user = new User(id, fullName, isTeacher);
>>>>>>> Development
            }
            return user;

        } catch (SQLException ex)
        {
            System.out.println(ex);
            System.out.println("Connection Error");
            return null;
        }
    }

    public Date getStartDate()
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT dateInTime FROM Calendar WHERE dateID=1");
            ResultSet rs = ps.executeQuery();
            rs.next();
            Date startDate = rs.getDate("dateInTime");
            return startDate;

        } catch (SQLException ex)
        {
            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public Date getLastLoginDate(int PID)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement ps = con.prepareStatement("SELECT lastlogin FROM people WHERE PID="+PID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Date lastLogin = rs.getDate("lastlogin");
            return lastLogin;
            
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
            PreparedStatement ps = con.prepareStatement("SELECT dateInTime FROM Calendar WHERE isschoolday=1");
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
    
//    private Course getStudent(Course course)
//    {
//     
//        try (Connection con = ds.getConnection())
//        {
//            PreparedStatement ps = con.prepareStatement(""
//                    + "SELECT People.fname, People.mname, People.lname, People.Teacher "
//                    + "FROM People, Classes, ClassRelation "
//                    + "WHERE People.PID = ClassRelation.PID AND Classes.ClassID = ClassRelation.ClassID AND People.slog=? AND People.spass=?");
//            ps.setInt(1, PID);
//            ResultSet rs = ps.executeQuery();
//
//            rs.next();
//        } catch (SQLException ex)
//        {
//            Logger.getLogger(DatabaseAccess.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
