package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kingdom {
    private String name;
    public List<Hooman> hoomansList = new ArrayList<>();
    public List<Hooman> deadList = new ArrayList<>();
    public List<Hooman> knightList = new ArrayList<>();
    public static final double HEALTH_MIN = 0.2;
    private int peasantNameNumber = 1;


    public Kingdom(String theName) {
        this.name = theName;
    }

    public Hooman createHooman(String name, String title) {
        Hooman hooman;
        switch (title.toLowerCase()) {
            case "king":
                hooman = new King(name, hoomansList.size());
                break;
            default:
                hooman = new Peasant(name, hoomansList.size(), null);
                break;
        }
        hoomansList.add(hooman);
        return hooman;
    }

    public Hooman createHooman(String name, String title, Hooman chief) {
        Hooman hooman;
        switch (title.toLowerCase()) {
            case "king":
                hooman = new King(name, hoomansList.size());
                break;
            case "lord":
                hooman = new Lord(name, hoomansList.size(), chief);
                break;
            case "wizard":
                hooman = new Wizard(name, hoomansList.size(), chief);
                break;
            case "knight":
                hooman = new Knight(name, hoomansList.size(), chief);
                break;
            default:
                hooman = new Peasant(name, hoomansList.size(), chief);
                break;
        }
        hooman.setChief(chief);
        hoomansList.add(hooman);
        return hooman;
    }


    public void status() {
        System.out.println("\n");
        for (Hooman h : hoomansList) {
            if (h == null) {
                hoomansList.stream().skip(hoomansList.indexOf(h));
            } else if (h.title.equals("King") || h.title.equals("Wizard")) {
                System.out.println(h + " [health: "
                        + h.getHealth() + ", power: " + h.getPower() + ", Subordinates: "
                        + h.getSubordinateListString());
            } else {
                System.out.println(h + " [health: "
                        + h.getHealth() + ", power: " + h.getPower() + "], chief: " + h.getChief() + ", Subordinates: "
                        + h.getSubordinateListString());
            }
        }
    }

    public void lifeCheck() {
        for (Hooman check : hoomansList) {
            if (check == null) {
                hoomansList.stream().skip(hoomansList.indexOf(check));
            } else if (check.getHealth() < HEALTH_MIN) {
                addToDeadList(check);
            }
        }
    }

    public void addToDeadList(Hooman hooman) {
        deadList.add(hooman);
        hooman.setAlive(false);
        hoomansList.set(hooman.getIdNumber(), null);
    }

    public void fight() {
        Random r = new Random();
        Hooman hooman1 = knightList.get(r.nextInt(knightList.size()));
        knightList.remove(hooman1);
        Hooman hooman2 = knightList.get(r.nextInt(knightList.size()));
        knightList.remove(hooman2);
        System.out.println("\nFight between " + hooman1 + " and " + hooman2 + " is started!");
        if (hooman1.checkRankDifference(hooman2) || hooman1.getPower() > hooman2.getPower()) {
            hooman1.setHealth(hooman1.getHealth() - (hooman2.getHealth() / 2));
            hooman1.setPower(hooman1.getPower() + (hooman2.getPower() / 2));
            addToDeadList(hooman2);
            knightList.add(hooman1);
            System.out.println(hooman1 + " wins the fight");
        } else if (hooman2.checkRankDifference(hooman1) || hooman2.getPower() > hooman1.getPower()) {
            hooman2.setHealth(hooman2.getHealth() - (hooman1.getHealth() / 2));
            hooman2.setPower(hooman2.getPower() + (hooman1.getPower() / 2));
            addToDeadList(hooman1);
            System.out.println(hooman2 + " wins the fight");
        } else {
            System.out.println("Since participants are even, the fight is cancelled");
        }
    }

    public void doActions(King theKing, Lord lordOne, Lord lordTwo, Knight knightOne, Knight knightTwo, Kingdom kingdom1) {

        System.out.println("\n---->>> Actions form S to C:");
        if (theKing.isAlive()) {
            theKing.givePeasants(lordOne, kingdom1);
            theKing.givePeasants(lordTwo, kingdom1);
        }
        if (lordOne.isAlive() && lordOne.getChief().isAlive()) {
            System.out.println(lordOne.getNameAndTitle()
                    + (lordOne.isCheckEven() ? lordOne.loyalty(lordOne.getChief()) : lordOne.militaryAid(lordOne.getChief()))
                    + lordOne.getChief());
        }
        if (lordTwo.isAlive() && lordTwo.getChief().isAlive()) {
            System.out.println(lordTwo.getNameAndTitle()
                    + (lordTwo.isCheckEven() ? lordTwo.loyalty(lordTwo.getChief()) : lordTwo.militaryAid(lordTwo.getChief()))
                    + lordTwo.getChief());
        }
        if (knightOne.isAlive() && knightOne.getChief().isAlive()) {
            System.out.println(knightOne.getNameAndTitle()
                    + (knightOne.isCheckEven() ? knightOne.greetings(knightOne.getChief()) : knightOne.militaryService(knightOne.getChief()))
                    + knightOne.getChief());
        }
        if (knightTwo.isAlive() && knightTwo.getChief().isAlive()) {
            System.out.println(knightTwo.getNameAndTitle()
                    + (knightTwo.isCheckEven() ? knightTwo.greetings(knightTwo.getChief()) : knightTwo.militaryService(knightTwo.getChief()))
                    + knightTwo.getChief());
        }
        for (Hooman h : lordOne.getSubordinateList()) {
            if (h instanceof Peasant && h.isAlive() && h.getChief().isAlive())
                System.out.println(h.getName()
                        + (h.isCheckEven() ? h.farmLand(h.getChief()) : h.paysRent(h.getChief()))
                        + h.getChief());
        }
        for (Hooman g : lordTwo.getSubordinateList()) {
            if (g instanceof Peasant && g.isAlive() && g.getChief().isAlive())
                System.out.println(g.getName()
                        + (g.isCheckEven() ? g.farmLand(g.getChief()) : g.paysRent(g.getChief()))
                        + g.getChief());
        }

        System.out.println("\n---->>> Actions form C to S:");
        if (theKing.isAlive()) {
            System.out.println(theKing.getNameAndTitle() + theKing.fief(theKing.getSubordinateList()));
        }
        if (lordOne.isAlive()) {
            System.out.println(lordOne.getNameAndTitle()
                    + (lordOne.isCheckEven() ? lordOne.shelter(lordOne.getSubordinateList()) : lordOne.protect(lordOne.getSubordinateList())));
        }
        if (lordTwo.isAlive()) {
            System.out.println(lordTwo.getNameAndTitle()
                    + (lordTwo.isCheckEven() ? lordTwo.shelter(lordTwo.getSubordinateList()) : lordTwo.protect(lordTwo.getSubordinateList())));
        }
        if (!knightOne.getSubordinateList().isEmpty() && knightOne.isAlive()) {
            System.out.println(knightOne.getNameAndTitle()
                    + (knightOne.isCheckEven() ? knightOne.giveFood(knightOne.getSubordinateList()) : knightOne.protect(knightOne.getSubordinateList())));
        }
        if (!knightTwo.getSubordinateList().isEmpty() && knightTwo.isAlive()) {
            System.out.println(knightTwo.getNameAndTitle()
                    + (knightTwo.isCheckEven() ? knightTwo.giveFood(knightTwo.getSubordinateList()) : knightTwo.protect(knightTwo.getSubordinateList())));
        }
        lifeCheck();
    }
}

