// King.java
package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    String name;
    String title;
    double health;
    int power;
    private static final double HEALTH_CHANGE_COEFFICIENT = 0.98;
    public static int runCounter = 0;
    private double healthUpCoeff = 1.33;
    private double healthDownCoeff = 1.164993;





    public Person(String name) {
        this.name = name;
    }

    public String getTitleAndName()  {
        return title + name;
    }
    public static double getRank(Person person){
       return person.health * person.power;
    }
    public static void copyPowerAndHealth(Person person1, Person person2) {
        boolean isFirstHigher = getRank(person1) > getRank(person2);
        if(isFirstHigher){
            person2.power =person1.power;
            person2.health = person1.health;
        }
        else{
            person1.power = person2.power;
            person1.health = person2.health;
        }
    }

    public void doAction(String actionContent, boolean isUpwards) throws Exception {
        Action action = new Action();
        if (Person.runCounter > 0) {
            healthUpCoeff /= HEALTH_CHANGE_COEFFICIENT;
            healthDownCoeff *= HEALTH_CHANGE_COEFFICIENT;
        }
        if (isUpwards) {
            health *= healthUpCoeff;
            power--;
        } else {
            health /= healthDownCoeff;
            power++;
        }
        action.doAction(this.getTitleAndName(), actionContent);
        if (health < 0.2) {
            DEADMANLIST.add(this);
        }
    }
    public void doAction(String actionContent) throws Exception{
        doAction(actionContent, false);
    }

    public void report() {
        System.out.println(getTitleAndName() + "'s health: " + health + "; power: " + power);
    }
    private static final double HEALTH_MIN = 0.2;
    private static final List<Person> DEADMANLIST = new ArrayList<>();
    }


