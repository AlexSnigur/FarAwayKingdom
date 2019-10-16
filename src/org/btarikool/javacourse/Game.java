package org.btarikool.javacourse;

import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException {
        Kingdom kingdom = new Kingdom("Estonia");
        Kingdom kingdom2 = new Kingdom("England");
        System.out.println("\n");
        Championship.startChamp();
        Settings.writeLog();
    }
}
