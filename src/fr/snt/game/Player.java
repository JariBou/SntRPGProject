package fr.snt.game;

import fr.snt.game.enemies.Enemies;
import fr.snt.game.equipables.Equipables;
import fr.snt.game.equipables.armors.Armors;
import fr.snt.game.equipables.weapons.Weapons;
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
        if (this.hasWeapon() && Objects.requireNonNull(this.weapon).getSpEffectType().equals("percent")) {
            return (int) (attack * weapon.getDmgMultiplier());
        }
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
        } else {
            this.health -= attack;
        }
    }

    private int getTotalDamage() {
        if (this.hasWeapon()) {
            return this.getAttack() + weapon.getAttack();
        } else {
            return this.getAttack();
        }
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

        if (this.weapon.hasSpEffect() && this.weapon.getSpEffectType().equals("burn")) { //replace by a switch later on
            double spChance = rand();
            if (spChance < 0.2) { // 20% chance of burning
                target.setBurning(weapon.getBurn(), weapon.getBurnLvl());
            }
        }
        if (!target.dodged) {
            int totalDamage = this.getTotalDamage() - target.getArmor();
            if (target.guarded) {
                System.out.println(this.getName() + " attacked " + target.getName() + " for " + (totalDamage - target.getArmor()) + " damage!");
                target.damage(totalDamage - target.getArmor());
            } else {
                if (this.weapon.hasSpEffect() && this.weapon.getSpEffectType().equals("burn")) { //replace by a switch later on
                    double spChance = rand();
                    if (spChance < 0.2) { // 20% chance of burning
                        target.setBurning(weapon.getBurn(), weapon.getBurnLvl());
                    }
                }
                target.damage(totalDamage);
                System.out.println(this.getName() + " attacked " + target.getName() + " for " + totalDamage + " damage!");
                if (!target.isAlive()) {
                    System.out.println(target.getName() + " is dead!");
                    this.addGold(target.getGoldValue());
                    System.out.println("You were awarded with " + target.getGoldValue() + "gold!");
                }
            }
        }
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
