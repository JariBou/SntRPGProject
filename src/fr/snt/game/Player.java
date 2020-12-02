package fr.snt.game;

import fr.snt.game.enemies.Enemies;
import fr.snt.game.equipables.Armors;
import fr.snt.game.equipables.Equipables;
import fr.snt.game.equipables.Weapons;
import fr.snt.game.levels.GameOverLevel;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int health, maxHealth, attack, gold;
    private final int baseArmor;
    private Weapons weapon;
    private Armors Armor;
    private ArrayList<Equipables> Inventory;
    // Player Levels and poison mechanics
    private int levelCount, poison, poisonLevel;

    public Player(String name, int maxHealth, int attack, int armor){
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.baseArmor = armor;
        this.weapon = null;
        this.Armor = null;
        this.gold = 0;
    }

    public String getName() {
        return name;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
        System.out.println("Successfully equipped '" + weapon.getName() + "'!");
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount){
        this.gold += amount;
    }

    public void removeGold(int amount){
        this.gold -= amount;
    }

    private void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    private void levelUp(){
        this.setMaxHealth(getMaxHealth() + 3);
        levelCount++;
        if (levelCount == 4){
            levelCount = 0;
            this.setAttack(getAttack() + 1);
        }
    }

    private void resetHealth() {
        this.health = this.maxHealth;
    }

    /**
     *  To be called at the end of a level
     */
    public void endLevel(){
        setPoison(0, 0);
        levelUp();
        resetHealth();
    }

    public boolean hasWeapon(){
        return this.weapon != null;
    }

    public void setArmor(Armors armor){
        this.Armor = armor;
    }

    public Armors getArmor(){
        return Armor;
    }

    public boolean hasArmor(){
        return this.Armor != null;
    }

    public int getBaseArmor() {
        if (hasArmor()) {
            return this.baseArmor + this.Armor.getArmorValue();
        } else{
            return this.baseArmor;
        }
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void damage(int attack){
        this.health -= attack;
    }

    private int getTotalDamage(){
        if (this.hasWeapon()){
            return this.getAttack() + weapon.getAttack();
        } else{
            return this.getAttack();
        }
    }

    public void setPoison(int turns, int poisonLevel){
        this.poison = turns;
        this.poisonLevel = poisonLevel;
    }

    /**
     * To call at the end of EACH turn
     */
    public void update(){
        if (isDead()){
                new GameOverLevel(this);
            }
        else if (poison > 0){
            this.health -= poisonLevel;
            poison--;
        }
    }

    public void attack(Enemies target){
        int totalDamage = this.getTotalDamage() - target.getArmor();
        target.damage(totalDamage);
        if (!target.dodged) {
            if (target.guarded){
                System.out.println(this.getName() + " attacked " + target.getName() + " for " + (totalDamage - target.getArmor()) + " damage!");
            } else {
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
    public boolean isDead(){
        if (this.health <= 0){
            System.out.println(this.getName() + " is dead!");
            return true;
        } else{
            return false;
        }
    }

    //Inventory Managemen Functions -----------------------
    public void addToInventory(Equipables item){
        this.Inventory.add(item);
    }

    public void removeFromPosInventory(int position){
        this.Inventory.remove(position);
    }

    public void removeItemFromInventory(Equipables item){
        this.Inventory.remove(item);
    }

    public void clearInventory(){
        this.Inventory.clear();
    }

    public Equipables getItemInInventory(int position){
        return this.Inventory.get(position);
    }

    public ArrayList<Equipables> getInventory() {
        return this.Inventory;
    }

    public boolean isItemInInventory(Equipables item){
        return this.Inventory.contains(item);
    }
    //------------------------------------------------------
}
