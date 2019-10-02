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
    }

    public void giveLand(Hooman human) {

            this.changeStatus(human);
            totalLandQuantity--;
            System.out.println("King gives land to " + human.getNameAndTitle());

    }

    public void givePeasants(Hooman human) {
        this.changeStatus(human);
            peasantQuantity--;
            System.out.println("King gives peasant to " + human.getNameAndTitle());

    }
}
