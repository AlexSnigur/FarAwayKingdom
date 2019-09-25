package org.btarikool.javacourse;

public class Knight extends Hooman {
private final static String TITLE = "Knight";


    public Knight(String name) {

        super(name);
        this.levelOfDominance = 3;
        this.title = TITLE;
        this.health = 0.7;
        this.power = 5;

        System.out.println("Knight " + name + " is created");

    }
}
