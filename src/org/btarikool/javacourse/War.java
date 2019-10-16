package org.btarikool.javacourse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class War {

    private List<Hooman> knightList1 = new ArrayList<Hooman>();
    private List<Hooman> knightList2 = new ArrayList<Hooman>();
    private String log = "";
    private String winner;


    public Hooman pickRandomKnight(List<Hooman> list) {
        Random r = new Random();
        Hooman hooman = list.get(r.nextInt(list.size()));
        return hooman;
    }

    public void fight(Kingdom k1, Kingdom k2) {
        knightList1.addAll(k1.knightList);
        knightList2.addAll(k2.knightList);
        while (!(knightList1.size() == 0) && !(knightList2.size() == 0)) {
            Hooman kn1 = pickRandomKnight(knightList1);
            Hooman kn2 = pickRandomKnight(knightList2);
            System.out.println("\nFight between " + kn1 + " and " + kn2 + " is started!");
            if (kn1.checkRankDifference(kn2) || kn1.getPower() > kn2.getPower()) {
                kn1.setHealth(kn1.getHealth() - (kn2.getHealth() / 2));
                if (kn1.getHealth() < Hooman.getHealthMin()) {
                    kn1.setHealth(Hooman.getHealthMin());
                }
                kn1.setPower(kn1.getPower() + (kn2.getPower() / 2));
                knightList2.remove(kn2);
                System.out.println(kn1.toString() + " wins the fight!");
            } else if (kn2.checkRankDifference(kn1) || kn2.getPower() > kn1.getPower()) {
                kn2.setHealth(kn2.getHealth() - (kn1.getHealth() / 2));
                if (kn2.getHealth() < Hooman.getHealthMin()) {
                    kn2.setHealth(Hooman.getHealthMin());
                }
                kn2.setPower(kn2.getPower() + (kn1.getPower() / 2));
                knightList1.remove(kn1);
                System.out.println(kn2.toString() + " wins the fight!");
            }
        }
        if (knightList1.isEmpty()) {
            System.out.println("\n" + k2.getName() + " wins the fight");
            winner = "\n<--------------------The winner is " + k2.getName() + "!!!-------------------->";
        } else {
            System.out.println("\n" + k1.getName() + " wins the fight");
            winner = "\n<--------------------The winner is " + k1.getName() + "!!!-------------------->";
        }
        saveKingdomState(k1);
        saveKingdomState(k2);
        writeToLog();
    }

    public void saveKingdomState(Kingdom k) {
        for (Hooman h : k.hoomansList) {
            if (h instanceof King) {
                log += (h.toString() + "\n");
                log += saveKingdomHelper(h) + "\n";
            }
        }
    }

    public String saveKingdomHelper(Hooman h) {
        String forReturn = "";
        for (int i = 0; i < h.getSubordinateList().size(); i++) {
            if (!h.getSubordinateList().get(i).isAlive()) {
                forReturn += "\u2620" + "\t" + h.getSubordinateList().get(i) + "\n";
                for (int x = 0; x < h.getSubordinateList().get(i).getSubordinateList().size(); x++) {
                    Hooman hooman = h.getSubordinateList().get(i).getSubordinateList().get(x);
                    forReturn += (hooman.isAlive() ? "\t\t" : "\u2620" + "\t\t") + hooman + "\n";
                }
            } else {
                forReturn += "\t" + h.getSubordinateList().get(i) + "\n";
                for (int x = 0; x < h.getSubordinateList().get(i).getSubordinateList().size(); x++) {
                    Hooman hooman = h.getSubordinateList().get(i).getSubordinateList().get(x);
                    forReturn += (hooman.isAlive() ? "\t\t" : "\u2620" + "\t\t") + hooman + "\n";
                }
            }
        }
        return forReturn;
    }

    public void writeToLog() {
        try {
            FileWriter writer = new FileWriter("kingdom_out.log");
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.println(log);
            printWriter.println(winner);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
