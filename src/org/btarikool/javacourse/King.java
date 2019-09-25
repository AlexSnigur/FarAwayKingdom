package org.btarikool.javacourse;

import java.util.ArrayList;
import java.util.List;

public class King extends Hooman {
    //private int landQuantity;
    private int peasantQuantity = 10;
    private int totalLandQuantity = 5;

    private String title;


    public King(String name, String title) {
        super(name, title);
        this.title = "King";
        this.health = 0.5;
        this.power = 10;
        this.levelOfDominance = 1;
        System.out.println("King " + name + " is created.");
    }

    public String giveLand(Hooman human) {


        this.changeStatus(human);
        return "King gives land to " + human.getNameAndTitle();

    }

    public void givePeasants(Hooman human) {
        peasantQuantity--;
        System.out.println("King gives peasant to "+ human.getNameAndTitle());
        //this.changeStatus(human);
    }
}
