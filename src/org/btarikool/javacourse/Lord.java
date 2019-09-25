package org.btarikool.javacourse;

public class Lord extends Hooman {

    private String title;

    public Lord(String name, String title) {
        super(name, title);
        this.levelOfDominance = 2;
        this.title = "Lord";
        this.health = 0.7;
        this.power = 5;
        System.out.println("Lord " + name + " is created.");
    }
}
