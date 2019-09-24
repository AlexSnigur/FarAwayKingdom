package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;

public abstract class Human {
    private String name;
    public double healPoints;
    public String title;
    public int authorityPoints;
    public int statusLevel;
    private static double healthIndexDown = 1.164993;
    private static double healthIndexUp = 1.3;
    private static List<Human> humansList = new ArrayList<>();
    private int indexByHumansList;
    private static List<Peasant> peasantsList = new ArrayList<>();
    private int indexByPeasantsList;

    //Default constructor
    public Human() {
    }

    //Constructor for Human & Peasant objects
    public Human(String name) {
        // // If object is is instance of class Peasant
        if (this instanceof Peasant) {
            this.indexByPeasantsList = peasantsList.size();
            peasantsList.add((Peasant)this);
            this.name = name + "'s peasant with Own ID: " + getIndexByList();
        // If object is is instance of class Human
        } else {
            this.indexByHumansList = humansList.size();
            humansList.add(this);
            this.name = name;
        }
    }

    //Get name
    public String getName() {
        return this.name;
    }

    //Get List of Humans
    public static List<Human> getHumansList() {
        return humansList;
    }

    //Get List of Peasants
    public static List<Peasant> getPeasantsList() {
        return peasantsList;
    }

    //Get Peasant's or Human's ID from their own list
    public int getIndexByList() {
        if (this instanceof Peasant) return this.indexByPeasantsList;
        else return this.indexByHumansList;
    }

    //Set alternative HealPoints Index by changing the coefficient
    public static void changeHpIndex(double a) {
        healthIndexDown *= a;
        healthIndexUp *= a;
    }

    //Sout list of Humans
    public static void printListOfHumans() {
        for (Human human : humansList) System.out.println(human);
    }

    public void changeHpAndAuthorityLevel(Human person){
        if (this.statusLevel > person.statusLevel) {
            this.healPoints *= healthIndexUp;
            this.authorityPoints--;
            person.healPoints /= healthIndexDown;
            person.authorityPoints++;
        }
        else if (this.statusLevel < person.statusLevel) {
            this.healPoints /= healthIndexDown;
            this.authorityPoints++;
            person.healPoints *= healthIndexUp;
            person.authorityPoints--;
        }
    }

    //Use for all Human objects and extended classes
    @Override
    public String toString() {
        return "I'm - " + this.title + " " + getName() + ". My HP level: " + this.healPoints + ". My authority level: " + authorityPoints + ".";
    }
}