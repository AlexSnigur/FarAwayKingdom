package org.btarikool.javacourse;

public class Wizard extends Person {
    private static final String TITLE = "Knight ";


    public Wizard(String name,  Person chief, Person subordinate, Kingdom kingdom) {
        super(name, chief, kingdom);
        this.title = TITLE;
        this.health = 0.7;
        this.power = 5;

    }

}
