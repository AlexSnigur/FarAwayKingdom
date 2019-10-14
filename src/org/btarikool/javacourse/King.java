package org.btarikool.javacourse;


public class King extends Hooman {
    private final static String TITLE = "King";
    private int peasantQuantity = 10;
    private int totalLandQuantity = 5;


    public King(String name, int idNumber) {
        super(name, idNumber);
        this.title = TITLE;
        this.health = 0.5;
        this.power = 10;
        this.levelOfDominance = 1;
        this.rank = this.health * this.power;
    }

    public void giveLand(Hooman human) {
            this.changeStatus(human);
            totalLandQuantity--;
            System.out.println("King gives land to " + human.getNameAndTitle());
    }

    public Hooman givePeasants(Hooman human, Kingdom kingdom) {
            peasantQuantity--;
            Hooman forReturn = kingdom.createHooman(human.getNameAndTitle() +"'s peasant #" +kingdom.hoomansList.size(), "");
            forReturn.getChief().getSubordinateList().remove(forReturn);
            forReturn.setChief(human);
            human.getSubordinateList().add(forReturn);
            return forReturn;

    }
}
