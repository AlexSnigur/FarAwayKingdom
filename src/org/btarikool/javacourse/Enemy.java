package org.btarikool.javacourse;

import java.util.ArrayList;

public class Enemy extends Hooman {
    private final static String TITLE = "Enemy";
    private String phrasePart;
    private StringBuffer phrase;


    public Enemy(String name, String phrasePart, StringBuffer phrase, int idNumber) {
        super(name, idNumber,null);
        this.title = TITLE;
        this.health = 0.0;
        this.power = 0;
        this.phrasePart = phrasePart;
        this.phrase = phrase.append(phrasePart);
    }

    public Enemy createEnemy(String name, String phrasePart) {

        return new Enemy(name, phrasePart, this.phrase, idNumber);
    }

    public void decodeReverse() {
        String forReturn = "";
        for (int i = phrase.length(); i > 0; i--) {
            forReturn += phrase.charAt(i - 1);
        }
        System.out.println(forReturn);

    }

    public void decodeGroups() {
        String decodedPhraseOne = "";

        if (phrase.length() < 20) {
            for (int i = 7; i < phrase.length(); i++) {
                decodedPhraseOne += phrase.charAt(i);
                if (i > 8) {
                    break;
                }
            }
            for (int i = 0; i < phrase.length(); i++) {
                decodedPhraseOne += phrase.charAt(i);
                if (i > 5) {
                    break;
                }
            }
            for (int i = 10; i < phrase.length(); i++) {
                decodedPhraseOne += phrase.charAt(i);
            }
        } else if(phrase.length()>=20){
            for (int i = 7; i < phrase.length(); i++) {
                decodedPhraseOne += phrase.charAt(i);
                if (i > 8) {
                    break;
                }
            }
            for (int i = 0; i < phrase.length(); i++) {
                decodedPhraseOne += phrase.charAt(i);
                if (i > 5) {
                    break;
                }

            }
            for (int i = 17; i < phrase.length(); i++) {
                decodedPhraseOne += phrase.charAt(i);
                if (i > 18) {
                    break;
                }
            }
            for (int i = 10; i < phrase.length(); i++) {
                decodedPhraseOne += phrase.charAt(i);
                if (i > 15) {
                    break;
                }
            }
            for (int i = 20; i < phrase.length(); i++) {
                decodedPhraseOne += phrase.charAt(i);
            }
        }
        System.out.println(decodedPhraseOne);

    }

    @Override
    public String toString() {
        return title + " " + name + ": " + this.phrase;
    }
}
