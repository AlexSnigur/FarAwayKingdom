package org.btarikool.javacourse;

public class Peasant extends Hooman {

    private String title;

    public Peasant(String name, String title) {
        super(name, title);
        this.levelOfDominance = 4;
        this.title = "Peasant";
        this.health = 0.9;
        this.power = 2;
    }
}
