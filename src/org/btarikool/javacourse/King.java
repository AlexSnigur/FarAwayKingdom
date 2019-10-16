// King.java
package org.btarikool.javacourse;

public class King extends Person {
    int peasants = 10;
    private static String TITLE = "King ";


    public King(String name) {
        super(name);
        this.title = TITLE;
        this.setHealth(0.7);
        this.power = 5;
        super.calcPower(Settings.getKnightsPowerInterval());
        super.calcHelth(Settings.getKnightsHealthInterval());
        this.actions = new String[][]{
                {"homage", "military service"},
                {"food", "protection"}
        };
    }

    public Peasant providePeasant(String masterName) {
        peasants--;
        return new Peasant(masterName + "'s peasant #" + (10 - peasants));
    }

    public void providePeasantToSubordinates(Kingdom kingdom) {
        for (Person lord : this.subordinates) {
            if (!(lord instanceof Lord)) {
                continue;
            }
            createPeasantWithName(lord, kingdom);
        }
    }
    public Person createPeasantWithName(Person p, Kingdom k) {
        String name = p.getTitleAndName() + "'s peasant";
        return k.createPerson(name, "", p);
    }
}