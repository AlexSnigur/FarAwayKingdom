package org.btarikool.javacourse;

public class Lord extends Person{
    private static final String TITLE = "Lord ";


    public Lord(String name) {
        super(name);
        this.title =  TITLE;
        super.calcHealth(Settings.getLordsHealthInterval());
        super.calcPower(Settings.getLordsPowerInterval());
        this.actions = new String[][] {
                {"loyalty", "military aid"},
                {"shelter", "protection"}
        };
    }

}
