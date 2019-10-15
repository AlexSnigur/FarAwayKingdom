package org.btarikool.javacourse;

public class Knight extends Person {
    private static final String TITLE = "Knight ";

    public Knight(String name) {
        super(name);
        this.title = TITLE;
        super.calcPower(Settings.getKnightsPowerInterval());
        super.calcHealth(Settings.getKnightsHealthInterval());
        this.actions = new String[][]{
                {"homage", "military service"},
                {"food", "protection"}
        };

    }

}
