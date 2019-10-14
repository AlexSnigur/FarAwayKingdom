package org.btarikool.javacourse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kingdom {
    private String name;
    public List<Hooman> hoomansList = new ArrayList<>();
    public List<Hooman> deadList = new ArrayList<>();
    public List<Hooman> knightList = new ArrayList<>();
    public List<Hooman> kingList = new ArrayList<>();
    public List<Hooman> lordList = new ArrayList<>();
    public List<Wizard> wizardList = new ArrayList<>();

    public List<Hooman> knightFightList = new ArrayList<>();

    public static final double HEALTH_MIN = 0.2;
    private Settings settings;

    public Kingdom() {
        //Hooman.changeHpLoss(0.8);
        this.settings = new Settings();
        this.createHooman(settings.randomName(), "King");
        for (int i = 0; i < settings.qtyOfLords(); i++) {
            this.createHooman(settings.randomName(), "Lord");
        }
        for (int i = 0; i < settings.qtyOfWizards(); i++) {
            this.createHooman(settings.randomName(), "Wizard");
        }
        for (int i = 0; i < settings.qtyOfKnights(); i++) {
            this.createHooman(settings.randomName(), "knight");
        }
        this.name = settings.kingdomName();
        for (int i = 0; i < settings.gameRounds(); i++) {
            doActions(this);
            status();
        }
    }


    public Hooman createHooman(String name, String title) {
        Hooman hooman;
        switch (title.toLowerCase()) {
            case "king":
                hooman = new King(name, hoomansList.size());
                kingList.add(hooman);
                hooman.setHealth(settings.getHealthNumber(hooman));
                hooman.setPower(settings.getPowerNumber(hooman));
                break;
            case "lord":
                hooman = new Lord(name, hoomansList.size());
                lordList.add(hooman);
                hooman.setChief(kingList.get(0));
                hooman.getChief().getSubordinateList().add(hooman);
                hooman.setHealth(settings.getHealthNumber(hooman));
                hooman.setPower(settings.getPowerNumber(hooman));
                break;
            case "wizard":
                hooman = new Wizard(name, hoomansList.size());
                hooman.setChief(kingList.get(0));
                hooman.getChief().getSubordinateList().add(hooman);
                hooman.setHealth(settings.getHealthNumber(hooman));
                hooman.setPower(settings.getPowerNumber(hooman));
                wizardList.add((Wizard) hooman);
                break;
            case "knight":
                hooman = new Knight(name, hoomansList.size());
                knightList.add(hooman);
                hooman.setChief(getRandomLord());
                hooman.getChief().getSubordinateList().add(hooman);
                hooman.setHealth(settings.getHealthNumber(hooman));
                hooman.setPower(settings.getPowerNumber(hooman));
                break;
            default:
                hooman = new Peasant(name, hoomansList.size());
                hooman.setChief(getRandomLord());
                hooman.getChief().getSubordinateList().add(hooman);
                hooman.setHealth(settings.getHealthNumber(hooman));
                hooman.setPower(settings.getPowerNumber(hooman));
                break;
        }
        //hooman.setChief(chief);
        hoomansList.add(hooman);
        return hooman;
    }

    public Hooman getRandomLord() {
        int rand = new Random().nextInt(lordList.size());
        return lordList.get(rand);
    }


    public void status() {
        System.out.println("\nKingdom '" + this.name + "' status:");
        for (Hooman h : hoomansList) {
            if (h == null) {
                hoomansList.stream().skip(hoomansList.indexOf(h));
            } else if (h.title.equals("King")) {
                System.out.println(h + ", Subordinates: "
                        + h.getSubordinateListString());
            } else {
                System.out.println(h + ", chief: " + h.getChief().getNameAndTitle() + ", Subordinates: "
                        + h.getSubordinateListString());
            }
        }
        System.out.println("\n");
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
        hooman.setHealth(0);
        //hoomansList.set(hooman.getIdNumber(), null);
        if (hooman instanceof Knight) {
            knightList.remove(hooman);
        }
    }

    public Hooman pickRandomKnight() {
        Random r = new Random();
        Hooman hooman = knightList.get(r.nextInt(knightList.size()));
        return hooman;
    }

    public void fight() {
        while (knightList.size() > 1) {
            Hooman hooman1 = pickRandomKnight();
            Hooman hooman2 = pickRandomKnight();
            while (hooman2.equals(hooman1)) {
                hooman2 = pickRandomKnight();
            }
            System.out.println("Fight between " + hooman1 + " and " + hooman2 + " is started!");
            if (hooman1.checkRankDifference(hooman2) || hooman1.getPower() > hooman2.getPower()) {
                hooman1.setHealth(hooman1.getHealth() - (hooman2.getHealth() / 2));
                if (hooman1.getHealth() < HEALTH_MIN) {
                    hooman1.setHealth(HEALTH_MIN);
                }
                hooman1.setPower(hooman1.getPower() + (hooman2.getPower() / 2));
                addToDeadList(hooman2);
                System.out.println(hooman1.toString() + " wins the fight!");
            } else if (hooman2.checkRankDifference(hooman1) || hooman2.getPower() > hooman1.getPower()) {
                hooman2.setHealth(hooman2.getHealth() - (hooman1.getHealth() / 2));
                if (hooman2.getHealth() < HEALTH_MIN) {
                    hooman2.setHealth(HEALTH_MIN);
                }
                hooman2.setPower(hooman2.getPower() + (hooman1.getPower() / 2));
                addToDeadList(hooman1);
                System.out.println(hooman2.toString() + " wins the fight!");
            }

        }
        System.out.println("\nThe winner of the championship is: " + winnerDetails() + ", Congratulations!!!");
    }

    public Hooman winnerDetails() {
        return knightList.get(0);
    }


    public void doActions(Kingdom kingdom1) {


        System.out.println("\n---->>> Actions form S to C:");
        int count = hoomansList.size() - 1;
        while (count >= 0) {
            Hooman human = hoomansList.get(count);
            if (human instanceof King && human.isAlive()) {
                for (Hooman x : human.getSubordinateList()) {
                    if (x instanceof Lord) {
                        ((King) human).givePeasants(x, kingdom1);
                    }
                }
            }
            if (human instanceof Lord && human.isAlive() && human.getChief().isAlive()) {
                System.out.println(human.getNameAndTitle()
                        + (human.isCheckEven() ? human.loyalty(human.getChief()) : human.militaryAid(human.getChief())));
            }
            if (human instanceof Knight && human.isAlive() && human.getChief().isAlive()) {
                System.out.println(human.getNameAndTitle()
                        + (human.isCheckEven() ? human.greetings(human.getChief()) : human.militaryService(human.getChief())));
            } else {

            }
            /*for (Hooman h : lordOne.getSubordinateList()) {
                if (h instanceof Peasant && h.isAlive() && h.getChief().isAlive())
                    System.out.println(h.getName()
                            + (h.isCheckEven() ? h.farmLand(h.getChief()) : h.paysRent(h.getChief())));
            }
            for (Hooman g : lordTwo.getSubordinateList()) {
                if (g instanceof Peasant && g.isAlive() && g.getChief().isAlive())
                    System.out.println(g.getName()
                            + (g.isCheckEven() ? g.farmLand(g.getChief()) : g.paysRent(g.getChief())));
            }*/
            count--;
        }
        System.out.println("\n---->>> Actions form C to S:");


        for (int i = 0; i < hoomansList.size(); i++) {
            Hooman human = hoomansList.get(i);
            if (human instanceof King && human.isAlive()) {
                System.out.println(human.getNameAndTitle() + human.fief(human.getSubordinateList()));
            }
            if (human instanceof Lord && human.isAlive()) {
                System.out.println(human.getNameAndTitle()
                        + (human.isCheckEven() ? human.shelter(human.getSubordinateList()) : human.protect(human.getSubordinateList())));
            }
            if (human instanceof Knight && !human.getSubordinateList().isEmpty() && human.isAlive()) {
                System.out.println(human.getNameAndTitle()
                        + (human.isCheckEven() ? human.giveFood(human.getSubordinateList()) : human.protect(human.getSubordinateList())));
            }
        }
        lifeCheck();
        for (int i = 0; i < settings.qtyOfHeals(); i++) {
            wizardList.get(0).healKing(this);
        }
    }

    public void saveKingdomState() {
        try {
            FileWriter writer = new FileWriter("kingdom_out.log");
            PrintWriter printWriter = new PrintWriter(writer);
            for (Hooman h : this.hoomansList) {
                if (h instanceof King) {
                    printWriter.println(h);
                    printWriter.println(saveKingdomHelper(h));
                }
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String saveKingdomHelper(Hooman h) {
        String forReturn = "";
        for (int i = 0; i < h.getSubordinateList().size(); i++) {
            if (!h.getSubordinateList().get(i).isAlive()) {
                forReturn += "\u2620" + "   " + h.getSubordinateList().get(i) + "\n";
                for (int x = 0; x < h.getSubordinateList().get(i).getSubordinateList().size(); x++) {
                    Hooman hooman = h.getSubordinateList().get(i).getSubordinateList().get(x);
                    if (!hooman.isAlive()) {
                        forReturn += "\u2620" + "    " + hooman + "\n";
                    } else {
                        forReturn += "      " + hooman + "\n";
                    }
                }
            } else {
                forReturn += "   " + h.getSubordinateList().get(i) + "\n";
                for (int x = 0; x < h.getSubordinateList().get(i).getSubordinateList().size(); x++) {
                    Hooman hooman = h.getSubordinateList().get(i).getSubordinateList().get(x);
                    if (!hooman.isAlive()) {
                        forReturn += "\u2620" + "    " + hooman + "\n";
                    } else {
                        forReturn += "      " + hooman + "\n";
                    }
                }
            }
        }
        return forReturn;
    }
}

