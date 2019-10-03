package org.btarikool.javacourse;

public class Game {


    public static void main(String[] args) {

        Kingdom kingdom1 = new Kingdom("Erebor");
        King kingErebor = (King) kingdom1.createHooman("Arthur", "King");
        Lord lordOne = (Lord) kingdom1.createHooman("James", "Lord", kingErebor);
        Lord lordTwo = (Lord) kingdom1.createHooman("John", "Lord", kingErebor);
        Wizard wizardOne = (Wizard) kingdom1.createHooman("Gendalf", "Wizard", kingErebor);
        Knight knightOne = (Knight) kingdom1.createHooman("Lee", "Knight", lordOne);
        Knight knightTwo = (Knight) kingdom1.createHooman("Victor", "knight", lordTwo);

        kingdom1.status();
        kingdom1.doActions(kingErebor, lordOne, lordTwo, knightOne, knightTwo);
        kingdom1.status();
    }

}

