// King.java
package org.btarikool.javacourse;

public class King extends Person{
    int peasants = 10;
    private static String TITLE = "King ";
    public static class Sword {
        public int lenth;
        public double weight;
    }

    public King(String name,  Person chief, Kingdom kingdom) {
        super(name, chief, kingdom);
        this.title = TITLE;
        this.health = 0.5;
        this.power = 10;

    }

    public void providePeasant(Person master) {
        peasants--;
        Peasant peasant = new Peasant(master.getTitleAndName() + "'s peasant #" + (10-peasants), master, this.kingdom);
        master.addSubordinate(peasant);
    }

    public void givePeasantToLord(int generalIndex) {
        int index = generalIndex % 2 == 0 ? 0 : 1;
        if (this.subordinates.length <= index) {
            return;
        }
        providePeasant(this.subordinates[index]);
    }

}