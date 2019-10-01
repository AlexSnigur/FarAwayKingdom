package org.btarikool.javacourse;

public class Knight extends Person {
    private static final String TITLE = "Knight ";

    public Knight(String name) {
        super(name);
        this.title = TITLE;
        this.health = 0.7;
        this.power = 5;
        this.actions = new String[][]{
                {"homage", "military service"},
                {"food", "protection"}
        };

    }

}
