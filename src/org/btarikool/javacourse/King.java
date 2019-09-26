// King.java
package org.btarikool.javacourse;

public class King extends Person{
    int peasants = 10;
    private static String TITLE = "King ";
    public static class Sword {
        public int lenth;
        public double weight;
    }

    public King(String name) {
        super(name);
        this.title = TITLE;
        this.health = 0.5;
        this.power = 10;

    }

    public Peasant providePeasant(String masterName) {
        peasants--;
        return new Peasant(masterName + "'s peasant #" + (10-peasants));
    }

}