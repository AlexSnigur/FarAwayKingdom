package org.btarikool.javacourse;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.List;

public abstract class Hooman {
    private String name;
    public String title;
    public double health;
    private static double healthUp = 1.3;
    private static double healthDown = 1.164993;
    public final double HEALTH_MIN = 0.2;
    public int power;
    public int levelOfDominance;
    public static List<Hooman> hoomans = new ArrayList<>();
    public static List<Hooman> deadList = new ArrayList<>();



    public Hooman(String name) {
        this.name = name;

        hoomans.add(this);

    }

    public static double rank(Hooman hooman){
       return hooman.power*hooman.health;
    }

    public static void copyHealthAndPower(Hooman hooman1, Hooman hooman2){
        if (rank(hooman1)>rank(hooman2)){
            hooman2.health = hooman1.health;
            hooman2.power = hooman1.power;
        } else if(rank(hooman1)<rank(hooman2)){
            hooman1.health=hooman2.health;
            hooman1.power=hooman2.power;
        }
    }

    public static void addToDeadList(Hooman hooman){
        deadList.add(hooman);
        hoomans.remove(hooman);
    }

    public static void getDeadList() {
        for (Hooman check : deadList){
            System.out.println(check);
        }
    }

    public static List<Hooman> getHoomans() {
        return hoomans;
    }


    public String getNameAndTitle() {
        return this.title + " " + this.name;
    }

    public double getHealth() {
        return health;
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
    @Override
    public String toString() {
        return title + name;
    }
}
