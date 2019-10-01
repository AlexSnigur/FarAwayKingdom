package org.btarikool.javacourse;

public class Actions extends Human{

    public Actions() {
    }

    public Actions(String name, String title, double healPoints, int authorityPoints, int statusLevel, int collectiveListId, int ownListId, Kingdom kingdom, Human chief) {
        super(name, title, healPoints, authorityPoints, statusLevel, collectiveListId, ownListId, kingdom, chief);
    }

    public void doPresentPeasant(Human person) {
        if (this.getMyPeasantsList().size() > 0) {
            this.getMyPeasantsList().get(0).setChief(person);
            person.getMyPeasantsList().add(this.getMyPeasantsList().get(0));
            person.getSubordinateList().add(this.getMyPeasantsList().get(0));
            System.out.println(this.getTitleAndName() + " presents " + this.getMyPeasantsList().get(0).getTitleAndName() + " to " + person.getTitleAndName() + ".");
            this.getSubordinateList().remove(this.getMyPeasantsList().get(0));
            this.getMyPeasantsList().remove(this.getMyPeasantsList().get(0));
        } else {
            System.out.println(this.getTitleAndName() + " doesnt present any peasant to " + person.getTitleAndName() + ", because he owns 0 peasants.");
        }
    }

    public void doAction(Human person, String action) {
        System.out.println(this.getTitleAndName() + " " + action + " " + person.getTitleAndName() + ".");
        this.changeHpAndAuthorityLevel(person);
    }
}
