package org.btarikool.javacourse;

public class Peasant extends Person{

    public static final String TITLE = "";

    public Peasant(String name, Person chief, Kingdom kingdom) {
        super(name, chief, kingdom);
        this.title = TITLE;
        this.health = 0.9;
        this.power = 2;

    }

}
