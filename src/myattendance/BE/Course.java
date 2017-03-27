package myattendance.BE;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course
{
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    
    private List<User> userList = new ArrayList<>();
    
    @Override
    public String toString()
    {
        return name.get();
    }
    
    public Course(int id, String name)
    {
        this.id.set(id);
        this.name.set(name);
    }

    public int getId()
    {
        return id.get();
    }

    public String getName()
    {
        return name.get();
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }
    
    public void addToUserList(User user)
    {
        this.userList.add(user);
    }
    
    public void clearUserList()
    {
        userList.clear();
    }
    
    
    
    
    

}
