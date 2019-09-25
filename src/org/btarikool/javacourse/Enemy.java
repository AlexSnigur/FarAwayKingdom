package org.btarikool.javacourse;

public class Enemy extends Hooman {
    private final static String TITLE = "Enemy";
    private String phrasePart;
    private StringBuffer phrase;


    public Enemy(String name, String phrasePart, StringBuffer phrase) {
        super(name);
        //this.levelOfDominance = 4;
        this.title = TITLE;
        this.health = 0.0;
        this.power = 0;
        this.phrasePart = phrasePart;
        this.phrase = phrase.append(phrasePart);
    }
    public Enemy createEnemy(String name, String phrasePart){
        return  new Enemy(name, phrasePart, this.phrase);
    }


    @Override
    public String toString() {
        return title +" "+ name +": "+ this.phrase ;
    }
}
