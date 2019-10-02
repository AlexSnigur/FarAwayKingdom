package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {
    private String name;
    public static List<Hooman> hoomansList = new ArrayList<>();
    public static List<Hooman> deadList = new ArrayList<>();
    public static final double HEALTH_MIN = 0.2;
    private int peasantNumber = 1;



    public Kingdom(String theName) {
        this.name = theName;
    }

    public Hooman createHooman(String name, String title) {
        Hooman hooman;
        switch (title.toLowerCase()) {
            case "king":
                hooman = new King(name, hoomansList.size());
                break;
            case "lord":
                hooman = new Lord(name, hoomansList.size());
                break;
            case "knight":
                hooman = new Knight(name, hoomansList.size());
                break;
            default:
                hooman = new Peasant(name, hoomansList.size());
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
                hooman = new Lord(name, hoomansList.size());
                break;
            case "knight":
                hooman = new Knight(name, hoomansList.size());
                break;
            default:
                hooman = new Peasant(name, hoomansList.size());
                break;
        }
        hooman.setChief(chief);
        hoomansList.add(hooman);
        return hooman;
    }


    public void status() {
        for (Hooman h : hoomansList) {
                if (h.title.equals("King")) {
                    System.out.println(h + " [health: "
                            + h.getHealth() + ", power: " + h.getPower() + "]");
                } else if (!h.title.equals("King")) {
                    System.out.println(h + " [health: "
                            + h.getHealth() + ", power: " + h.getPower() + "], chief: " + h.getChief());
                }
        }
    }

    public static boolean lifeCheck() {
        for (Hooman check : hoomansList) {
                if (check.getHealth() < HEALTH_MIN) {
                    addToDeadList(check);
                    return true;
                }
        }
        return false;
    }

    public static void addToDeadList(Hooman hooman) {
        deadList.add(hooman);
        hoomansList.set(hooman.getIdNumber(), null);
    }

    public void doActions(King theKing, Lord lordOne, Lord lordTwo, Knight knightOne, Knight knightTwo) {

        System.out.println("\n---->>> Actions form S to C:");
            theKing.givePeasants(lordOne);
            theKing.givePeasants(lordTwo);
            Hooman peasantOne = createHooman("number #"+ peasantNumber, "Peasant", lordOne);
            Hooman peasantTwo = createHooman("number #"+ (peasantNumber +1), "Peasant", lordTwo);

            System.out.println(lordOne.getNameAndTitle()
                    + (lordOne.isCheckEven() ? lordOne.loyalty(lordOne.getChief()) : lordOne.militaryAid(lordOne.getChief()))
                    + lordOne.getChief());
            System.out.println(lordTwo.getNameAndTitle()
                    + (lordTwo.isCheckEven() ? lordTwo.loyalty(lordTwo.getChief()) : lordTwo.militaryAid(lordTwo.getChief()))
                    + lordTwo.getChief());
            System.out.println(knightOne.getNameAndTitle()
                    + (knightOne.isCheckEven() ? knightOne.greetings(knightOne.getChief()) : knightOne.militaryService(knightOne.getChief()))
                    + knightOne.getChief());
            System.out.println(knightTwo.getNameAndTitle()
                    + (knightTwo.isCheckEven() ? knightTwo.greetings(knightTwo.getChief()) : knightTwo.militaryService(knightTwo.getChief()))
                    + knightTwo.getChief());
            System.out.println(peasantOne.getNameAndTitle()
                    + (peasantOne.isCheckEven() ? peasantOne.farmLand(peasantOne.getChief()) : peasantOne.paysRent(peasantOne.getChief()))
                    + peasantOne.getChief());
            System.out.println(peasantTwo.getNameAndTitle()
                    + (peasantTwo.isCheckEven() ? peasantTwo.farmLand(peasantTwo.getChief()) : peasantTwo.paysRent(peasantTwo.getChief()))
                    + peasantTwo.getChief());
            peasantNumber +=2;

            System.out.println("\n---->>> Actions form C to S:");
            theKing.givePeasants(lordOne);
            theKing.givePeasants(lordTwo);
            Hooman peasantThree = createHooman("number #"+ peasantNumber, "Peasant", lordOne);
            Hooman peasantFour = createHooman("number #"+ + (peasantNumber +1), "Peasant", lordTwo);
            System.out.println(lordOne.getNameAndTitle()
                    + (lordOne.isCheckEven() ? lordOne.shelter(knightOne) : lordOne.protect(knightOne))
                    + knightOne.getNameAndTitle());
            System.out.println(lordTwo.getNameAndTitle()
                    + (lordTwo.isCheckEven() ? lordTwo.shelter(knightTwo) : lordTwo.protect(knightTwo))
                    + knightTwo.getNameAndTitle());
            System.out.println(knightOne.getNameAndTitle()
                    + (knightOne.isCheckEven() ? knightOne.giveFood(peasantThree) : knightOne.protect(peasantThree))
                    + peasantThree.getNameAndTitle());
            System.out.println(knightTwo.getNameAndTitle()
                    + (knightTwo.isCheckEven() ? knightTwo.giveFood(peasantFour) : knightTwo.protect(peasantFour))
                    + peasantFour.getNameAndTitle());
            peasantNumber +=2;

        lifeCheck();
    }
}

