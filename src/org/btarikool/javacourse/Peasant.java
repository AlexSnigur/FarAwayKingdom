package org.btarikool.javacourse;

public class Peasant extends Hooman {
    private final static String TITLE = "Peasant";


    public Peasant(String name, int idNumber) {
        super(name, idNumber);
        this.levelOfDominance = 4;
        this.title = TITLE;
        this.health = 0.9;
        this.power = 2;
    }
}
