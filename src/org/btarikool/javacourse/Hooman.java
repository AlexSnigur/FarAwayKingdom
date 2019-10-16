package org.btarikool.javacourse;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Hooman {
    private static double healthUp = 1.3;
    private static double healthDown = 1.164993;
    public static final double HEALTH_MIN = 0.2;
    int levelOfDominance;
    int idNumber;
    String title;
    double health;
    double rank;
    double power;
    private String name;
    private boolean isAlive;
    private Hooman chief;
    private boolean checkEven;
    private List<Hooman> subordinateList = new ArrayList<>();
    private List<Hooman> listOfPeasants = new ArrayList<>();


    public Hooman(String name, int idNumber) {
        this.name = name;
        this.idNumber = idNumber;
        isAlive = true;
        if (idNumber % 2 == 0) {
            this.checkEven = true;
        } else {
            this.checkEven = false;
        }
    }

    public boolean checkRankDifference(Hooman hooman1) {
        if ((this.getRank() - hooman1.getRank()) >= 0.5) {
            return true;
        } else {
            return false;
        }
    }

    public void setChief(Hooman chief) {
        this.chief = chief;

    }

    public String getTitle() {
        return title;
    }

    public double getRank() {
        return this.rank;
    }

    public void copyHealthAndPower(Hooman hooman1, Hooman hooman2) {
        if (hooman1.getRank() > hooman2.getRank()) {
            hooman2.health = hooman1.health;
            hooman2.power = hooman1.power;
        } else if (hooman1.getRank() < hooman2.getRank()) {
            hooman1.health = hooman2.health;
            hooman1.power = hooman2.power;
        }
    }

    public static double getHealthMin() {
        return HEALTH_MIN;
    }

    public List<Hooman> getSubordinateList() {
        return subordinateList;
    }

    public List<Hooman> getListOfPeasants() {
        return listOfPeasants;
    }

    public String getSubordinateListString() {
        String forReturn = "";
        for (Hooman h : this.subordinateList) {
            forReturn += h.getNameAndTitle() + (checkIfLastInList(h)? "." : ", ");
        }
        if (forReturn.isEmpty()) {
            String emptyString = "none.";
            return emptyString;
        }
        return forReturn;
    }

    public boolean checkIfLastInList(Hooman h){
        return (this.subordinateList.indexOf(h)==this.subordinateList.size()-1);
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public boolean isCheckEven() {
        return this.checkEven;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getNameAndTitle() {
        return this.title + " " + this.name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {

        this.health = health;
    }

    public double getPower() {
        return this.power;
    }

    public String getName() {
        return name;
    }

    public Hooman getChief() {
        return this.chief;
    }

    public String protect(List<Hooman> list) {
        String s = "";
        for (Hooman h : list) {
            this.changeStatus(h);
            s += h.getNameAndTitle() + (checkIfLastInList(h)? "." : ", ");
        }
        return " protects " + s;
    }

    public String greetings(Hooman human) {
        this.changeStatus(human);
        return ": 'Greetings, " + human.getNameAndTitle()+"!'";
    }

    public String shelter(List<Hooman> list) {
        String s = "";
        for (Hooman h : list) {
            this.changeStatus(h);
            s += h.getNameAndTitle() + (checkIfLastInList(h)? "." : ", ");
        }
        return " gives shelter to " + s;
    }

    public String fief(List<Hooman> list) {
        String s = "";
        for (Hooman h : list) {
            this.changeStatus(h);
            s += h.getNameAndTitle() + (checkIfLastInList(h)? "." : ", ");
        }
        return " gives fief to " + s;
    }

    public String giveFood(List<Hooman> list) {
        String s = "";
        for (Hooman h : list) {
            this.changeStatus(h);
            s += h.getNameAndTitle() + (checkIfLastInList(h)? "." : ", ");
        }
        return " gives food to " + s;
    }

    public String militaryService(Hooman human) {
        this.changeStatus(human);
        return " provides military service to " + human.getNameAndTitle();
    }

    public String militaryAid(Hooman human) {
        this.changeStatus(human);
        return " provides military aid to "+ human.getNameAndTitle();
    }

    public String loyalty(Hooman human) {
        this.changeStatus(human);
        return " gives loyalty to "+ human.getNameAndTitle();
    }


    public String farmLand(Hooman human) {
        this.changeStatus(human);
        return " farms land for "+ human.getNameAndTitle();
    }

    public String paysRent(Hooman human) {
        this.changeStatus(human);
        return " pays rent to "+ human.getNameAndTitle();
    }

    //coefficient for King power 20. Not used further than task 1
    public static void changeHpLoss(double x) {
        healthUp *= x;
        healthDown *= x;
    }

    public void changeStatus(Hooman human) {
        if (this.levelOfDominance > human.levelOfDominance) {
            this.health *= healthUp;
            this.power--;
        } else if (this.levelOfDominance < human.levelOfDominance) {
            this.health /= healthDown;
            this.power++;
        }
    }

    @Override
    public String toString() {

        double round = (double) Math.round(this.getHealth()*100.0)/100.0;
        double round1 = (double) Math.round(this.getPower()*100.0)/100.0;
        return this.title + " " + this.name + " [h: " + round+ ", p: " + round1+"]";

    }
}
