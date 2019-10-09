package org.btarikool.javacourse;

public class Lord extends Hooman {

    private final static String TITLE = "Lord";

    public Lord(String name, int idNumber, Hooman chief) {
        super(name, idNumber, chief);
        this.levelOfDominance = 2;
        this.title = TITLE;
        this.health = 0.7;
        this.power = 5;
        this.rank = this.health * this.power;
    }
}
