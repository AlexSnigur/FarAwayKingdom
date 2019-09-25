package org.btarikool.javacourse;

import org.omg.CORBA.TIMEOUT;

import java.util.ArrayList;

public class Peasant extends Human{
    private final static String TITLE = "Peasant of";
    private String name;
    private final double HEALTH = 0.9d;
    private final int AUTHORITY = 2;
    private final int STATUS_LEVEL = 3;


    public Peasant(String name){
        super(name, TITLE, 0.9d, 2, 3);

    }

    public void presents(Human person, String present) {
        System.out.println(getName() + " даёт " + present + " " + person.getTitle() + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }
}