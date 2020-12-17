package fr.snt.game.enemies;

import fr.snt.game.Player;


abstract public class Enemies {
    // Base attributes
    protected String name;
    protected int health, attack, armor, goldValue, maxHealth;
    // Dodge and guard mechanic
    protected boolean dodged = false;
    protected boolean guarded = false;
    // Burning mechanics
    protected int burn, burnLvl;
    //  Freezing mechanics
    protected int frozenTurns = 0;
    // Paralyzed mechanics
    protected int paraTurns = 0;
    protected float paraChance = 0.0f;

    public Enemies(String name, int maxHealth, int attack, int armor, int goldValue) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = this.maxHealth;
        this.attack = attack;
        this.armor = armor;
        this.goldValue = goldValue;
    }

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
        if (frozenTurns > 0) {
            System.out.println(this.getName() + " was frozen, couldn't attack!");
            return;
        }
        if (paraTurns > 0) {
            if (paraChance < paraChance) {
                System.out.println(this.getName() + " was paralyzed, he failed to attack!");
                return;
            }
        }
        int totalDamage;
        if (target.hasArmor() && target.getArmor().hasSpEffect() && target.getArmor().getSpEffectType().equals("dmgDim")) {
            totalDamage = this.getAttack() - target.getArmor().getDmgDim() - target.getBaseArmor();
        } else {
            totalDamage = this.getAttack() - target.getBaseArmor();
        }
        target.damage(totalDamage);
        System.out.println(this.getName() + " attacked " + target.getName() + " for " + totalDamage + " damage!");

    }

    public float getMissingHealth() {
        return this.maxHealth - this.health;
    }

    public float getPercentMissingHealth() {
        return (this.getMissingHealth()) / (float) (this.maxHealth);
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
            this.health -= burnLvl;
            burn--;
        }
        if (frozenTurns > 0) {
            frozenTurns--;
        }
        if (paraTurns > 0) {
            paraTurns--;
        }
    }

    public void setBurning(int burn, int burnLvl) {
        this.burn = burn;
        this.burnLvl = burnLvl;
    }

    public void setFrozen(int frozenTurns) {
        this.frozenTurns = frozenTurns;
    }

    public void setParalyzed(int paraTurns) {
        this.paraTurns = paraTurns;
    }

    public boolean hasDodged() {
        return dodged;
    }

    public boolean hasGuarded() {
        return guarded;
    }

}
