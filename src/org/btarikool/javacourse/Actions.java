package org.btarikool.javacourse;

public class Actions extends Human{

    public Actions(String name, String title, double healPoints, int authorityPoints, int statusLevel, int collectiveListId, int ownListId, Kingdom kingdom) {
        super(name, title, healPoints, authorityPoints, statusLevel, collectiveListId, ownListId, kingdom);
    }

    public void doPresentPeasant(Human person) {
        if (this.getNumPeasantsOwned() > 0) {
            this.getMyPeasantsList().get(0).setOwner(person);
            person.getMyPeasantsList().add(this.getMyPeasantsList().get(0));
            System.out.println(this.getTitleAndName() + " presents " + this.getMyPeasantsList().get(0).getTitleAndName() + " to " + person.getTitleAndName() + ".");
            this.getMyPeasantsList().remove(0);
        } else {
            System.out.println(this.getTitleAndName() + " doesnt present any peasant to " + person.getTitleAndName() + " , because he owns 0 peasants");
        }
    }

    public void doPresentSmthng(Human person, String present) {
        System.out.println(this.getTitleAndName() + " gives " + present + " " + person.getTitleAndName());
        this.changeHpAndAuthorityLevel(person);
    }

    public void doOath(Human person) {
        System.out.println(this.getTitleAndName() + " swears to the " + person.getTitleAndName());
        this.changeHpAndAuthorityLevel(person);
    }

    public void doShelter(Human person) {
        System.out.println(this.getTitleAndName() + " gives shelter to " + person.getTitleAndName());
        this.changeHpAndAuthorityLevel(person);
    }

    public void doBringNewLand(Human person, String present) {
        System.out.println(this.getTitleAndName() + " conquering " + present + " " + person.getTitleAndName());
        this.changeHpAndAuthorityLevel(person);
    }
    public void doGiveHomage(Human person) {
        System.out.println(this.getTitleAndName() + " gives homage " + person.getTitleAndName());
        this.changeHpAndAuthorityLevel(person);
    }
}
