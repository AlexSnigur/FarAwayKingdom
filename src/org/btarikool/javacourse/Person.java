// King.java
package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    String name;
    String title;
    double health;
    int power;
    private static final String TITLE = "";
    private static double COEFFICIENT_UP = 1.33;
    private static double COEFFICIENT_DOWN = 1.164993;
    private static final double COEFFICIENT = 1;
    public static boolean IS_FIRST_ITERATION = true;
    public static double LOWER_HEALTH_BOUNDARY = 0.2;
    public static List<Person> DEAD_PEOPLE = new ArrayList<Person>();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String getTitleAndName() {
        return title + name;
    }

    public void doAction(String actionContent, boolean isUpwards) throws Exception {
        if (!IS_FIRST_ITERATION) {
            COEFFICIENT_UP /= COEFFICIENT;
            COEFFICIENT_DOWN *= COEFFICIENT;
        }
        Action action = new Action();
        if (isUpwards) {
            health *= COEFFICIENT_UP;
            power--;
        } else {
            health /= COEFFICIENT_DOWN;
            power++;
        }
//        if (health < LOWER_HEALTH_BOUNDARY) {
//            throw new Exception("Game over!");
//        }
        action.doAction(this.getTitleAndName(), actionContent);
        if (health < LOWER_HEALTH_BOUNDARY) {
            if (!DEAD_PEOPLE.contains(this))
                DEAD_PEOPLE.add(this);
        }
    }

    public void doAction(String actionContent) throws Exception {
        doAction(actionContent, false);
    }

    public void report() {
        System.out.println(getTitleAndName() + "'s health: " + health + "; power: " + power);
    }

    public void sayHello(String name) {
        System.out.println("Hello! I am the " + getTitleAndName());

    }

    public static double getRank(double power, double health) {
        return power * health;
    }

    public static void copyPowerHealth(Person p1, Person p2) {
        double rankP1 = getRank(p1.power, p1.health);
        double rankP2 = getRank(p2.power, p2.health);
        if (rankP1 > rankP2) {
            p2.power = p1.power;
            p2.health = p1.health;
        } else {
            p1.power = p2.power;
            p1.health = p2.health;
        }
    }

    public static void printDeadPeople() {
        System.out.println("List of Dead People:");
        for (Person deadPerson : DEAD_PEOPLE) {
            System.out.println(deadPerson.getTitleAndName());
        }
    }

}