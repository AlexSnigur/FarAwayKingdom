package org.btarikool.javacourse;

public class Enemy extends Person{

    public static final String TITLE = "Enemy of the King ";
    private String phrasePart;
    public StringBuffer phrase;

    public Enemy(String name, String phrasePart, StringBuffer phrase) {
        super(name);
        this.title = TITLE;
        this.health = 0.0;
        this.power = 0;
        this.phrasePart = phrasePart;
        this.phrase = phrase.append(phrasePart);
    }

    public Enemy createEnemy(String name, String phrasePart) {
        return new Enemy(name, phrasePart, this.phrase);
    }


    public static String encoded (String cipher)
    {
        StringBuffer decode = new StringBuffer();
        for (int i= 0; i< cipher.length(); i++)
        {
            decode.insert( 0, cipher.charAt(i));
        }
        return decode.toString();
    }


}
