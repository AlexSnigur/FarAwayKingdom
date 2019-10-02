package org.btarikool.javacourse;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Hooman {
    public String name;
    public String title;
    public double health;
    private static double healthUp = 1.3;
    private static double healthDown = 1.164993;
    public int power;
    public int levelOfDominance;
    public int idNumber;
    private Hooman chief;
    private Hooman subordinate;
    public boolean checkEven;


    public Hooman(String name, int idNumber) {
        this.name = name;
        this.idNumber = idNumber;
        if (idNumber % 2 == 0) {
            this.checkEven = true;
        } else {
            this.checkEven = false;
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

    public int getPower() {
        return this.power;
    }

    public String getName() {
        return name;
    }

    public Hooman getChief() {
        return this.chief;
    }

    public String protect(Hooman human) {
        this.changeStatus(human);
        return " protects ";
    }

    public String greetings(Hooman human) {
        this.changeStatus(human);
        return " Greetings, ";
    }

    public String shelter(Hooman human) {
        this.changeStatus(human);
        return " gives shelter to ";
    }

    public String giveFood(Hooman human) {
        this.changeStatus(human);
        return " gives food to ";
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
