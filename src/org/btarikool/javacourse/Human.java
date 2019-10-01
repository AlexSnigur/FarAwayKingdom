package org.btarikool.javacourse;

import java.awt.*;
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
    private static final double MINIMUM_HP_LEVEL = 0.2d;
    private static double healthIndexDown = 1.164993d;
    private static double healthIndexUp = 1.3d;
    private int ownListId;
    private int collectiveListId;
    private Kingdom kingdom;
    private List<Peasant> myPeasantsList = new ArrayList<>();
    private Human chief;
    private Human subordinate;
    private boolean evenOrOdd;

    //Constructor for Human & Peasant objects
    public Human(String name, String title, double healPoints, int authorityPoints, int statusLevel, int collectiveListId, int ownListId, Kingdom kingdom) {
        this.name = name;
        this.statusLevel = statusLevel;
        this.ownListId = ownListId;
        this.collectiveListId = collectiveListId;
        this.authorityPoints = authorityPoints;
        this.healPoints = healPoints;
        this.title = title;
        this.status = true;
        this.rank = healPoints * authorityPoints;
        this.kingdom = kingdom;
        this.evenOrOdd = collectiveListId%2==0?true:false;
    }

    //Get name
    public String getName() {
        return this.name;
    }

    //Get title
    public String getTitle() {
        return this.title;
    }

    //Get title and name
    public String getTitleAndName() {
        return this.title + " " + this.name;
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

    //Get MINIMUM_HP_LEVEL
    public static double getMINIMUM_HP_LEVEL() {
        return MINIMUM_HP_LEVEL;
    }

    //Get My peasants list
    public List<Peasant> getMyPeasantsList() {
        return myPeasantsList;
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

    //Get own list's ID
    public int getOwnListId() {
        return ownListId;
    }

    //Get collective lit's ID
    public int getCollectiveListId() {
        return collectiveListId;
    }

    //Get kingdom
    public Kingdom getKingdom() {
        return kingdom;
    }

    //Get num of peasants Owned
    public int getNumPeasantsOwned() {
        return myPeasantsList.size() + 1;
    }

    //Get even or odd
    public boolean isEvenOrOdd() {
        return evenOrOdd;
    }

    //Get healthIndexDown
    public static double getHealthIndexDown() {
        return healthIndexDown;
    }

    //Get healthIndexUp
    public static double getHealthIndexUp() {
        return healthIndexUp;
    }

    //Set alternative HealPoints Index by changing the coefficient
    public static void changeHpIndex(double a) {
        healthIndexDown *= a;
        healthIndexUp *= a;
    }

    //Set rank
    public void setRank(Human human) {
        human.rank = human.getAuthorityPoints() * human.getHealPoints();
    }

    //Get rank by field
    public double getRankByField() {
        return this.rank;
    }

    //Get rank by calculation
    public static double getRank(Human human) {
        return human.getAuthorityPoints() * human.getHealPoints();
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

    public void changeHpAndAuthorityLevel(Human person){
        if (this.statusLevel > person.getStatusLevel()) {
            this.setHealPoints(this.getHealPoints()*healthIndexUp);
            this.authorityPoints--;
            this.kingdom.removeFromAliveSetToDeadList(this);
            person.setHealPoints(person.getHealPoints()/healthIndexDown);
            person.setAuthorityPoints(person.getAuthorityPoints() + 1);
        }
        else if (this.statusLevel < person.getStatusLevel()) {
            this.setHealPoints(this.getHealPoints()/healthIndexDown);
            this.authorityPoints++;
            person.setHealPoints(person.getHealPoints()*healthIndexUp);
            person.setAuthorityPoints(getAuthorityPoints() - 1);
            this.kingdom.removeFromAliveSetToDeadList(person);
        }
        setRank(person);
        setRank(this);
    }

    @Override
    public String toString() {
        return "I'm - " + this.getTitleAndName() + ". My HP level: " + this.healPoints + ". My authority level: " + this.authorityPoints + ".";
    }
}