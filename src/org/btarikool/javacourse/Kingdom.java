package org.btarikool.javacourse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
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
    private int iterationCounter = 0;

    public Kingdom() {
    }

    public Kingdom(String name) {
        this.settings = new Settings(this);
        this.name = name;
        addNewHumansToList();
        runActions(settings.getIterCount());
    }

    public Settings getSettings() {
        return settings;
    }

    public String getName() {
        return name;
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

    public void removeFromAliveSetToDeadList(Human human) {
        if (human.getHealPoints() < human.getMINIMUM_HP_LEVEL()) {
            System.out.println("☠ " + human.getTitleAndName());
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

    public void runActions(int countOfIter) {
        while (countOfIter > 0 && !KING_LIST.get(0).getSubordinateList().isEmpty()) {
            iterationCounter++;
            System.out.println("\n\t" + iterationCounter + " ITERATION:\n");
            int size = HUMAN_LIST.size() - 1;
            actionsUp(size);
            actionsDown(size, 0);
            countOfIter--;
            printResult();
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
            //checkForChampionshipStart();
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
                //checkForChampionshipStart();
                start++;
                subCount = human.getSubordinateList().size() - 1;
                if (human.getStatus()) actionWithSubordinate(human, subCount, start);
            }
        } else return;
    }
    public void printResult() {
        String line = "\n\t" + iterationCounter + " ITERATION RESULTS : " + this.name.concat(" kingdom").toUpperCase() + "\n";
        System.out.println(line);
        toLog = toLog.concat(line);
        String kingToString = KING_LIST.get(0).toString();
        System.out.println(kingToString);
        toLog = toLog.concat("\n" + kingToString);
        checkForPrintResult(KING_LIST.get(0), "\t");
        System.out.println("\n");
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

    private void addNewHumansToList()  {
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

    public String getLog() {
        return this.toLog;
    }

    public void emptyLog() {
        this.toLog = "";
    }

}