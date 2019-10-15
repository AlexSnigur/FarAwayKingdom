package org.btarikool.javacourse;

import com.sun.scenario.Settings;

public class Knight extends Person {
    private static final String TITLE = "Knight ";

    public Knight(String name) {
        super(name);
        this.title = TITLE;
        this.setHealth(0.7);
        super.calcPower(Settings.getKnightsPowerInterval());
        this.actions = new String[][]{
                {"homage", "military service"},
                {"food", "protection"}
        }

    }

}
