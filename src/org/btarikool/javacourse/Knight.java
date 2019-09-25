package org.btarikool.javacourse;

public class Knight extends Hooman {

    private String title;

    public Knight(String name, String title) {

        super(name, title);
        this.levelOfDominance = 3;
        this.title = "Knight";
        this.health = 0.7;
        this.power = 5;

        System.out.println("Knight " + name + " is created");

    }
}
