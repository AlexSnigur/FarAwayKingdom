// King.java
package org.btarikool.javacourse;

public class King extends Person{
    int peasants = 10;
    private static final String TITLE = "King ";


    public King(String name) {
        super(name);
        this.title = "King ";
        this.health = 0.5;
        this.power = 10;

    }

    public Peasant providePeasant(String masterName) {
        peasants--;
        return new Peasant(masterName + "'s peasant #" + (10-peasants));
    }

}