package fr.snt.game.enemies;

import fr.snt.game.Player;


abstract public class Enemies {
    // Dodge mechanic
    public boolean dodged = false;
    public boolean guarded = false;
    // Base attributes
    protected String name;
    protected int health, attack, armor, goldValue;
    // Burning mechanics
    protected int burn, burnLvl;
    //TODO: Implement burn mechanic on attack Player side

    public int getHealth() {
        return health;
    }

    protected double rand() {
        return Math.random();
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public void damage(int attack) {
        this.health -= attack;
    }

    public int getArmor() {
        return armor;
    }

    public void attack(Player target) {
        int totalDamage;
        if (target.hasArmor() && target.getArmor().hasSpEffect() && target.getArmor().getSpEffectType().equals("dmgDim")){
            totalDamage = this.getAttack() - target.getArmor().getDmgDim() - target.getBaseArmor();
        } else {
            totalDamage = this.getAttack() - target.getBaseArmor();
        }
        target.damage(totalDamage);
        System.out.println(this.getName() + " attacked " + target.getName() + " for " + totalDamage + " damage!");
    }

    public int getGoldValue() {
        return this.goldValue;
    }

    /**
     * @return true if target is Alive
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    public void update() {
        if (burn > 0) {
            this.health -= 2 * burnLvl;
            burn--;
        }
    }

    public void setBurning(int burn, int burnLvl) {
        this.burn = burn;
        this.burnLvl = burnLvl;
    }

}
