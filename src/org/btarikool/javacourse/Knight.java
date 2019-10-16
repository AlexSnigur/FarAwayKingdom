package org.btarikool.javacourse;

public class Knight extends Person {
    private static final String TITLE = "Knight ";

    public Knight(String name) {
        super(name);
        this.title = TITLE;
        this.setHealth(0.5);
     super.calcPower(Settings.getKnightsPowerInterval());
     super.calcHelth(Settings.getKnightsHealthInterval());
        this.actions = new String[][]{
                {"homage", "military service"},
                {"food", "protection"}
        };

    }

}
