package org.btarikool.javacourse;

import java.io.*;

public class Enemy extends Human {

    private final static String TITLE = "Enemy of the king";
    private String ownPhrase;
    private String receivedDecryptedPhrase;
    private byte[] ownEncryptedPhrase;
    private byte[] receivedEncryptedPhrase;
    private static int phraseCounter = 0;
    private static int enemiesCounter;

    public Enemy(String name) throws IOException {
        super(name, TITLE, 0, 0, 10);
        this.ownPhrase = getEachEnemyOwnPhraseFromFile();
        this.ownEncryptedPhrase = this.ownPhrase.getBytes();
        this.enemiesCounter++;
    }

    public Enemy(String name, byte[] phraseInBytes) throws IOException {
        super(name, TITLE, 0, 0, 10);
        this.receivedEncryptedPhrase = phraseInBytes;
        this.receivedDecryptedPhrase = decryptMessage(this.receivedEncryptedPhrase);
        this.ownPhrase = receivedDecryptedPhrase + getEachEnemyOwnPhraseFromFile();
        this.ownEncryptedPhrase = this.ownPhrase.getBytes();
        this.enemiesCounter++;
    }

    public String getEachEnemyOwnPhraseFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\org\\btarikool\\javacourse\\song.txt"));
        String returningLine;
        if (phraseCounter == 0) {
            phraseCounter = 1;
            return "\n" + reader.readLine() + "\n";

        } else {
            int counter = 1;
            while (counter <= phraseCounter) {
                reader.readLine();
                if (counter == phraseCounter) {
                    return reader.readLine() + "\n";
                }
                counter++;
            }
        }
        reader.close();
        return "ept";
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

    @Override
    public String toString() {
        if (this.enemiesCounter == 1) return "I am " + this.TITLE + ", my name is " + this.getName() + ".My phrase is: " + this.ownPhrase;
        else return "I am " + this.TITLE + ", my name is " + this.getName() + ". \nReceived encrypted phrase: " + receivedEncryptedMessage(receivedEncryptedPhrase) + " \nSo it looks like a song: " + this.receivedDecryptedPhrase;

    }
}
