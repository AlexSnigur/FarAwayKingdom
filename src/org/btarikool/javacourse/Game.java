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

        Enemy enemyOne = new Enemy("one", "I am ", new StringBuffer(),kingdom1.hoomansList.size());
        Enemy enemyTwo = enemyOne.createEnemy("two", "the ");
        Enemy enemyThree = enemyTwo.createEnemy("three", "King");
        System.out.println(enemyThree);
        enemyThree.decodeGroups();
        enemyThree.decodeReverse();

        kingdom1.status();
        for (int i = 1; i <9; i++) {
            wizardOne.healKing(kingdom1);
        }
        kingdom1.doActions(kingErebor, lordOne, lordTwo, knightOne, knightTwo, kingdom1);
        kingdom1.status();

        kingdom1.fight();

        kingdom1.saveKingdomState();
    }

}

