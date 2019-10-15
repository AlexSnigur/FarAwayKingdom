package org.btarikool.javacourse;

import com.sun.scenario.Settings;

public class Peasant extends Person{

    public static final String TITLE = "";

    public Peasant(String name) {
        super(name);
        this.title = TITLE;
        this.setHealth(0.9);
        super.calcPower(Settings.getPeasantsPowerInterval());
        this.actions = new String[][] {
                {"farm the land", "pay rent"},
                {null, null}
        }

    }

}
