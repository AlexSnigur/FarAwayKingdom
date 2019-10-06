package org.btarikool.javacourse;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Hooman {
    String name;
    String title;
    double health;
    private static double healthUp = 1.3;
    private static double healthDown = 1.164993;
    int power;
    int levelOfDominance;
    int idNumber;
    boolean isAlive;
     Hooman chief;
    private boolean checkEven;
    private List<Hooman> subordinateList = new ArrayList<>();



    public Hooman(String name, int idNumber, Hooman chief) {
        this.name = name;
        this.idNumber = idNumber;
        this.chief = chief;
        isAlive = true;
        if (idNumber % 2 == 0) {
            this.checkEven = true;
        } else {
            this.checkEven = false;
        }
        if (!(this instanceof King || this instanceof Wizard)) {
            this.chief.getSubordinateList().add(this);
        }
    }

    public void setChief(Hooman chief) {
        this.chief = chief;
    }


    public static double rank(Hooman hooman) {
        return hooman.power * hooman.health;
    }


    public static void copyHealthAndPower(Hooman hooman1, Hooman hooman2) {
        if (rank(hooman1) > rank(hooman2)) {
            hooman2.health = hooman1.health;
            hooman2.power = hooman1.power;
        } else if (rank(hooman1) < rank(hooman2)) {
            hooman1.health = hooman2.health;
            hooman1.power = hooman2.power;
        }
    }

    public List<Hooman> getSubordinateList() {
        return subordinateList;
    }

    public String getSubordinateListString() {
        String forReturn = "";
        for (Hooman h : this.subordinateList) {
            forReturn += h.getNameAndTitle() + " ";
        }
        if (forReturn.isEmpty()) {
            String emptyString = "none ";
            return emptyString;
        }
        return forReturn;
    }

    public void setCheckEven(boolean checkEven) {
        this.checkEven = checkEven;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setPower(int power) {
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

    public int getPower() {
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
            s += h.getNameAndTitle() +" ";
        }
        return " protects " +s;
    }

    public String greetings(Hooman human) {
        this.changeStatus(human);
        return " Greetings, ";
    }

    public String shelter(List<Hooman> list) {
        String s = "";
        for (Hooman h : list) {
            this.changeStatus(h);
            s += h.getNameAndTitle() +" ";
        }
        return " gives shelter to " + s;
    }
    public String fief(List<Hooman> list) {
        String s = "";
        for (Hooman h : list) {
            this.changeStatus(h);
            s += h.getNameAndTitle() +" ";
        }
        return " gives fief to " + s;
    }

    public String giveFood(List<Hooman> list) {
        String s = "";
        for (Hooman h : list) {
            this.changeStatus(h);
            s += h.getNameAndTitle() +" ";
        }
        return " gives food to " + s;
    }

    public String militaryService(Hooman human) {
        this.changeStatus(human);
        return " provides military service to ";
    }

    public String militaryAid(Hooman human) {
        this.changeStatus(human);
        return " provides military aid to ";
    }

    public String loyalty(Hooman human) {
        this.changeStatus(human);
        return " gives loyalty to ";
    }


    public String farmLand(Hooman human) {
        this.changeStatus(human);
        return " farms land for ";
    }

    public String paysRent(Hooman human) {
        this.changeStatus(human);
        return " pays rent to ";
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
        return this.title + " " + this.name;

    }
}
