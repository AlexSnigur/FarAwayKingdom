package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;

public abstract class Human {
    private String name;
    private double healPoints;
    private String title;
    private int authorityPoints;
    private int statusLevel;
    private double rank;
    private boolean status; //true is alive, false is dead
    private final double MINIMUM_HP_LEVEL = 0.2d;
    private static double healthIndexDown = 1.164993d;
    private static double healthIndexUp = 1.3d;
    private static List<Human> humansList = new ArrayList<>();
    private int indexByHumansList;
    private static List<Peasant> peasantsList = new ArrayList<>();
    private int indexByPeasantsList;
    private static List<Human> deadList = new ArrayList<>();


    //Default constructor
    public Human() {
    }

    //Constructor for Human & Peasant objects
    public Human(String name, String title, double healPoints, int authorityPoints, int statusLevel) {
        // // If object is is instance of class Peasant
        if (this instanceof Peasant) {
            this.indexByPeasantsList = peasantsList.size();
            peasantsList.add((Peasant)this);
            this.name = name + " with Own ID: " + getIndexByList();
        // If object is is instance of class Human
        } else {
            this.indexByHumansList = humansList.size();
            humansList.add(this);
            this.name = name;
        }
        this.statusLevel = statusLevel;
        this.authorityPoints = authorityPoints;
        this.healPoints = healPoints;
        this.title = title;
        this.status = true;
        this.rank = healPoints * authorityPoints;
    }

    //Get name
    public String getName() {
        return this.name;
    }

    //Get title
    public String getTitle() {
        return this.title;
    }

    //Get Heal Points
    public double getHealPoints() {
        return healPoints;
    }

    //Get authorityPoints
    public int getAuthorityPoints() {
        return this.authorityPoints;
    }

    //Get List of Humans
    public static List<Human> getHumansList() {
        return humansList;
    }

    //Get List of Peasants
    public static List<Peasant> getPeasantsList() {
        return peasantsList;
    }

    //Get List of Dead Humans
    public static List<Human> getDeadList() {
        return deadList;
    }

    //Get Peasant's or Human's ID from their own list
    public int getIndexByList() {
        if (this instanceof Peasant) return this.indexByPeasantsList;
        else return this.indexByHumansList;
    }

    //Print list of Humans
    public static void printListOfHumans() {
        for (Human human : humansList) System.out.println(human);
    }

    //Print list of Peasants
    public static void printListOfPeasants() {
        for (Peasant peasant : peasantsList) System.out.println(peasant);
    }

    //Print list of dead humans
    public static void printListOfDead() {
        for (Human deadHuman : deadList) System.out.println(deadHuman);
    }

    //Set alternative HealPoints Index by changing the coefficient
    public static void changeHpIndex(double a) {
        healthIndexDown *= a;
        healthIndexUp *= a;
    }

    //Set rank
    public void setRank(Human human) {
        this.rank = human.getAuthorityPoints() * human.getHealPoints();
    }

    //Get rank by field
    public double getRankByField(Human human) {
        return this.rank;
    }

    //Get rank
    public static double getRank(Human human) {
        return human.getAuthorityPoints() * human.getHealPoints();
    }

    public void changeHpAndAuthorityLevel(Human person){
        if (this.statusLevel > person.statusLevel) {
            this.healPoints *= healthIndexUp;
            this.authorityPoints--;
            removeFromAliveSetToDeadList(this);
            person.healPoints /= healthIndexDown;
            person.authorityPoints++;
        }
        else if (this.statusLevel < person.statusLevel) {
            this.healPoints /= healthIndexDown;
            this.authorityPoints++;
            person.healPoints *= healthIndexUp;
            person.authorityPoints--;
            removeFromAliveSetToDeadList(person);
        }
        setRank(person);
        setRank(this);
    }

    public void changeHpAndAuthorityLevelVol2(Human person){
        if (this.statusLevel > person.statusLevel) {
            this.healPoints *= healthIndexUp;
            this.authorityPoints--;
            removeFromAliveSetToDeadList(this);
            person.healPoints /= healthIndexDown;
            person.authorityPoints++;

        }
        else if (this.statusLevel < person.statusLevel) {
            this.healPoints /= healthIndexDown;
            this.authorityPoints++;
            person.healPoints *= healthIndexUp;
            person.authorityPoints--;
            removeFromAliveSetToDeadList(person);
        }
        setRank(person);
        setRank(this);
    }

    //Remove from Alive Set to Dead list
    public void removeFromAliveSetToDeadList(Human person) {
        if (person.getHealPoints() < MINIMUM_HP_LEVEL) {
            if (person instanceof Peasant) {
                deadList.add(person);
                peasantsList.set(person.getIndexByList(), null);
                person.status = false;
            } else if (person instanceof Human) {
                deadList.add(person);
                humansList.set(person.getIndexByList(), null);
                person.status = false;
            }
        }
    }

    //Copies Heal Points and Authority Points from higher to lower rank object
    public static void changeStats(Human person1, Human person2) {
        if (person1.rank < person2.rank) {
            person1.healPoints = person2.getHealPoints();
            person1.authorityPoints = person2.getAuthorityPoints();
        } else if (person1.rank > person2.rank) {
            person2.healPoints = person1.getHealPoints();
            person2.authorityPoints = person1.getAuthorityPoints();
        }
    }

    //Use for all Human objects and extended classes
    @Override
    public String toString() {
        return "I'm - " + this.title + " " + getName() + ". My HP level: " + this.getHealPoints() + ". My authority level: " + authorityPoints + ".";
    }
}