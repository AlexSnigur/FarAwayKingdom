package org.btarikool.javacourse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Kingdom {
    private String name;
    private final List<Human> HUMAN_LIST = new ArrayList<>();
    private final List<Human> HUMAN_LIST_DUB = new ArrayList<>();
    private final List<Human> KING_LIST = new ArrayList<>();
    private final List<Human> LORD_LIST = new ArrayList<>();
    private final List<Human> KNIGHT_LIST = new ArrayList<>();
    private final List<Human> WIZARD_LIST = new ArrayList<>();
    private final List<Human> PEASANT_LIST = new ArrayList<>();
    private final List<Human> ENEMY_LIST = new ArrayList<>();
    private final List<Human> DEAD_LIST = new ArrayList<>();
    private Settings settings;
    private String toLog = "";

    public Kingdom() {
    }

    public Kingdom(String name) {
        this.settings = new Settings();
        this.name = name;
        settings.setInputSettingsForStart();
        addNewHumansToList();
        printResult();
        runActions(settings.getIterCount());
    }

    public Human createHuman(String name, String title, Human chief) throws IOException {
        Human human = null;
        switch (title.toLowerCase()) {
            case "king":
                human = new King(name, HUMAN_LIST.size(), KING_LIST.size(), this);
                addToLists(human);
                break;
            case "lord":
                human = new Lord(name, HUMAN_LIST.size(), LORD_LIST.size(), this, chief);
                addToLists(human);
                break;
            case "knight":
                human = new Knight(name, HUMAN_LIST.size(), KNIGHT_LIST.size(), this, chief);
                addToLists(human);
                break;
            case "wizard":
                human = new Wizard(name, HUMAN_LIST.size(), WIZARD_LIST.size(), this, chief);
                addToLists(human);
                break;
            case "enemy":
                human = new Enemy(name, HUMAN_LIST.size(), ENEMY_LIST.size(), this);
                addToLists(human);
                break;
        }
        return human;
    }

    public List<Human> getHumanList() {
        return HUMAN_LIST;
    }

    public List<Human> getKingList() {
        return KING_LIST;
    }

    public List<Human> getLordList() {
        return LORD_LIST;
    }

    public List<Human> getHumanDubList() {
        return HUMAN_LIST_DUB;
    }

    public List<Human> getKnightList() {
        return KNIGHT_LIST;
    }

    public List<Human> getPeasantList() {
        return PEASANT_LIST;
    }

    public List<Human> getWizardList() {
        return WIZARD_LIST;
    }

    public List<Human> getEnemyList() {
        return ENEMY_LIST;
    }

    public List<Human> getDeadList() {
        return DEAD_LIST;
    }

    public void printHumanList() {
        HUMAN_LIST.stream().forEach(System.out::println);
    }

    public void addToLists(Human human) {
        HUMAN_LIST.add(human);
        HUMAN_LIST_DUB.add(human);
        if (human instanceof King) KING_LIST.add(human);
        else if (human instanceof Lord) LORD_LIST.add(human);
        else if (human instanceof Knight) KNIGHT_LIST.add(human);
        else if (human instanceof Wizard) WIZARD_LIST.add(human);
        else if (human instanceof Peasant) PEASANT_LIST.add(human);
        else if (human instanceof Enemy) ENEMY_LIST.add(human);
    }

    public Knight[] getRandomKnightsList() {
        Random random = new Random();
        Knight[] shuffledKnightList = new Knight[KNIGHT_LIST.size()];
        Collections.shuffle(KNIGHT_LIST);
        for (int x = shuffledKnightList.length - 1; x >= 0; x--) {
            shuffledKnightList[x] = (Knight) KNIGHT_LIST.get(x);
            KNIGHT_LIST.remove(x);
        }
        return shuffledKnightList;
    }

    public void checkForChampionshipStart() {
        if (KNIGHT_LIST.size() == 8) {
            System.out.println("\n-----------------------CHAMPIONSHIP BEGINS!----------------------\n");
            doFightRand(getRandomKnightsList());
            System.out.println("\n-----------------------CHAMPIONSHIP IS OVER! CHAMPION IS " + KNIGHT_LIST.get(0).getTitleAndName() + " ----------------------\n");
        }
    }

    public void doFightRand(Knight[] shuffledKnightList) {
        int counter = shuffledKnightList.length;
        while (counter > 0) {
            counter--;
            Knight knightFighter1 = shuffledKnightList[counter];
            counter--;
            Knight knightFighter2 = shuffledKnightList[counter];
            Knight looser;
            Knight winner;
            boolean isMore = Math.abs(knightFighter1.getRankByField() - knightFighter2.getRankByField()) > 0.5d ? true : false;
            if (isMore) looser = knightFighter1.getRankByField() > knightFighter2.getRankByField() ? knightFighter2 : knightFighter1;
            else looser = knightFighter1.getAuthorityPoints() > knightFighter2.getAuthorityPoints() ? knightFighter2 : knightFighter1;
            winner = looser == knightFighter1 ? knightFighter2 : knightFighter1;
            winner.setHealPoints(winner.getHealPoints() - looser.getHealPoints() / 2);
            winner.setAuthorityPoints(winner.getAuthorityPoints() + looser.getAuthorityPoints() / 2);
            Human.setRank(winner);
            KNIGHT_LIST.add(winner);
            looser.setHealPoints(0);
            System.out.println(winner.getTitleAndName() + " fights with " + looser.getTitleAndName() + ", winner is: " + winner.getTitleAndName());
            this.removeFromAliveSetToDeadList(looser);
        }
        if (KNIGHT_LIST.size() > 1 && KNIGHT_LIST.size() % 2 == 0) doFightRand(getRandomKnightsList());
    }

/*    public Human doFight(Knight knight1, Knight knight2) {
        Knight looser;
        Knight winner;
        boolean isMore = Math.abs(knight1.getRankByField() - knight2.getRankByField()) > 0.5d ? true : false;
        if (isMore) looser = knight1.getRankByField() > knight2.getRankByField() ? knight2 : knight1;
        else looser = knight1.getAuthorityPoints() > knight2.getAuthorityPoints() ? knight2 : knight1;
        winner = looser == knight1 ? knight2 : knight1;
        winner.setHealPoints(winner.getHealPoints() - (looser.getHealPoints() / 2));
        winner.setAuthorityPoints(winner.getAuthorityPoints() + (looser.getAuthorityPoints() / 2));
        Human.setRank(winner);
        looser.setHealPoints(0);
        System.out.println(winner.getTitleAndName() + " fights with " + looser.getTitleAndName() + ", winner is: " + winner.getTitleAndName());
        this.removeFromAliveSetToDeadList(looser);
        return winner;
    }*/

    public void removeFromAliveSetToDeadList(Human human) {
        if (human.getHealPoints() < human.getMINIMUM_HP_LEVEL()) {
            System.out.println("\n---------------------------------------------" + human.getTitleAndName() + " IS DEAD---------------------------------------------------\n");
            human.setStatus(false);
            DEAD_LIST.add(human);
            if (human.getChief()!=null) {
                human.getChief().getSubordinateList().remove(human);
                human.getChief().getMyPeasantsList().removeIf(x -> x == human);
            }
            HUMAN_LIST.remove(human);
            KING_LIST.removeIf(x-> x == human);
            LORD_LIST.removeIf(x-> x == human);
            KNIGHT_LIST.removeIf(x-> x == human);
            WIZARD_LIST.removeIf(x-> x == human);
            PEASANT_LIST.removeIf(x-> x == human);
            ENEMY_LIST.removeIf(x-> x == human);
        }
    }

    public void runActions(int countofIter) {
        int counterForSout = 1;
        while (countofIter > 0 && !KING_LIST.get(0).getSubordinateList().isEmpty()) {
            System.out.println("\n\t\t" + counterForSout + " ITERATION:");
            int size = HUMAN_LIST.size() - 1;
            actionsUp(size);
            actionsDown(size, 0);
            countofIter--;
            counterForSout++;
            printResult();
            writeLog();
        }
    }

    public void actionsUp(int count) {
        if (count >= 0) {
            Human human = HUMAN_LIST.get(count);
            actionWithChief(human);
            count--;
            actionsUp(count);
        } else return;
    }

    public void actionWithChief(Human human) {
        if (human.getChief() != null && human.getChief().getStatus()) {
            Human chief = human.getChief();
            System.out.println(human.getTitleAndName() + " gives " + human.getPhrase(0) + " to " + chief.getTitleAndName());
            removeFromAliveSetToDeadList(human);
            checkForChampionshipStart();
        } else return;
    }

    public void actionsDown(int count, int start) {
        if (start <= count) {
            if (count != HUMAN_LIST.size()-1) count = HUMAN_LIST.size()-1;
            while (HUMAN_LIST.get(start).equals(null)) start++;
            Human human = HUMAN_LIST.get(start);
            int subSize = human.getSubordinateList().size() - 1;
            actionWithSubordinate(human, subSize, 0);
            start++;
            actionsDown(count, start);
        } else return;
    }

    public void actionWithSubordinate(Human human, int subCount, int start) {
        if (HUMAN_LIST.get(start) != null && start <= subCount && human.getSubordinateList().size() != 0) {
            if (!(human.getPhrase(0) == null) || !(human.getPhrase(1) == null)) {
                Human subordinate = human.getSubordinateList().get(start);
                if (human instanceof King) ((King) human).doPresentPeasant(subordinate);
                System.out.println(human.getTitleAndName() + " gives " + human.getPhrase(1) + " to " + subordinate.getTitleAndName());
                human.changeHpAndAuthorityLevel(subordinate);
                for (Human wizard : getWizardList()) ((Wizard) wizard).doToHeal((King) KING_LIST.get(0));
                removeFromAliveSetToDeadList(human);
                checkForChampionshipStart();
                start++;
                subCount = human.getSubordinateList().size() - 1;
                if (human.getStatus()) actionWithSubordinate(human, subCount, start);
            }
        } else return;
    }
    public void printResult() {
        String line = "\n---------------------------------------------RESULTS---------------------------------------------------\n";
        System.out.println(line);
        toLog = toLog.concat(line);
        String kingToString = KING_LIST.get(0).toString();
        System.out.println(kingToString);
        toLog = toLog.concat("\n" + kingToString);
        checkForPrintResult(KING_LIST.get(0), "\t");
        toLog = toLog.concat("\n");
    }

    private void checkForPrintResult(Human human,String tab) {
        for (Human sub : HUMAN_LIST_DUB) {
            if (!(sub.getChief() == null) && sub.getChief().equals(human)) {
                String subToString = (sub.getStatus() ? "" : "☠" ) + tab + sub.toString();
                System.out.println(subToString);
                toLog = toLog.concat("\n" + subToString);
                checkForPrintResult(sub, tab + "\t");
            }
        }
    }

    private void writeLog() {
        SimpleDateFormat df = new SimpleDateFormat("_dd_hh_mm_ss");
        String date = df.format(new Date());
        String dir = System.getProperty("user.dir").concat("\\log\\kingdom_" + this.name.replaceAll("\\s", "_") + date + ".log");
        try (BufferedWriter output = new BufferedWriter(new FileWriter(dir, true))) {
            output.write(toLog);
            toLog = "";
            System.out.println("WRITTEN");
        } catch (IOException e) {}
    }

    private void addNewHumansToList() {
        try {
            for (int x = 0; x < 1; x++) createHuman(settings.getRandomName(), "king", null);
            for (int x = 0; x < settings.getCountOnStartLord(); x++) createHuman(settings.getRandomName(), "lord", KING_LIST.get(0));
            for (int x = 0; x < settings.getCountOnStartWizard(); x++) createHuman(settings.getRandomName(), "wizard", KING_LIST.get(0));
            for (int x = 0; x < settings.getCountOnStartKnight(); x++) createHuman(settings.getRandomName(), "knight", LORD_LIST.get(getRandomLord()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getRandomLord() {
        return new Random().ints(0, settings.getCountOnStartLord()).findFirst().getAsInt();
    }


}