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

    public Student[] getDanishClassList()
    {
        
        Student[] danishStudents = new Student[]
        {
            new Student("Adam Lars Hansen", "Online"),
            new Student("Bo McKenzie Sinclair", "Online"),
            new Student("Casper Rødgaard", "Offline"),
            new Student("Casper Tønder Jensen", "Online"),
            new Student("Charlotte Bach Jensen", "Offline"),
            new Student("Emit Arentoft Johansen", "Offline"),
            new Student("Frederik Dyrberg", "Offline"),
            new Student("Jacob Enemark", "Offline"),
            new Student("Jens Nissen", "Offline"),
            new Student("Jesper Bjørnhart Riis", "Offline"),
            new Student("Joan Frøsig Tingskov Christensen", "Offline"),
            new Student("Kenneth Kruse Sørensen", "Offline"),
            new Student("Kenni Bent Rasmussen", "Online"),
            new Student("Lucas Larsen", "Online"),
            new Student("Mads Lorentsen", "Offline"),
            new Student("Mathias Sejrup Plougmann", "Offline"),
            new Student("Mathias Skovgaard Rasmussen", "Online"),
            new Student("Michael Christian Ibsen", "Online"),
            new Student("Mickei Christian Stage Jensen", "Online"),
            new Student("Miklas Kruchov", "Offline"),
            new Student("Nicolai Uhre Larsen", "Online"),
            new Student("Patrick Broe Hansen", "Online"),
            new Student("Rasmus Kærvang Lindved", "Online"),
            new Student("Simon Juhl Birkedal", "Offline"),
            new Student("Simon Walenkamp Hansen", "Offline"),
            new Student("Stefan Olsen", "Online"),
            new Student("Stephan Fuhlendorff", "Online"),
            new Student("Stephan Rosengreen", "Offline"),
            new Student("Thomas Meyer Hansen", "Offline"),
            new Student("Kasper Fage", "Online"),
        };

        
        return danishStudents;
    }
    
    public Student[] getInternaionalClassList()
    {
    Student[] internationalStudents = new Student[]
        {
            new Student("Adam Strasak", "Online"),
            new Student("Boldizsar Koppany", "Online"),
            new Student("Carlos Fermando Ognissanti", "Online"),
            new Student("Daniel Kasper Andersen Matras", "Online"),
            new Student("Deividas Tamosiunas", "Online"),
            new Student("Edison Javier Lamar", "Online"),
            new Student("Edwin Mhoy Silva Rifa", "Online"),
            new Student("Gudlaug Gudjónsdóttir", "Online"),
            new Student("Ingvar Örn Porarinsson", "Online"),
            new Student("Jeppe Ganderup Ehmsen", "Offline"),
            new Student("Jesper Enemark", "Online"),
            new Student("Jonas Husted Andersen", "Offline"),
            new Student("Kristof Perger", "Online"),
            new Student("Kristoffer Møller Christensen", "Offline"),
            new Student("Linda Braarup", "Online"),
            new Student("Mark Palmai", "Online"),
            new Student("Masoud Zangeneh", "Online"),
            new Student("Meng Ting Dunmow", "Offline"),
            new Student("Michal Adam Izdebski", "Online"),
            new Student("Mieszko Aleksander Kozma", "Online"),
            new Student("Peder Blok Laugesen", "Online"),
            new Student("Rasmus Fjord Christensen", "Offline"),
            new Student("Robie Anticristo Sun", "Online"),

        };
    
        return internationalStudents;
    }

}
