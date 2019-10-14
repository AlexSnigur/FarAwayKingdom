package org.btarikool.javacourse;


public class Wizard extends Hooman {
    private final static String TITLE = "Wizard";


    public Wizard(String name, int idNumber) {
        super(name, idNumber);
        this.title = TITLE;
        this.health = 1.0;
        this.power = 10;
        this.levelOfDominance = 2;
        this.rank = this.health * this.power;
    }




    public void healKing(Kingdom k) {
        System.out.println("\n>"+this.getNameAndTitle() + " heals the King");
        King king = (King) this.chief;
        king.setHealth(king.getHealth() + 0.2);
        Peasant peasant = (Peasant) king.givePeasants(this, k);
        peasant.getChief().getSubordinateList().remove(peasant);
        peasant.setChief(this);
        this.getSubordinateList().add(peasant);
        peasant.setHealth(getHealth() - 0.1);
        peasant.setPower(getPower() + 1);
        this.getListOfPeasants().add(peasant);
        if (this.getListOfPeasants().size() % 5 == 0) {
            createKnight(k);
        }
    }
    public void createKnight(Kingdom k){
        Knight knight = new Knight("Promoted #" + this.getListOfPeasants().get(0).idNumber,
                this.getListOfPeasants().get(0).idNumber);
        int x = k.hoomansList.indexOf(this.getListOfPeasants().get(0));
        knight.setChief(this);
        this.getSubordinateList().add(knight);
        knight.setHealth(getRandomHealth());
        knight.setPower(getRandomPower());
        knight.rank = knight.health * knight.power;
        k.hoomansList.set(x, knight);
        k.knightList.add(knight);
        this.getListOfPeasants().remove(0);
    }

    public double getRandomHealth() {
        double base = 0.3;
        double rand = Math.random() * 0.6; //from 0.0 to 0.6
        return base + rand;
    }

    public int getRandomPower() {
        int base = 4;
        int rand = (int) (Math.random() * 5); //from 0.0 to 5
        return base + rand;
    }
}