package org.btarikool.javacourse;

public class Game {


    public static void main(String[] args) {

        Kingdom kingdom1 = new Kingdom("Erebor");
        King kingErebor = (King) kingdom1.createHooman("Arthur", "King");
        Lord lordOne = (Lord) kingdom1.createHooman("James", "Lord", kingErebor);
        Lord lordTwo = (Lord) kingdom1.createHooman("John", "Lord", kingErebor);
        Knight knightOne = (Knight) kingdom1.createHooman("Lee", "Knight", lordOne);
        Knight knightTwo = (Knight) kingdom1.createHooman("Victor", "knight", lordTwo);

        kingdom1.status();

        System.out.println("\n");

        kingdom1.doActions(kingErebor, lordOne, lordTwo, knightOne, knightTwo);
        System.out.println("\n");


        kingdom1.status();
    }

}

