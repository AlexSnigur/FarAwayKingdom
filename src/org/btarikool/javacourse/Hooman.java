package org.btarikool.javacourse;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.List;

public abstract class Hooman {
    String name;
    String title;
    double health;
    private static double healthUp = 1.3;
    private static double healthDown = 1.164993;
    int power;
    int levelOfDominance;
    List<Hooman> hoomans = new ArrayList<>();

    public Hooman(String name, String title) {
        this.name = name;
        this.title = title;

    }

    public List<Hooman> getHoomans() {
        return hoomans;
    }

    public int getLevelOfDominance() {
        return levelOfDominance;
    }

    public String getNameAndTitle() {
        return this.title + " " + this.name;
    }

    public double getHealth() {
        return health;
    }


    public int getPower() {
        return power;
    }


    public String protect(Hooman human) {
        this.changeStatus(human);

        return " protects ";
    }

    public String greetings(Hooman human) {
        this.changeStatus(human);

        return "Greetings, ";
    }

    public String giveFood(Hooman human) {
        this.changeStatus(human);
        return " gives food to ";

    }

    public String payRent(Hooman human) {
        this.changeStatus(human);
        return " pays rent to";
    }

    public String conquerLand(Hooman human) {
        this.changeStatus(human);
        return " conquers land for ";
    }

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

    public static void healthCheck(Hooman hooman) {
        if (hooman.health < 0.2) {

        }
    }

    public void status() {
        System.out.println(this.getNameAndTitle() + ", Health status: " + this.health + ", Power status: " + this.power);

    }
}
