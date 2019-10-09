package org.btarikool.javacourse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kingdom {
    private String name;
    private final List<Human> HUMAN_LIST = new ArrayList<>();
    private final List<Human> KING_LIST = new ArrayList<>();
    private final List<Human> LORD_LIST = new ArrayList<>();
    private final List<Human> KNIGHT_LIST = new ArrayList<>();
    private final List<Human> WIZARD_LIST = new ArrayList<>();
    private final List<Human> PEASANT_LIST = new ArrayList<>();
    private final List<Human> ENEMY_LIST = new ArrayList<>();
    private final List<Human> DEAD_LIST = new ArrayList<>();

    public Kingdom() {
    }

    public Kingdom(String name) {
        this.name = name;
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
        if (human instanceof King) KING_LIST.add(human);
        else if (human instanceof Lord) LORD_LIST.add(human);
        else if (human instanceof Knight) KNIGHT_LIST.add(human);
        else if (human instanceof Wizard) WIZARD_LIST.add(human);
        else if (human instanceof Peasant) PEASANT_LIST.add(human);
        else if (human instanceof Enemy) ENEMY_LIST.add(human);
    }

    public Knight[] getRandomPair() {
        Random random = new Random();
        int a = 0;
        int b = a;
        a = random.nextInt(KNIGHT_LIST.size());
        while (a == b) b = random.nextInt(KNIGHT_LIST.size());
        Knight[] pair = new Knight[] {(Knight)KNIGHT_LIST.get(a), (Knight)KNIGHT_LIST.get(b)};
        return pair;
    }

    public void doFight(Knight[] knight) {
        Knight looser;
        Knight winner;
        boolean isMore = Math.abs(knight[0].getRankByField() - knight[1].getRankByField()) > 0.5d ? true : false;
        if (isMore) looser = knight[0].getRankByField() > knight[1].getRankByField() ? knight[1] : knight[0];
        else looser = knight[0].getAuthorityPoints() > knight[1].getAuthorityPoints() ? knight[1] : knight[0];
        winner = looser == knight[0] ? knight[1] : knight[0];
        winner.setHealPoints(winner.getHealPoints() - looser.getHealPoints() / 2);
        winner.setAuthorityPoints(winner.getAuthorityPoints() + looser.getAuthorityPoints() / 2);
        Human.setRank(winner);
        looser.setHealPoints(0);
        this.removeFromAliveSetToDeadList(looser);
    }

    public void removeFromAliveSetToDeadList(Human human) {
        if (human.getHealPoints() < Human.getMINIMUM_HP_LEVEL()) {
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
}