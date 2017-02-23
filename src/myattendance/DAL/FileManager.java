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
            danishStudents.add(new Student("Adam Lars Hansen", "Online"));
            danishStudents.add(new Student("Bo McKenzie Sinclair", "Online"));
            danishStudents.add(new Student("Casper Rødgaard", "Offline"));
            danishStudents.add(new Student("Casper Tønder Jensen", "Online"));
            danishStudents.add(new Student("Charlotte Bach Jensen", "Offline"));
            danishStudents.add(new Student("Emit Arentoft Johansen", "Offline"));
            danishStudents.add(new Student("Frederik Dyrberg", "Offline"));
            danishStudents.add(new Student("Jacob Enemark", "Offline"));
            danishStudents.add(new Student("Jens Nissen", "Offline"));
            danishStudents.add(new Student("Jesper Bjørnhart Riis", "Offline"));
            danishStudents.add(new Student("Joan Frøsig Tingskov Christensen", "Offline"));
            danishStudents.add(new Student("Kenneth Kruse Sørensen", "Offline"));
            danishStudents.add(new Student("Kenni Bent Rasmussen", "Online"));
            danishStudents.add(new Student("Lucas Larsen", "Online"));
            danishStudents.add(new Student("Mads Lorentsen", "Offline"));
            danishStudents.add(new Student("Mathias Sejrup Plougmann", "Offline"));
            danishStudents.add(new Student("Mathias Skovgaard Rasmussen", "Online"));
            danishStudents.add(new Student("Michael Christian Ibsen", "Online"));
            danishStudents.add(new Student("Mickei Christian Stage Jensen", "Online"));
            danishStudents.add(new Student("Miklas Kruchov", "Offline"));
            danishStudents.add(new Student("Nicolai Uhre Larsen", "Online"));
            danishStudents.add(new Student("Patrick Broe Hansen", "Online"));
            danishStudents.add(new Student("Rasmus Kærvang Lindved", "Online"));
            danishStudents.add(new Student("Simon Juhl Birkedal", "Offline"));
            danishStudents.add(new Student("Simon Walenkamp Hansen", "Offline"));
            danishStudents.add(new Student("Stefan Olsen", "Online"));
            danishStudents.add(new Student("Stephan Fuhlendorff", "Online"));
            danishStudents.add(new Student("Stephan Rosengreen", "Offline"));
            danishStudents.add(new Student("Thomas Meyer Hansen", "Offline"));
            danishStudents.add(new Student("Kasper Fage", "Online"));
        
        return danishStudents;
    }
    
    public List<Student> getInternaionalClassList()
    {
    List<Student> internationalStudents = new ArrayList<>();
        
            internationalStudents.add(new Student("Adam Strasak", "Online"));
            internationalStudents.add(new Student("Boldizsar Koppany", "Online"));
            internationalStudents.add(new Student("Carlos Fermando Ognissanti", "Online"));
            internationalStudents.add(new Student("Daniel Kasper Andersen Matras", "Online"));
            internationalStudents.add(new Student("Deividas Tamosiunas", "Online"));
            internationalStudents.add(new Student("Edison Javier Lamar", "Online"));
            internationalStudents.add(new Student("Edwin Mhoy Silva Rifa", "Online"));
            internationalStudents.add(new Student("Gudlaug Gudjónsdóttir", "Online"));
            internationalStudents.add(new Student("Ingvar Örn Porarinsson", "Online"));
            internationalStudents.add(new Student("Jeppe Ganderup Ehmsen", "Offline"));
            internationalStudents.add(new Student("Jesper Enemark", "Online"));
            internationalStudents.add(new Student("Jonas Husted Andersen", "Offline"));
            internationalStudents.add(new Student("Kristof Perger", "Online"));
            internationalStudents.add(new Student("Kristoffer Møller Christensen", "Offline"));
            internationalStudents.add(new Student("Linda Braarup", "Online"));
            internationalStudents.add(new Student("Mark Palmai", "Online"));
            internationalStudents.add(new Student("Masoud Zangeneh", "Online"));
            internationalStudents.add(new Student("Meng Ting Dunmow", "Offline"));
            internationalStudents.add(new Student("Michal Adam Izdebski", "Online"));
            internationalStudents.add(new Student("Mieszko Aleksander Kozma", "Online"));
            internationalStudents.add(new Student("Peder Blok Laugesen", "Online"));
            internationalStudents.add(new Student("Rasmus Fjord Christensen", "Offline"));
            internationalStudents.add(new Student("Robie Anticristo Sun", "Online"));

    
        return internationalStudents;
    }

}
