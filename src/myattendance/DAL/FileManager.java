/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myattendance.DAL;

import java.util.ArrayList;
import java.util.List;
import myattendance.BE.Student;

/**
 *
 * @author Fjord82
 */
public class FileManager
{

    private static FileManager instance;

    public static FileManager getInstance()
    {
        if (instance == null)
        {
            instance = new FileManager();
        }
        return instance;
    }

    private FileManager()
    {

    }

    public List<Student> getDanishClassList()
    {
        
        List<Student> danishStudents = new ArrayList<>();
            danishStudents.add(new Student("Adam Lars Hansen", "Online", 50, 50));
            danishStudents.add(new Student("Bo McKenzie Sinclair", "Online", 50, 50));
            danishStudents.add(new Student("Casper Rødgaard", "Offline", 50, 50));
            danishStudents.add(new Student("Casper Tønder Jensen", "Online", 50, 50));
            danishStudents.add(new Student("Charlotte Bach Jensen", "Offline", 50, 50));
            danishStudents.add(new Student("Emit Arentoft Johansen", "Offline", 50, 50));
            danishStudents.add(new Student("Frederik Dyrberg", "Offline", 50, 50));
            danishStudents.add(new Student("Jacob Enemark", "Offline", 50, 50));
            danishStudents.add(new Student("Jens Nissen", "Offline", 50, 50));
            danishStudents.add(new Student("Jesper Bjørnhart Riis", "Offline", 50, 50));
            danishStudents.add(new Student("Joan Frøsig Tingskov Christensen", "Offline", 50, 50));
            danishStudents.add(new Student("Kenneth Kruse Sørensen", "Offline", 50, 50));
            danishStudents.add(new Student("Kenni Bent Rasmussen", "Online", 50, 50));
            danishStudents.add(new Student("Lucas Larsen", "Online", 50, 50));
            danishStudents.add(new Student("Mads Lorentsen", "Offline", 50, 50));
            danishStudents.add(new Student("Mathias Sejrup Plougmann", "Offline", 50, 50));
            danishStudents.add(new Student("Mathias Skovgaard Rasmussen", "Online", 50, 50));
            danishStudents.add(new Student("Michael Christian Ibsen", "Online", 50, 50));
            danishStudents.add(new Student("Mickei Christian Stage Jensen", "Online", 50, 50));
            danishStudents.add(new Student("Miklas Kruchov", "Offline", 50, 50));
            danishStudents.add(new Student("Nicolai Uhre Larsen", "Online", 50, 50));
            danishStudents.add(new Student("Patrick Broe Hansen", "Online", 50, 50));
            danishStudents.add(new Student("Rasmus Kærvang Lindved", "Online", 50, 50));
            danishStudents.add(new Student("Simon Juhl Birkedal", "Offline", 50, 50));
            danishStudents.add(new Student("Simon Walenkamp Hansen", "Offline", 50, 50));
            danishStudents.add(new Student("Stefan Olsen", "Online", 50, 50));
            danishStudents.add(new Student("Stephan Fuhlendorff", "Online", 50, 50));
            danishStudents.add(new Student("Stephan Rosengreen", "Offline", 50, 50));
            danishStudents.add(new Student("Thomas Meyer Hansen", "Offline", 50, 50));
            danishStudents.add(new Student("Kasper Fage", "Online", 50, 50));
        
        return danishStudents;
    }
    
    public List<Student> getInternaionalClassList()
    {
    List<Student> internationalStudents = new ArrayList<>();
        
            internationalStudents.add(new Student("Adam Strasak", "Online", 50, 50));
            internationalStudents.add(new Student("Boldizsar Koppany", "Online", 50, 50));
            internationalStudents.add(new Student("Carlos Fermando Ognissanti", "Online", 50, 50));
            internationalStudents.add(new Student("Daniel Kasper Andersen Matras", "Online", 50, 50));
            internationalStudents.add(new Student("Deividas Tamosiunas", "Online", 50, 50));
            internationalStudents.add(new Student("Edison Javier Lamar", "Online", 50, 50));
            internationalStudents.add(new Student("Edwin Mhoy Silva Rifa", "Online", 50, 50));
            internationalStudents.add(new Student("Gudlaug Gudjónsdóttir", "Online", 50, 50));
            internationalStudents.add(new Student("Ingvar Örn Porarinsson", "Online", 50, 50));
            internationalStudents.add(new Student("Jeppe Ganderup Ehmsen", "Offline", 50, 50));
            internationalStudents.add(new Student("Jesper Enemark", "Online", 50, 50));
            internationalStudents.add(new Student("Jonas Husted Andersen", "Offline", 50, 50));
            internationalStudents.add(new Student("Kristof Perger", "Online", 50, 50));
            internationalStudents.add(new Student("Kristoffer Møller Christensen", "Offline", 50, 50));
            internationalStudents.add(new Student("Linda Braarup", "Online", 50, 50));
            internationalStudents.add(new Student("Mark Palmai", "Online", 50, 50));
            internationalStudents.add(new Student("Masoud Zangeneh", "Online", 50, 50));
            internationalStudents.add(new Student("Meng Ting Dunmow", "Offline", 50, 50));
            internationalStudents.add(new Student("Michal Adam Izdebski", "Online", 50, 50));
            internationalStudents.add(new Student("Mieszko Aleksander Kozma", "Online", 50, 50));
            internationalStudents.add(new Student("Peder Blok Laugesen", "Online", 50, 50));
            internationalStudents.add(new Student("Rasmus Fjord Christensen", "Offline", 50, 50));
            internationalStudents.add(new Student("Robie Anticristo Sun", "Online", 50, 50));

    
        return internationalStudents;
    }

}
