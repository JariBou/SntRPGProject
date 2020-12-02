package fr.snt.game.equipables;

public class Armors extends Equipables {
    private final int armorValue;

    public Armors(String name, int armorValue, int cost){
        this.name = name;
        this.armorValue = armorValue;
        this.cost = cost;
    }

    public int getArmorValue() {
        return armorValue;
    }

}
