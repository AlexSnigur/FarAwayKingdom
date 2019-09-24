package org.btarikool.javacourse;

import java.util.ArrayList;

public class Peasant extends Human{
    private final static String TITLE = "Крестьянин";
    private String name;
    private final double HEALTH = 0.9d;
    private final int AUTHORITY = 2;
    private final int STATUS_LEVEL = 3;


    public Peasant(String name){
        super(name);
        this.healPoints = HEALTH;
        this.title = TITLE;
        this.authorityPoints = AUTHORITY;
        this.statusLevel = STATUS_LEVEL;

    }

    public void presents(Human person, String present) {
        System.out.println(getName() + " даёт " + present + " " + person.title + " " + person.getName());
        this.changeHpAndAuthorityLevel(person);
    }
}