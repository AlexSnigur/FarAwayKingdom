package org.btarikool.javacourse;

public class Knight extends Hooman {
private final static String TITLE = "Knight";


    public Knight(String name, int idNumber) {
        super(name, idNumber);
        this.levelOfDominance = 3;
        this.title = TITLE;
        this.health = 0.7;
        this.power = 5;
        this.rank = this.health * this.power;
    }
}
