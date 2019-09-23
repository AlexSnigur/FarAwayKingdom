package org.btarikool.javacourse;

public class Peasant extends Person{

    public Peasant(String name) {
        super(name);
        this.title = "";
        this.health = 0.9;
        this.power = 2;
        this.game_over();

    }

}
