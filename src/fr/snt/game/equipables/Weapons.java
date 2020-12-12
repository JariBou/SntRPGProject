package fr.snt.game.equipables;

public class Weapons extends Equipables {
    private final int attack;
    private int burnLvl = 0;
    private int burn = 0;
    private float dmgMultiplier = 1.2f;

    public Weapons(String name, int attack, int cost) {
        this.name = name;
        this.attack = attack;
        this.cost = cost;
    }

    public Weapons(String name, int attack, int cost, boolean spEffect, String spEffectType) {
        this.name = name;
        this.attack = attack;
        this.cost = cost;
        this.spEffect = spEffect;
        this.spEffectType = spEffectType;
    }

    public Weapons(String name, int attack, int cost, boolean spEffect, String spEffectType, int burn, int burnLvl) {
        this.name = name;
        this.attack = attack;
        this.cost = cost;
        this.spEffect = spEffect;
        this.spEffectType = spEffectType;
        this.burnLvl = burnLvl;
        this.burn = burn;
    }

    public Weapons(String name, int attack, int cost, boolean spEffect, String spEffectType, float dmgMultiplier) {
        this.name = name;
        this.attack = attack;
        this.cost = cost;
        this.spEffect = spEffect;
        this.spEffectType = spEffectType;
        this.dmgMultiplier = dmgMultiplier;
    }

    public int getAttack() {
        return attack;
    }

    public int getBurnLvl() {
        return burnLvl;
    }

    public int getBurn() {
        return burn;
    }

    public float getDmgMultiplier(){
        return dmgMultiplier;
    }

}
