package org.btarikool.javacourse;

public class Knight extends Person {
    private static final String TITLE = "Knight ";
    private static String[] upActionContent = {"hommage", "military service"};
    private static String[] downActionContent = {"food", "protection"};

    public Knight(String name, Person chief, Kingdom kingdom) {
        super(name, chief, kingdom);
        this.title = TITLE;
        this.health = 0.7;
        this.power = 5;
        this.actionContent = this.kingdom.persons.length % 2 == 0 ? upActionContent : downActionContent;

    }

}
