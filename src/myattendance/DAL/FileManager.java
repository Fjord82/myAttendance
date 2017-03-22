    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.DAL;

import java.util.ArrayList;
import java.util.List;
import myattendance.BE.User;

/**
 *
 * @author Fjord82
 */
public class FileManager
{

    public FileManager()
    {

    }

    public List<User> getDanishClassList()
    {
        
        List<User> danishStudents = new ArrayList<>();
            danishStudents.add(new User("Adam Lars Hansen", "Online", 50, 50));
            danishStudents.add(new User("Bo McKenzie Sinclair", "Online", 50, 50));
            danishStudents.add(new User("Casper Rødgaard", "Offline", 50, 50));
            danishStudents.add(new User("Casper Tønder Jensen", "Online", 50, 50));
            danishStudents.add(new User("Charlotte Bach Jensen", "Offline", 50, 50));
            danishStudents.add(new User("Emit Arentoft Johansen", "Offline", 50, 50));
            danishStudents.add(new User("Frederik Dyrberg", "Offline", 50, 50));
            danishStudents.add(new User("Jacob Enemark", "Offline", 50, 50));
            danishStudents.add(new User("Jens Nissen", "Offline", 50, 50));
            danishStudents.add(new User("Jesper Bjørnhart Riis", "Offline", 50, 50));
            danishStudents.add(new User("Joan Frøsig Tingskov Christensen", "Offline", 50, 50));
            danishStudents.add(new User("Kenneth Kruse Sørensen", "Offline", 50, 50));
            danishStudents.add(new User("Kenni Bent Rasmussen", "Online", 50, 50));
            danishStudents.add(new User("Lucas Larsen", "Online", 50, 50));
            danishStudents.add(new User("Mads Lorentsen", "Offline", 50, 50));
            danishStudents.add(new User("Mathias Sejrup Plougmann", "Offline", 50, 50));
            danishStudents.add(new User("Mathias Skovgaard Rasmussen", "Online", 50, 50));
            danishStudents.add(new User("Michael Christian Ibsen", "Online", 50, 50));
            danishStudents.add(new User("Mickei Christian Stage Jensen", "Online", 50, 50));
            danishStudents.add(new User("Miklas Kruchov", "Offline", 50, 50));
            danishStudents.add(new User("Nicolai Uhre Larsen", "Online", 50, 50));
            danishStudents.add(new User("Patrick Broe Hansen", "Online", 50, 50));
            danishStudents.add(new User("Rasmus Kærvang Lindved", "Online", 50, 50));
            danishStudents.add(new User("Simon Juhl Birkedal", "Offline", 50, 50));
            danishStudents.add(new User("Simon Walenkamp Hansen", "Offline", 50, 50));
            danishStudents.add(new User("Stefan Olsen", "Online", 50, 50));
            danishStudents.add(new User("Stephan Fuhlendorff", "Online", 50, 50));
            danishStudents.add(new User("Stephan Rosengreen", "Offline", 50, 50));
            danishStudents.add(new User("Thomas Meyer Hansen", "Offline", 50, 50));
            danishStudents.add(new User("Kasper Fage", "Online", 50, 50));
        
        return danishStudents;
    }
    
    public List<User> getInternationalClassList()
    {
    List<User> internationalStudents = new ArrayList<>();
        
            internationalStudents.add(new User("Adam Strasak", "Online", 50, 50));
            internationalStudents.add(new User("Boldizsar Koppany", "Online", 50, 50));
            internationalStudents.add(new User("Carlos Fermando Ognissanti", "Online", 50, 50));
            internationalStudents.add(new User("Daniel Kasper Andersen Matras", "Online", 50, 50));
            internationalStudents.add(new User("Deividas Tamosiunas", "Online", 50, 50));
            internationalStudents.add(new User("Edison Javier Lamar", "Online", 50, 50));
            internationalStudents.add(new User("Edwin Mhoy Silva Rifa", "Online", 50, 50));
            internationalStudents.add(new User("Gudlaug Gudjónsdóttir", "Online", 50, 50));
            internationalStudents.add(new User("Ingvar Örn Porarinsson", "Online", 50, 50));
            internationalStudents.add(new User("Jeppe Ganderup Ehmsen", "Offline", 50, 50));
            internationalStudents.add(new User("Jesper Enemark", "Online", 50, 50));
            internationalStudents.add(new User("Jonas Husted Andersen", "Offline", 50, 50));
            internationalStudents.add(new User("Kristof Perger", "Online", 50, 50));
            internationalStudents.add(new User("Kristoffer Møller Christensen", "Offline", 50, 50));
            internationalStudents.add(new User("Linda Braarup", "Online", 50, 50));
            internationalStudents.add(new User("Mark Palmai", "Online", 50, 50));
            internationalStudents.add(new User("Masoud Zangeneh", "Online", 50, 50));
            internationalStudents.add(new User("Meng Ting Dunmow", "Offline", 50, 50));
            internationalStudents.add(new User("Michal Adam Izdebski", "Online", 50, 50));
            internationalStudents.add(new User("Mieszko Aleksander Kozma", "Online", 50, 50));
            internationalStudents.add(new User("Peder Blok Laugesen", "Online", 50, 50));
            internationalStudents.add(new User("Rasmus Fjord Christensen", "Offline", 50, 50));
            internationalStudents.add(new User("Robie Anticristo Sun", "Online", 50, 50));

    
        return internationalStudents;
    }
    
    public User getRasmus()
    {
        return new User("Rasmus Fjord Christensen", "Offline", 5, 95);
    }

}
