package org.btarikool.javacourse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Championship {

    private static Map<Knight, Kingdom> knightsMap = new HashMap<>();
    private static Knight winner;

    private Championship() {
    }

    public static Knight getWinner() {
        return winner;
    }

    public static void startChamp() {
        listsToMap(Settings.getKingdomsList());
        System.out.println("Championship between kingdoms begins!\n".toUpperCase());
        fighting(getTwoFighters());
        System.out.println("\nTHE WINNER OF THIS CHAMPIONSHIP IS " + Championship.winner.getTitleAndName() +
                ".\nKINGDOM " + winner.getKingdom().getName().toUpperCase() +
                " WINS!");
    }

    private static void fighting(Knight[] twoFighters) {
        Knight fighter1 = twoFighters[0];
        Knight fighter2 = twoFighters[1];
        Knight looser;
        boolean isMore = Math.abs(fighter1.getRankByField() - fighter2.getRankByField()) > 0.5d ? true : false;
        if (isMore) looser = fighter1.getRankByField() > fighter2.getRankByField() ? fighter2 : fighter1;
        else looser = fighter1.getAuthorityPoints() > fighter2.getAuthorityPoints() ? fighter2 : fighter1;
        Knight winner = looser == fighter1 ? fighter2 : fighter1;
        System.out.println(fighter1.getTitleAndName() +
                " From Kingdom: " + fighter1.getKingdom().getName().toUpperCase() +
                " fights with " + fighter2.getTitleAndName() +
                " From Kingdom: " + fighter2.getKingdom().getName().toUpperCase() +
                ". WINNER IS: " + winner.getTitleAndName());
        setStats(winner, looser);
        Kingdom loosersKingdom = looser.getKingdom();
        if (knightsMap.values().contains(loosersKingdom))
        fighting(getTwoFighters());
    }

    private static Knight[] getTwoFighters() {
        Knight fighter1;
        Knight fighter2;
        if (Championship.winner == null) fighter1 = knightsMap.keySet().stream().findFirst().get();
        else fighter1 = Championship.winner;
        fighter2 = knightsMap.keySet().stream().filter(knight -> knight.getKingdom() != fighter1.getKingdom()).findFirst().get();
        return new Knight[] {fighter1, fighter2};
    }

    private static void setStats(Knight winner, Knight looser) {
        winner.setHealPoints(winner.getHealPoints() - looser.getHealPoints() / 2);
        winner.setAuthorityPoints(winner.getAuthorityPoints() + looser.getAuthorityPoints() / 2);
        Human.setRank(winner);
        looser.setHealPoints(0);
        knightsMap.remove(looser);
        looser.getKingdom().removeFromAliveSetToDeadList(looser);
        Championship.winner = winner;
    }


    private static void listsToMap(List<Kingdom> list) {
        list.stream().forEach(x -> {
                        x.getKnightList().stream().forEach(knight ->
                                    knightsMap.put((Knight)knight, x));
                    });
    }

}
