package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;

public abstract class Human implements Actions {
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
    private static int id = 0;
    private Kingdom kingdom;
    private List<Peasant> myPeasantsList = new ArrayList<>();
    private List<Human> subordinateList = new ArrayList<>();
    private Human chief;
    private boolean evenOrOdd;
    private String[][] phrase;

    public Human() {
    }

    //Constructor for Human & Peasant objects
    public Human(String name, String title, int statusLevel, String[][] phrase, int collectiveListId, int ownListId, Kingdom kingdom, Human chief) {
        this.name = name;
        this.statusLevel = statusLevel;
        this.ownListId = ownListId;
        this.collectiveListId = collectiveListId;
        this.id++;
        if (this instanceof Peasant) {
            this.authorityPoints = 2;
            this.healPoints = 0.9d;
        } else {
            this.authorityPoints = kingdom.getSettings().getAuth(this);
            this.healPoints = kingdom.getSettings().getHP(this);
        }

        this.title = title;
        this.status = true;
        this.rank = healPoints * authorityPoints;
        this.kingdom = kingdom;
        this.chief = chief;
        this.evenOrOdd = collectiveListId%2==0?true:false;
        this.phrase = phrase;
        if (!(this instanceof King || this instanceof Enemy)) chief.getSubordinateList().add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    //Get name
    public String getName() {
        return this.name;
    }

    //Get title
    public String getTitle() {
        return this.title;
    }

    //Set title

    public void setTitle(String title) {
        this.title = title;
    }

    public static int getId() {
        return id;
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

    //Set StatusLevel
    public void setStatusLevel(int statusLevel) {
        this.statusLevel = statusLevel;
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

    public void setOwnListId(int ownListId) {
        this.ownListId = ownListId;
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

    public boolean isEvenOrOdd() {
        return evenOrOdd;
    }

    public Human getChief() {
        return chief;
    }

    //Set chief
    public void setChief(Human chief) {
        this.chief = chief;
    }

    //Get healthIndexDown
    public static double getHealthIndexDown() {
        return healthIndexDown;
    }

    public String getPhrase(int i) {
        return this.evenOrOdd ? this.phrase[i][1] : this.phrase[i][0];
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
    public static void setRank(Human human) {
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

    //Get subordinates list
    public List<Human> getSubordinateList() {
        return subordinateList;
    }

    //Get subordinates (string)
    public String getStringOfSubordinateList() {
        String allSubordinates = "";
        for (int x = 0; x < this.subordinateList.size(); x++) allSubordinates = allSubordinates + " " + this.subordinateList.get(x).getTitleAndName() + (this.subordinateList.size()-1==x?".":", ");
        return allSubordinates;
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
            person.setHealPoints(person.getHealPoints()/healthIndexDown);
            person.setAuthorityPoints(person.getAuthorityPoints() + 1);
        }
        else if (this.statusLevel < person.getStatusLevel()) {
            this.setHealPoints(this.getHealPoints()/healthIndexDown);
            this.authorityPoints++;
            person.setHealPoints(person.getHealPoints()*healthIndexUp);
            person.setAuthorityPoints(getAuthorityPoints() - 1);
        }
        setRank(person);
        setRank(this);
    }

    @Override
    public void doPresentPeasant(Human person) {
        if (this instanceof King && this.getMyPeasantsList().isEmpty())
            ((King) this).createPeasant();
        if (this.getMyPeasantsList().size() > 0) {
            this.getMyPeasantsList().get(0).setChief(person);
            person.getMyPeasantsList().add(this.getMyPeasantsList().get(0));
            person.getSubordinateList().add(this.getMyPeasantsList().get(0));
            System.out.println(this.getTitleAndName() + " presents " + this.getMyPeasantsList().get(0).getTitleAndName() + " to " + person.getTitleAndName() + ".");
            this.getSubordinateList().remove(this.getMyPeasantsList().get(0));
            this.getMyPeasantsList().remove(this.getMyPeasantsList().get(0));
        } else {
            System.out.println(this.getTitleAndName() + " doesnt present any peasant to " + person.getTitleAndName() + ", because he owns 0 peasants.");
        }
    }

    @Override
    public void doAction(Human person, String action) {
        System.out.println(this.getTitleAndName() + " " + action + " " + person.getTitleAndName() + ".");
        this.changeHpAndAuthorityLevel(person);
    }

    @Override
    public String toString() {
        return this.getTitleAndName() +
                " HP: " +
                this.healPoints +
                "Auth: " +
                this.authorityPoints + (this instanceof King?"":". My chief is " +
                this.chief.getTitleAndName()) +
                " Status: " +
                (this.getStatus() ? "Alive." : "Dead.");
                /*". My subordinate" +
                (this.subordinateList.size()>1?"s are:":" is:") +
                (this.subordinateList.size()==0?" nobody at the moment.":this.getStringOfSubordinateList());*/
    }
}