package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;

public abstract class Human {
    private String name;
    public double healPoints;
    public String title;
    public int authorityPoints;
    public int statusLevel;
    public double rank;
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
        this.status = true;
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
        this.rank = human.authorityPoints * human.healPoints;
    }

    //Get rank by field
    public double getRankByField(Human human) {
        return this.rank;
    }

    //Get rank
    public static double getRank(Human human) {
        return human.authorityPoints * human.healPoints;
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
        if (person.healPoints < MINIMUM_HP_LEVEL) {
            if (person instanceof Peasant) {
                deadList.add(person);
                peasantsList.remove(person.getIndexByList());
                person.status = false;
            } else if (person instanceof Human) {
                deadList.add(person);
                humansList.remove(person.getIndexByList());
                person.status = false;
            }
        }
    }

    //Copies Heal Points and Authority Points from higher to lower rank object
    public static void changeStats(Human person1, Human person2) {
        if (person1.rank < person2.rank) {
            person1.healPoints = person2.healPoints;
            person1.authorityPoints = person2.authorityPoints;
        } else if (person1.rank > person2.rank) {
            person2.healPoints = person1.healPoints;
            person2.authorityPoints = person1.authorityPoints;
        }
    }

    //Use for all Human objects and extended classes
    @Override
    public String toString() {
        return "I'm - " + this.title + " " + getName() + ". My HP level: " + this.healPoints + ". My authority level: " + authorityPoints + ".";
    }
}