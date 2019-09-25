package org.btarikool.javacourse;

public class Lord extends Hooman {

    private final static String TITLE = "Lord";

    public Lord(String name) {
        super(name);
        this.levelOfDominance = 2;
        this.title = TITLE;
        this.health = 0.7;
        this.power = 5;
        System.out.println("Lord " + name + " is created.");
    }
}
