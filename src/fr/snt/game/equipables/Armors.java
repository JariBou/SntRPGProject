package fr.snt.game.equipables;

public class Armors extends Equipables {
    private final int armorValue;

    public Armors(String name, int armorValue, int cost) {
        this.name = name;
        this.armorValue = armorValue;
        this.cost = cost;
    }

    public Armors(String name, int armorValue, int cost, boolean spEffect, String spEffectType) {
        this.name = name;
        this.armorValue = armorValue;
        this.cost = cost;
        this.spEffect = spEffect;
        this.spEffectType = spEffectType;
    }

    public int getArmorValue() {
        return armorValue;
    }

}
