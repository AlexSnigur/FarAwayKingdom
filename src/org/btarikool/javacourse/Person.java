// King.java
package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Person {
    String name;
    int id;
    String title;
    double health;
    int power;
    private static final String TITLE = "";
    private static final Double HEALTH_CHANGE_COEFFICIENT = 0.98;
    public static int runCounter = 0;
    private double healthUpCoeff = 1.33;
    private double healthDownCoeff = 1.164993;
    private static final double HEALTH_THRESHOLD = 0.2;
    private static final List<Person> DEAD_LIST = new ArrayList<>();
    Person chief;
    Person[] subordinates;
    String[][] actions;

    public Person() {}

    public Person(String name) {
        this.name = name;
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

    public void setChief(Person chief) {
        this.chief = chief;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitleAndName()  {
        return title + name;
    }


    public void doAction() {
        doAction(null);
    }


    public void doAction(Person subordinate) {
        Action action = new Action();
        if (Person.runCounter > 0) {
            healthUpCoeff /= HEALTH_CHANGE_COEFFICIENT;
            healthDownCoeff *= HEALTH_CHANGE_COEFFICIENT;
        }
        if (subordinate == null) {
            health *= healthUpCoeff;
            power--;
        } else {
            health /= healthDownCoeff;
            power++;
        }
        action.doAction(this, subordinate);
        if (health < HEALTH_THRESHOLD) {
            DEAD_LIST.add(this);
        }

    }
    public void addSubordinate(Person p) {
        if (this.subordinates == null) {
            this.subordinates = new Person[] {p};
        } else {
            int nextIndex = this.subordinates.length;
            this.subordinates = Arrays.copyOf(this.subordinates, nextIndex + 1);
            this.subordinates[nextIndex] = p;
        }
    }

    public void report() {
        System.out.println(getTitleAndName() + "'s health: " + health + "; power: " + power);
    }

    public String printSubordinates() {
        if (this.subordinates == null) {
            return "";
        }
        String ret = Arrays.stream(this.subordinates)
                .filter(Objects::nonNull)
                .map(Person::getTitleAndName)
                .collect(Collectors.joining(", "));
        return " [" + ret + "]";
    }

    @Override
    public String toString() {
        return this.title
                + " " + this.name
                + " (" + this.health
                + " - " + this.power + ")"
                + this.printSubordinates()
                + (this.chief != null ? " CHIEF: " +  this.chief : "")
                ;
    }

}