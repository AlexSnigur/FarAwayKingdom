// King.java
package org.btarikool.javacourse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Person {
    private static final String TITLE = "";
    private static final Double HEALTH_CHANGE_COEFFICIENT = 0.98;
    public static int runCounter = 0;
    private static final double HEALTH_THRESHOLD = 0.2;
    private static final List<Person> DEAD_LIST = new ArrayList<>();

    static {

    }
    String name;
    int id;
    String title;
    private double health;
    int power;
    public boolean isDead;
    private double healthUpCoeff = 1.33;
    private double healthDownCoeff = 1.164993;
    Person chief;
    Person[] subordinates;
    String[][] actions;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public double getRank() {
        return this.health * this.power;
    }

    public static void copyPowerAndHealth(Person person1, Person person2) {
        boolean isFirstHigher = person1.getRank() > person2.getRank();
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

    public double getHealth(){
        return this.health;
    }

    public void setHealth(double health) {
        this.health = health;
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
        return (this.title.length() > 0 ? this.title + " " : "")
                + this.name
                + " (" + this.health
                + " - " + this.power + ")"
                + (isDead ? " is dead " : "" );
    }

}