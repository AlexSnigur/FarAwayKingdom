package org.btarikool.javacourse;

public class Peasant extends Person{

    public static final String TITLE = "";

    public Peasant(String name) {
        super(name);
        this.title = TITLE;
        super.calcHealth(Settings.getPeasantsHealthInterval());
        super.calcPower(Settings.getPeasantsPowerInterval());
        this.actions = new String[][] {
                {"farm the land", "pay rent"},
                {null, null}
        };

    }

}