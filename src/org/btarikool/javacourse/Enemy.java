package org.btarikool.javacourse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Human {

    private final static String TITLE = "Enemy of the king";
    private String ownPhrase;
    private String receivedDecryptedPhrase;
    private byte[] ownEncryptedPhrase;
    private byte[] receivedEncryptedPhrase;
    private static int phraseCounter;
    private static int enemiesCounter;
    private int ownId;
    private static List<Enemy> list = new ArrayList<>();

    public Enemy(String name) throws IOException {
        super(name, TITLE, 0, 0, 10);
        this.ownPhrase = getEachEnemyOwnPhraseFromFile();
        this.ownEncryptedPhrase = this.ownPhrase.getBytes();
        this.ownId = enemiesCounter;
        this.enemiesCounter++;
        list.add(this);
    }

    public Enemy(String name, byte[] phraseInBytes) throws IOException {
        super(name, TITLE, 0, 0, 10);
        this.receivedEncryptedPhrase = phraseInBytes;
        this.receivedDecryptedPhrase = decryptMessage(this.receivedEncryptedPhrase);
        this.ownPhrase = receivedDecryptedPhrase + getEachEnemyOwnPhraseFromFile();
        this.ownEncryptedPhrase = this.ownPhrase.getBytes();
        this.ownId = enemiesCounter;
        this.enemiesCounter++;
        list.add(this);
    }

    public String getEachEnemyOwnPhraseFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\org\\btarikool\\javacourse\\song.txt"));
        String returningLine;
        if (phraseCounter == 0) {
            returningLine = "\n" + reader.readLine() + "\n";
            reader.close();
            phraseCounter = 1;
            return returningLine;

        } else {
            int counter = 0;
            while (counter <= phraseCounter) {
                returningLine = reader.readLine();
                if (counter == phraseCounter) {
                    reader.close();
                    phraseCounter++;
                    return returningLine + "\n";
                }
                counter++;
            }
        }
        return "empty";
    }

    public Enemy createNewEnemy(String name) throws IOException {
        return new Enemy(name, ownEncryptedPhrase);
    }

    public String receivedEncryptedMessage(byte[] array) {
        String s = "";
        for (byte x : array) s =  s + new String (x + " ");
        return s;
    }


    public String decryptMessage(byte[] array) {
        return new String(array);
    }

    public int getOwnId() {
        return this.ownId;
    }
    public static void removeEnemyFromList(int index) {
        list.set(index, null);
    }
    @Override
    public String toString() {
        if (this.enemiesCounter == 1) return "I am " + this.TITLE + ", my name is " + this.getName() + ".My phrase is: " + this.ownPhrase;
        else return "I am " + this.TITLE + ", my name is " + this.getName() + ". \nReceived encrypted phrase: " + receivedEncryptedMessage(receivedEncryptedPhrase) + " \nSo it looks like a song: " + this.receivedDecryptedPhrase;

    }
}
