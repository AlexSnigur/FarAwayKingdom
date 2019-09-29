package org.btarikool.javacourse;

import javafx.beans.binding.StringBinding;

public class Enemy extends Person {
    public static final String TITLE = "Enemy of the King";
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

    public String getDecryptedPhrase() {
        return reverseCharacters(reverseCharacters(this.phrase.toString()));
    }

    public String getEncryptedPhrase() {
        return reverseCharacters(this.phrase.toString());
    }

    private static String reverseCharacters(String text) {
        StringBuilder result = new StringBuilder(text);

        for (int i = 0; i < text.length(); i++) {
            int k = text.length() - 1 - i;

            if (text.length() % 2 == 0 && (text.length() / 2) == i) {
                break;
            }

            if (text.length() % 2 != 0 && i == k) {
                break;
            }

            char x = text.charAt(i);
            char y = text.charAt(k);
            result.setCharAt(i, y);
            result.setCharAt(k, x);
        }
        return result.toString();
    }
}