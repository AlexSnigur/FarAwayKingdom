package org.btarikool.javacourse;

import java.io.*;

public class Enemy extends Human {

    private final static String TITLE = "Enemy of the king";
    private String ownPhrase;
    private String receivedDecryptedPhrase;
    private byte[] ownEncryptedPhrase;
    private byte[] receivedEncryptedPhrase;
    private static int phraseCounter;
    private Enemy createdBy;
    private final static double HEALTH = 0.0d;
    private final static int AUTHORITY = 0;
    private final static int STATUS_LEVEL = 10;

    public Enemy(String name, int idFromCollectiveList, int idFromOwnList, Kingdom kingdom) throws IOException {
        super(name, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, idFromCollectiveList, idFromOwnList, kingdom, null);
        this.ownPhrase = this.getEachEnemyOwnPhraseFromFile();
        this.ownEncryptedPhrase = this.ownPhrase.getBytes();
        System.out.println(toString());
    }

    public Enemy(String name, byte[] phraseInBytes, Kingdom kingdom, Enemy createdBy) throws IOException {
        super(name, TITLE, HEALTH, AUTHORITY, STATUS_LEVEL, kingdom.getHumanList().size(), kingdom.getHumanList().size(), kingdom, createdBy);
        this.receivedEncryptedPhrase = phraseInBytes;
        this.receivedDecryptedPhrase = decryptMessage(this.receivedEncryptedPhrase);
        this.ownPhrase = this.receivedDecryptedPhrase + getEachEnemyOwnPhraseFromFile();
        this.ownEncryptedPhrase = this.ownPhrase.getBytes();
        this.createdBy = createdBy;
        kingdom.addToLists(this);
        System.out.println(toString());
    }

    public String getEachEnemyOwnPhraseFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\org\\btarikool\\javacourse\\song.txt"));
        String returningLine;
        if (phraseCounter == 0) {
            returningLine = reader.readLine() + ", ";
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
                    return returningLine + ", ";
                }
                counter++;
            }
        }
        return "empty";
    }

    public Enemy createNewEnemy(String name) throws IOException {
        return new Enemy(name, ownEncryptedPhrase, this.getKingdom(), this);
    }

    public String receivedEncryptedMessage(byte[] array) {
        String s = "";
        try {
            for (byte x : array) s = s + new String(x + " ");
        } catch (NullPointerException a) {}
        return s;
    }

    public String decryptMessage(byte[] array) {
        return new String(array);
    }

    @Override
    public String toString() {
        if (this.getOwnListId() == 0) return "I'm " + this.getTitleAndName() +". My HP level: " + this.getHealPoints() + ". My authority level: " + this.getAuthorityPoints() + ".\nMy phrase is: " + this.ownPhrase;
        else return "I'm " + this.getTitleAndName() + ", I was created by " + this.createdBy.getTitleAndName() +". My HP level: " + this.getHealPoints() + ". My authority level: " + this.getAuthorityPoints() + ".\nReceived encrypted phrase: " + receivedEncryptedMessage(receivedEncryptedPhrase) + " \nSo it looks like a song: \"" + this.receivedDecryptedPhrase + "\"";
    }
}
