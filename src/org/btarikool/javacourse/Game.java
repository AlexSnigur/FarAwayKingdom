package org.btarikool.javacourse;

public class Game {
    public static void main(String[] args) {
        War war = new War();
        Kingdom kingdom1 = new Kingdom("kingdom1");
        Kingdom kingdom2 = new Kingdom("kingdom2");
        war.fight(kingdom1, kingdom2);
    }
}

