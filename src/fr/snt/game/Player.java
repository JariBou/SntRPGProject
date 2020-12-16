package fr.snt.game;

import fr.snt.game.enemies.Enemies;
import fr.snt.game.equipables.Equipables;
import fr.snt.game.equipables.Armors;
import fr.snt.game.equipables.Weapons;
import fr.snt.game.levels.GameOverLevel;

import java.util.ArrayList;
import java.util.Objects;


public class Player {
    private final String name;
    private final int baseArmor;
    private final ArrayList<Equipables> Inventory;
    private int health, maxHealth, attack, gold, playerLevel;
    private Weapons weapon;
    private Armors Armor;
    // Player Levels and poison mechanics
    private int levelCount, poison, poisonLevel;

    public Player(String name, int maxHealth, int attack, int armor) {
        this.Inventory = new ArrayList<Equipables>();
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.baseArmor = armor;
        this.weapon = null;
        this.Armor = null;
        this.gold = 0;
        this.playerLevel = 1;
    }

    private double rand() {
        return Math.random();
    }

    public String getName() {
        return name;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
        System.out.println("Successfully equipped '" + weapon.getName() + "'!");
    }

    public int getGold() {
        return gold;
    }

    private void addGold(int amount) {
        this.gold += amount;
    }

    public void removeGold(int amount) {
        this.gold -= amount;
    }

    private int getMaxHealth() {
        return maxHealth;
    }

    private void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    private void levelUp() {
        this.setMaxHealth(getMaxHealth() + 3);
        levelCount++;
        playerLevel++;
        if (levelCount == 4) {
            levelCount = 0;
            this.setAttack(getAttack() + 1);
        }
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    private void resetHealth() {
        this.health = this.maxHealth;
    }

    /**
     * To be called at the end of a level
     */
    public void endLevel() {
        setPoison(0, 0);
        levelUp();
        resetHealth();
    }

    private boolean hasWeapon() {
        return this.weapon != null;
    }

    public Armors getArmor() {
        return Armor;
    }

    public void setArmor(Armors armor) {
        this.Armor = armor;
    }

    public boolean hasArmor() {
        return this.Armor != null;
    }

    public int getBaseArmor() {
        if (hasArmor()) {
            return this.baseArmor + this.Armor.getArmorValue();
        } else {
            return this.baseArmor;
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void damage(int attack) {

        if (this.getArmor().hasSpEffect()) {
            if (this.getArmor().getSpEffectType().equals("block")) {
                double blockChance = rand();
                if (blockChance < 0.15) { // 15% chance of dodging
                    return;
                }
            }
        }
        this.health -= attack;

    }

    private int getDamage() {
        if (this.hasWeapon()) {
            if (this.weapon.hasSpEffect() && Objects.requireNonNull(this.weapon).getSpEffectType().equals("percent")) {
                return (int) ((attack + weapon.getAttack()) * weapon.getDmgMultiplier());
            } else {
                return this.getAttack() + weapon.getAttack();
            }
        } else {
            return this.getAttack();
        }
    }

    private float getPercentHealth() {
        return (float) (this.health) / (float) (this.getMaxHealth());
    }

    public void setPoison(int turns, int poisonLevel) {
        this.poison = turns;
        this.poisonLevel = poisonLevel;
    }

    /**
     * To call at the end of EACH turn
     */
    public void update() throws Exception {
        if (isDead()) {
            new GameOverLevel();
        } else if (poison > 0) {
            this.health -= poisonLevel;
            poison--;
        }
    }

    public void attack(Enemies target) {
        if (!target.hasDodged()) {
            int totalDamage = getTotalDamage(target);
            target.damage(totalDamage);
            this.heal((int)(totalDamage * this.weapon.getVampRatio()));      //Implementation for LifeSteal
            System.out.println(this.getName() + " attacked " + target.getName() + " for " + totalDamage + " damage!");
            if (!target.isAlive()) {
                System.out.println(target.getName() + " is dead!");
                this.addGold(target.getGoldValue());
                System.out.println("You were awarded with " + target.getGoldValue() + "gold!");
            }
        }
    }

    public int getTotalDamage(Enemies target) {
        int totalDamage = this.getDamage() - target.getArmor();
        if (target.hasGuarded()) {
            System.out.println(this.getName() + " attacked " + target.getName() + " for " + (totalDamage - target.getArmor()) + " damage!");
            return (totalDamage - target.getArmor());
        } else {
            if (this.hasWeapon() && this.weapon.hasSpEffect()) {
                String spType = this.weapon.getSpEffectType();
                double spChance = rand();
                switch (spType) {
                    case "burn":
                        if (spChance < 0.2) { // 20% chance of burning
                            target.setBurning(weapon.getBurn(), weapon.getBurnLvl());
                        }
                        break;
                    case "freeze":
                        if (spChance < this.weapon.getFreezeChance()) {
                            target.setFrozen(3);
                        }
                        break;
                    case "percentDmg":
                        totalDamage = (int) ((1 + (target.getPercentMissingHealth() * this.weapon.getPercentRatio()))
                                * totalDamage); // to be balanced
                        break;
                    case "lSteal":
                        target.damage(this.weapon.getLStealLvl());
                        this.heal(this.weapon.getLStealLvl());
                }
            }
            if (this.hasArmor() && this.Armor.hasSpEffect()) {
                if (this.Armor.getSpEffectType().equals("lastStand")) {
                    return (int) ((1 + (this.getPercentMissingHealth() * this.Armor.getLsRatio())) * totalDamage);
                } else if (this.Armor.getSpEffectType().equals("wall")) {
                    float ratio = this.getPercentHealth() * this.Armor.getWallRatio();
                    if (ratio >= 0.20){
                        return  (int) (totalDamage * (this.getPercentHealth() * this.Armor.getWallRatio()));
                    } else{
                        return  (int) (totalDamage * 0.20);
                    }
                } else if (this.Armor.getSpEffectType().equals("atkBonus")){
                    return totalDamage + this.Armor.getAtkBonus();
                }
            }
            return totalDamage;
        }
    }

    private void heal(int amount) {
        this.health += amount;
    }

    public int getMissingHealth() {
        return this.maxHealth - this.health;
    }

    public float getPercentMissingHealth() {
        return ((float) this.getMissingHealth() / (float) this.maxHealth);
    }

    /**
     * @return true if target is dead
     */
    public boolean isDead() {
        if (this.health <= 0) {
            System.out.println(this.getName() + " is dead!");
            return true;
        } else {
            return false;
        }
    }

    //Inventory Management Functions -----------------------
    public void addToInventory(Equipables item) {
        this.Inventory.add(item);
    }

    public void removeFromPosInventory(int position) {
        this.Inventory.remove(position);
    }

    public void removeItemFromInventory(Equipables item) {
        this.Inventory.remove(item);
    }

    public void clearInventory() {
        this.Inventory.clear();
    }

    public Equipables getItemInInventory(int position) {
        return this.Inventory.get(position);
    }

    public ArrayList<Equipables> getInventory() {
        return this.Inventory;
    }

    public boolean isItemInInventory(Equipables item) {
        return this.Inventory.contains(item);
    }
    //------------------------------------------------------
}
