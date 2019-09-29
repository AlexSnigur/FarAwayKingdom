// King.java
package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Person {
    String name;
    String title;
    private Person chief;
    Person[] subordinates;
    Kingdom kingdom;
    double health;
    int power;
    private static final Double HEALTH_CHANGE_COEFFICIENT = 0.98;
    public static int runCounter = 0;
    private double healthUpCoeff = 1.33;
    private double healthDownCoeff = 1.164993;
    private static final double HEALTH_THRESHOLD = 0.2;
    private static final List<Person> DEAD_LIST = new ArrayList<>();
    String[] actionContent = new String[2];


    public Person() {}

    public Person(String name, Person chief, Kingdom kingdom) {
        this.name = name;
        this.chief = chief;
        if (this.chief != null) {
            this.chief.addSubordinate(this);
        }
        this.kingdom = kingdom;
    }

    public void addSubordinate(Person subordinate) {
        if (this.subordinates == null) {
            this.subordinates = new Person[1];
        } else {
            this.subordinates = Arrays.copyOf(subordinates, this.subordinates.length + 1);
        }
        this.subordinates[this.subordinates.length - 1] = subordinate;
    }

    public static double getRank(Person person) {
        return person.health * person.power;
    }

    public static void copyPowerAndHealth(Person person1, Person person2) {
        boolean isFirstHigher = getRank(person1) > getRank(person2);
        if(isFirstHigher) {
            person2.power = person1.power;
            person2.health = person1.health;
        } else {
            person1.power = person2.power;
            person1.health = person2.health;
        }
    }

    public String getTitleAndName()  {
        return title + name;
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
        int upIndex = isUpwards ? 1 : 0;
        String content = this.actionContent[upIndex];
        action.doAction(this.getTitleAndName(), content);
        if (health < HEALTH_THRESHOLD) {
            DEAD_LIST.add(this);
        }

    }

    public String getTitle() {
        return this.title;
    }


    public void doAction(String actionContent) throws Exception{
        doAction(actionContent, false);
    }

    public void report() {
        System.out.println(getTitleAndName() + "'s health: " + health + "; power: " + power);
    }

    @Override
    public String toString() {
        return this.title + this.name;
    }


}