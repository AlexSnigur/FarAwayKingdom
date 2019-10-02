package org.btarikool.javacourse;

public class Lord extends Hooman {

    private final static String TITLE = "Lord";

    public Lord(String name, int idNumber) {
        super(name, idNumber);
        this.levelOfDominance = 2;
        this.title = TITLE;
        this.health = 0.7;
        this.power = 5;
    }
}
