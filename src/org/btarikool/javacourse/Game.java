package org.btarikool.javacourse;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        Kingdom kingdom = new Kingdom("Far Far Away Kingdom");
        System.out.println("Creating persons:");
        King king = (King)kingdom.createHuman("Artur", "king");
        Lord lord = (Lord)kingdom.createHuman("Andrei", "lord");
        Lord lord2 = (Lord)kingdom.createHuman("Andrei", "lord");
        Lord lord3 = (Lord)kingdom.createHuman("Andrei", "lord");
        Knight knight1 = (Knight)kingdom.createHuman("Vasja", "knight");
        Enemy enemy = (Enemy)kingdom.createHuman("Vasja", "enemy");
        Enemy enemy2 = enemy.createNewEnemy("Marina");
        king.doPresentPeasant(lord2);
        king.doPresentPeasant(knight1);
        knight1.doPresentPeasant(king);
        System.out.println("Humans: " + kingdom.getHumanList().size());
        System.out.println("Kings: " + kingdom.getKingList().size());
        System.out.println("Lords: " + kingdom.getLordList().size());
        System.out.println("Knights: " + kingdom.getKnightList().size());
        System.out.println("Enemies: " + kingdom.getEnemyList().size());
        System.out.println("Peasants: " + kingdom.getPeasantList().size());
        System.out.println("Dead: " + kingdom.getDeadList().size());
        System.out.println();
        System.out.println("Lords Andrei Peasants: " + lord2.getMyPeasantsList().size());
        for (Peasant x : lord2.getMyPeasantsList()) System.out.println(x);
        System.out.println();
        System.out.println("Knight Vasja Peasants: " + knight1.getMyPeasantsList().size());
        for (Peasant x : knight1.getMyPeasantsList()) System.out.println(x);
        System.out.println();
        System.out.println("Kings Andrei Peasants: " + king.getMyPeasantsList().size());
        for (Peasant x : king.getMyPeasantsList()) System.out.println(x);



        Kingdom kingdom2 = new Kingdom("Far Far Away Kingdom2");
        King king2 = (King)kingdom2.createHuman("Artur", "king");
        Lord lord22 = (Lord)kingdom2.createHuman("Andrei", "lord");
        Lord lord222 = (Lord)kingdom2.createHuman("Andrei", "lord");
        Knight knight12 = (Knight)kingdom2.createHuman("Vasja", "knight");
        Enemy enemy222 = (Enemy)kingdom2.createHuman("Vasja", "enemy");
        Enemy enemy22 = enemy222.createNewEnemy("Marina");
        king2.doPresentPeasant(lord22);
        king2.doPresentPeasant(knight12);
        king2.doPresentPeasant(knight12);
        knight12.doPresentPeasant(king2);
        System.out.println("Humans: " + kingdom2.getHumanList().size());
        System.out.println("Kings: " + kingdom2.getKingList().size());
        System.out.println("Lords: " + kingdom2.getLordList().size());
        System.out.println("Knights: " + kingdom2.getKnightList().size());
        System.out.println("Enemies: " + kingdom2.getEnemyList().size());
        System.out.println("Peasants: " + kingdom2.getPeasantList().size());
        System.out.println("Dead: " + kingdom2.getDeadList().size());
        System.out.println();
        System.out.println("Lords Andrei Peasants: " + lord22.getMyPeasantsList().size());
        for (Peasant x : lord22.getMyPeasantsList()) System.out.println(x);
        System.out.println();
        System.out.println("Knight Vasja Peasants: " + knight12.getMyPeasantsList().size());
        for (Peasant x : knight12.getMyPeasantsList()) System.out.println(x);
        System.out.println();
        System.out.println("Kings Andrei Peasants: " + king2.getMyPeasantsList().size());
        for (Peasant x : king2.getMyPeasantsList()) System.out.println(x);
    }
}
