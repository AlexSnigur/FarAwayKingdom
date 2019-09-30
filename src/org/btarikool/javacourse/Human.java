package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;

public abstract class Human {
    private String name;
    private String title;
    private double healPoints;
    private int authorityPoints;
    private int statusLevel;
    private double rank;
    private boolean status; //true is alive, false is dead
    private final double MINIMUM_HP_LEVEL = 0.2d;
    private static double healthIndexDown = 1.164993d;
    private static double healthIndexUp = 1.3d;
    private static final List<Human> HUMAN_LIST = new ArrayList<>();
    private int indexByHumansList;
    private static final List<Peasant> PEASANT_LIST = new ArrayList<>();
    private int indexByPeasantsList;
    private static final List<Human> DEAD_LIST = new ArrayList<>();


    //Default constructor
    public Human() {
    }

    public Human(String name, String phrase) {
    }

    //Constructor for Human & Peasant objects
    public Human(String name, String title, double healPoints, int authorityPoints, int statusLevel) {
        // // If object is is instance of class Peasant
        if (this instanceof Peasant) {
            this.indexByPeasantsList = PEASANT_LIST.size();
            PEASANT_LIST.add((Peasant)this);
            this.name = name + " with Own ID: " + getIndexByList();
        // If object is is instance of class Human
        } else {
            this.indexByHumansList = HUMAN_LIST.size();
            HUMAN_LIST.add(this);
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

    //Set Heal Points
    public void setHealPoints(double healPoints) {
        this.healPoints = healPoints;
    }

    //Get Status Level
    public int getStatusLevel() {
        return this.statusLevel;
    }

    //Get Status
    public boolean getStatus() {
        return this.status;
    }

    //Set status
    public void setStatus(boolean status) {
        this.status = status;
    }

    //Get authorityPoints
    public int getAuthorityPoints() {
        return this.authorityPoints;
    }

    //Set authorityPoints
    public void setAuthorityPoints(int authorityPoints) {
        this.authorityPoints = authorityPoints;
    }

    //Get List of Humans
    public static List<Human> getHumansList() {
        return HUMAN_LIST;
    }

    //Get List of Peasants
    public static List<Peasant> getPeasantsList() {
        return PEASANT_LIST;
    }

    //Get List of Dead Humans
    public static List<Human> getDeadList() {
        return DEAD_LIST;
    }

    //Get Peasant's or Human's ID from their own list
    public int getIndexByList() {
        if (this instanceof Peasant) return this.indexByPeasantsList;
        else return this.indexByHumansList;
    }

    //Print list of Humans
    public static void printListOfHumans() {
        for (Human human : HUMAN_LIST) System.out.println(human);
    }

    //Print list of Peasants
    public static void printListOfPeasants() {
        for (Peasant peasant : PEASANT_LIST) System.out.println(peasant);
    }

    //Print list of dead humans
    public static void printListOfDead() {
        for (Human deadHuman : DEAD_LIST) System.out.println(deadHuman);
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
    public double getRankByField() {
        return this.rank;
    }

    //Get rank
    public static double getRank(Human human) {
        return human.getAuthorityPoints() * human.getHealPoints();
    }

    public void changeHpAndAuthorityLevel(Human person){
        if (this.statusLevel > person.getStatusLevel()) {
            this.setHealPoints(this.getHealPoints()*healthIndexUp);
            this.authorityPoints--;
            removeFromAliveSetToDeadList(this);
            person.setHealPoints(person.getHealPoints()/healthIndexDown);
            person.setAuthorityPoints(person.getAuthorityPoints() + 1);
        }
        else if (this.statusLevel < person.getStatusLevel()) {
            this.setHealPoints(this.getHealPoints()/healthIndexDown);
            this.authorityPoints++;
            person.setHealPoints(person.getHealPoints()*healthIndexUp);
            person.setAuthorityPoints(getAuthorityPoints() - 1);
            removeFromAliveSetToDeadList(person);
        }
        setRank(person);
        setRank(this);
    }

    public void changeHpAndAuthorityLevelVol2(Human person){
        if (this.statusLevel > person.getStatusLevel()) {
            this.healPoints *= healthIndexUp;
            this.authorityPoints--;
            removeFromAliveSetToDeadList(this);
            person.setHealPoints(person.getHealPoints()/healthIndexDown);
            person.setAuthorityPoints(person.getAuthorityPoints() + 1);

        }
        else if (this.statusLevel < person.getStatusLevel()) {
            this.healPoints /= healthIndexDown;
            this.authorityPoints++;
            person.setHealPoints(person.getHealPoints()*healthIndexUp);
            person.setAuthorityPoints(person.getAuthorityPoints() - 1);
            removeFromAliveSetToDeadList(person);
        }
        setRank(person);
        setRank(this);
    }

    //Remove from Alive Set to Dead list
    public void removeFromAliveSetToDeadList(Human person) {
        if (person.getHealPoints() < MINIMUM_HP_LEVEL) {
            if (person instanceof Peasant) {
                DEAD_LIST.add(person);
                PEASANT_LIST.set(person.getIndexByList(), null);
                person.status = false;
            } else if (person instanceof Human) {
                DEAD_LIST.add(person);
                HUMAN_LIST.set(person.getIndexByList(), null);
                person.status = false;
            }
        }
    }

    //Copies Heal Points and Authority Points from higher to lower rank object
    public static void changeStats(Human person1, Human person2) {
        if (person1.getRankByField() < person2.getRankByField()) {
            person1.setHealPoints(person2.getHealPoints());
            person1.setAuthorityPoints(person2.getAuthorityPoints());
        } else if (person1.getRankByField() > person2.getRankByField()) {
            person2.setHealPoints(person1.getHealPoints());
            person2.setAuthorityPoints(person1.getAuthorityPoints());
        }
    }

    //Use for all Human objects and extended classes
    @Override
    public String toString() {
        return "I'm - " + this.title + " " + this.name + ". My HP level: " + this.healPoints + ". My authority level: " + this.authorityPoints + ".";
    }
}