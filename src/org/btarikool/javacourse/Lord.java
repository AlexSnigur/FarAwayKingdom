package org.btarikool.javacourse;

public class Lord extends Person{
    private static final String TITLE = "Lord ";

    private static String[] upActionContent = {"loyalty", "military aid"};
    private static String[] downActionContent = {"shelter", "protection"};

    public Lord(String name,  Person chief, Kingdom kingdom) {
        super(name, chief, kingdom);
        this.title =  TITLE;
        this.health = 0.7;
        this.power = 5;
        this.actionContent = this.kingdom.persons.length % 2 == 0 ? upActionContent : downActionContent;

    }

}
