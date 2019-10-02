package org.btarikool.javacourse;

public class Lord extends Person{
    private static final String TITLE = "Lord ";


    public Lord(String name) {
        super(name);
        this.title =  TITLE;
        this.setHealth(0.7);
        this.power = 5;
        this.actions = new String[][] {
                {"loyalty", "military aid"},
                {"shelter", "protection"}
        };
    }

}
