package fr.snt.game;

import fr.snt.game.enemies.Enemies;
import fr.snt.game.equipables.Equipables;
import fr.snt.game.equipables.Armors;
import fr.snt.game.equipables.Weapons;
import fr.snt.game.levels.GameOverLevel;
import fr.snt.game.skills.Skill;

import static assets.utils.UtilMethods.getType;
import static java.lang.Integer.parseInt;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


public class Player {
    private String name;
    private int baseArmor;
    private ArrayList<Equipables> Inventory = new ArrayList<>();
    private int health, maxHealth, attack, gold, playerLevel;
    private Weapons weapon;
    private Armors Armor;
    // Player Levels and poison mechanics
    private int levelCount, poison, poisonLevel;
    private final Map<String, Field> fields = new HashMap<>();
    private final Map<String, Skill> skills = new HashMap<>();

    public Player(String saveName) throws Exception {
        saveName += saveName.endsWith(".properties") ? "" : ".properties";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src\\fr\\snt\\game\\saves\\" + saveName));
        } catch (IOException e) {
            throw new Exception("Unexpected Error while loadind '" + saveName + "'\n" + e);
        }
        ArrayList<Object> initializers = new ArrayList<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getName().equals("fields")) {
                continue;
            }
            String[] typeList = field.getType().getName().split("\\.");
            String type = typeList[typeList.length - 1];
            System.out.println(type);
            String value = prop.getProperty(field.getName());

            switch (type) {
                case "int" -> field.set(this, parseInt(value));
                case "Weapons" -> field.set(this, !Objects.equals(value, "null") ? new Weapons(value) : null);
                case "Armors" -> field.set(this, !Objects.equals(value, "null") ? new Armors(value) : null);
                case "ArrayList" -> {
                    // Put a switch with the name if multiple array type stuff appear
                    String[] items = value.split(":");
                    this.Inventory = new ArrayList<>();
                    for (String item : items) {
                        item = item.trim();
                        // Maybe do a constructor in Equipables.java that detects based on the name wether it is
                        // a Weapons or Armors type item
                        Equipables equipable = Equipables.get(item);
                        if (equipable != null) {
                            this.addToInventory(equipable);
                        }
                    }
                }
                case "Map" -> {
                    //TODO

                    break;
                }
                default -> field.set(this, prop.getProperty(field.getName()));
            }

            //field.set(this, type.cast(prop.getProperty(field.getName())));
        }


//        this.name = prop.getProperty("name");
//        this.baseArmor = parseInt(prop.getProperty("baseArmor"));
//        this.maxHealth = parseInt(prop.getProperty("maxHealth"));
//        this.attack = parseInt(prop.getProperty("attack"));
//        String[] playerLvls = prop.getProperty("playerLvl").split(":");
//        this.playerLevel = parseInt(playerLvls[0]);
//        this.levelCount = parseInt(playerLvls[1]);
//        this.gold = parseInt(prop.getProperty("gold"));
//
//        this.weapon = new Weapons(prop.getProperty("currWeapon"));
//        this.Armor = new Armors(prop.getProperty("currArmor"));
//
//        // Inv -------------------------------------------------
//        this.Inventory = new ArrayList<>();
//        String[] weapons = prop.getProperty("Weapons").split(":");
//        for (String s : weapons){
//            this.Inventory.add(new Weapons(s.trim()));
//        }
//        String[] armors = prop.getProperty("Armors").split(":");
//        for (String s : armors){
//            this.Inventory.add(new Armors(s.trim()));
//        }
        this.initFieldsMap();
    }

    public Player(String name, int maxHealth, int attack, int armor) {
        this.Inventory = new ArrayList<>();
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

    public void removeWeapon() {
        this.weapon = null;
    }

    public void removeArmor() {
        this.Armor = null;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public void removeGold(int amount) {
        this.gold -= amount;
    }

    public int getMaxHealth() {
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

    public int getLevelCount() {
        return levelCount;
    }

    private void resetHealth() {
        this.health = this.maxHealth;
    }

    /**
     * To be called at the end of a level
     */
    public void endLevel() {
        resetStatuses();
        levelUp();
        resetHealth();
    }

    private void resetStatuses() {
        setPoison(0, 0);
        // add other statuses
    }

    public boolean hasWeapon() {
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
        int atk = attack;
        if (this.hasSkills()) {
            if (this.skills.containsKey("ATTACK_UP")) {
                atk += (int) (skills.get("ATTACK_UP").getValue());
                int skillLvl = skills.get("ATTACK_UP").getLevel();
                if (skillLvl >= 5) {
                    atk = (int) (atk * (1 + (skillLvl - 4) * 0.03));
                }
            }
        }
        return atk;
    }

    public boolean hasSkills() {
        return this.skills.size() > 0;
    }

    public void addSkill(Skill skill) {
        if (skills.containsKey(skill.getName())) {return;}
        skills.put(skill.getName(), skill);
    }

    public void upgradeSkill(String skillName) {
        skills.get(skillName).lvlUp();
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void damage(int attack) {
        if (this.hasArmor() && this.getArmor().hasSpEffect()) {
            if (this.getArmor().getSpEffectType().equals("block")) {
                double blockChance = rand();
                if (blockChance < 0.10) { // 10% chance of dodging
                    return;
                }
            }
        }
        this.health -= attack;
    }

    private int getDamage() {
        if (this.hasWeapon()) {
            if (this.weapon.hasSpEffect() && this.weapon.getSpEffectType().equals("percent")) {
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
     * To call at the end of EACH turn (?)
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
            this.heal((int) (totalDamage * this.weapon.getVampRatio()));      //Implementation for LifeSteal
            System.out.println(this.getName() + " attacked " + target.getName() + " for " + totalDamage + " damage!");

            if (this.hasSkills()) {
                if (this.skills.containsKey("MULTI_ATK")) {
                    int dmg = (int) (skills.get("ATK_MULT").getValue() * this.getAttack());
                    target.damage(dmg);
                    System.out.println(this.getName() + " attacked a second time " + target.getName() + " for " + dmg + " damage!");
                    System.out.println("Total of " + (totalDamage + dmg) + " damage!");
                }
            }

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
            totalDamage = this.applyStatuses(target, totalDamage);
            if (this.hasArmor() && this.Armor.hasSpEffect()) {
                switch (this.Armor.getSpEffectType()) {
                    case "lastStand" -> totalDamage = (int) ((1 + (this.getPercentMissingHealth() * this.Armor.getLsRatio())) * totalDamage);
                    case "wall" -> {
                        float ratio = this.getPercentHealth() * this.Armor.getWallRatio();
                        if (ratio >= 0.20) {
                            totalDamage = (int) (totalDamage * (this.getPercentHealth() * this.Armor.getWallRatio()));
                        } else {
                            totalDamage = (int) (totalDamage * 0.20);
                        }
                    }
                    case "atkBonus" -> totalDamage += this.Armor.getAtkBonus();
                }
            }
            if (this.hasSkills()) {
                if (this.skills.containsKey("ATK_MULT")) {
                    totalDamage = (int) (totalDamage * skills.get("ATK_MULT").getValue());
                }
            }
            return totalDamage;
        }
    }

    private int applyStatuses(Enemies target, int totalDamage) {
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
                        target.setFrozen(this.weapon.getFreezeTurns());
                    }
                    break;
                case "percentDmg":
                    totalDamage = (int) ((1 + (target.getPercentMissingHealth() * this.weapon.getPercentRatio()))
                            * totalDamage); // to be balanced
                    break;
                case "lSteal":
                    target.damage(this.weapon.getLStealLvl());
                    this.heal(this.weapon.getLStealLvl());
                    break;
                case "thunder":
                    if (spChance < this.weapon.getThunderChance()) {
                        target.setParalyzed(this.weapon.getThunderTurns());
                    }
                    break;
            }
        }
        return totalDamage;
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

    public void removeFromInventory(int position) {
        this.Inventory.remove(position);
    }

    public void removeFromInventory(Equipables item) {
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

    public ArrayList<Weapons> getWeapons() {
        ArrayList<Weapons> wList = new ArrayList<>();
        for (Equipables w : getInventory()) {
            if (getType(w.getClass()).equals("Weapons")) {
                wList.add((Weapons) w);
            }
        }
        return wList;
    }

    public ArrayList<Armors> getArmors() {
        ArrayList<Armors> aList = new ArrayList<>();
        for (Equipables w : getInventory()) {
            if (getType(w.getClass()).equals("Armors")) {
                aList.add((Armors) w);
            }
        }
        return aList;
    }

    public boolean isInInventory(Equipables item) {
        return this.Inventory.contains(item);
    }

    public ArrayList<String> getInventoryItemNames() {
        if (this.Inventory.size() < 1) {
            return new ArrayList<>(List.of(""));
        }
        ArrayList<String> array = new ArrayList<>();
        for (Equipables obj : this.Inventory) {
            array.add(obj.getItemName());
        }
        return array;
    }
    //------------------------------------------------------

    private void initFieldsMap() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (!field.getName().equals("fields")) {
                fields.put(field.getName(), field);
            }
        }
    }

    public Map<String, String> getStats() {
        Map<String, String> data = new HashMap<>();

        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                Object obj = field.get(this);
                String value = "null";
                String[] type;
                if (field.getName().equals("fields")) {
                    continue;
                }
                if (obj == null) {
                    type = field.getType().getName().split("\\.");
                    //System.out.println(field.getName() + "[" + type[type.length - 1] + "]" + ": " + value);
                    data.put(field.getName(), value);
                    continue;
                }
                switch (field.getName()) {
                    case "weapon" -> value = (obj.getClass() == Weapons.class) ? ((Weapons) obj).getItemName() : "null";
                    case "Armor" -> value = (obj.getClass() == Armors.class) ? ((Armors) obj).getItemName() : "null";
                    case "Inventory" -> {
                        String str = this.getInventoryItemNames().toString();
                        value = str.replace(",", ":").replace("[", "").replace("]", "");
                    }
                    default -> value = (field.get(this) != null) ? field.get(this).toString() : "null";
                }
                type = field.getType().getName().split("\\.");
                //System.out.println(field.getName() + "[" + type[type.length - 1] + "]" + ": " + value);
                data.put(field.getName(), value);
                //System.out.println(field.getName() + ": " + field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(data);
        return data;
    }

    public void printStats() {
        System.out.println("===========STATS==========");
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                Object obj = field.get(this);
                String value = "null";
                String[] type;
                if (field.getName().equals("fields")) {
                    continue;
                }
                if (obj == null) {
                    type = field.getType().getName().split("\\.");
                    System.out.println(field.getName() + "[" + type[type.length - 1] + "]" + ": " + value);
                    continue;
                }
                switch (field.getName()) {
                    case "weapon" -> {
                        value = (obj.getClass() == Weapons.class) ? ((Weapons) obj).getItemName() : "null";
                    }
                    case "Armor" -> {
                        value = (obj.getClass() == Armors.class) ? ((Armors) obj).getItemName() : "null";
                    }
                    case "Inventory" -> {
                        String str = this.getInventoryItemNames().toString();
                        value = str.replace(",", ":").replace("[", "").replace("]", "");
                    }
                    case "skills" -> {

                    }
                    default -> {
                        value = (field.get(this) != null) ? field.get(this).toString() : "null";
                    }
                }
                type = field.getType().getName().split("\\.");
                System.out.println(field.getName() + "[" + type[type.length - 1] + "]" + ": " + value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        System.out.println("==================");
    }

}
