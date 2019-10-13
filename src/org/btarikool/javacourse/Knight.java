package org.btarikool.javacourse;

public class Knight extends Human {
    private final static String TITLE = "Knight";
    private final static int STATUS_LEVEL = 3;
    private static String[][] phrase = new String[][]{
        {"homage", "military service"},
        {"food", "protection"}
    };

    public Knight(String name, String title, int statusLevel, String[][] phrase, int collectiveListId, int ownListId, Kingdom kingdom, Human chief) {
        super(name, title, statusLevel, phrase, collectiveListId, ownListId, kingdom, chief);
    }

    public Knight(String name, int idFromCollectiveList, int idFromOwnList, Kingdom kingdom, Human chief) {
        super(name, TITLE, STATUS_LEVEL, phrase, idFromCollectiveList, idFromOwnList, kingdom, chief);
    }

}