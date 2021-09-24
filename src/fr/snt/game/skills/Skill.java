package fr.snt.game.skills;


public class Skill {

    private final String name, description, rarity;
    protected final double value;
    protected int level;
    private final int pointsRequired, maxLvl;

    public Skill(String name, int lvl, double value, String description, String rarity, int pointsRequired) {
        this.name = name;
        this.value = value;
        this.level = lvl;
        this.description = description;
        this.rarity = rarity;
        this.pointsRequired = pointsRequired;
        this.maxLvl = 10;
    }

    public Skill(String name, int lvl, double value, String description, String rarity, int pointsRequired, int maxLvl) {
        this.name = name;
        this.value = value;
        this.level = lvl;
        this.description = description;
        this.rarity = rarity;
        this.pointsRequired = pointsRequired;
        this.maxLvl = maxLvl;
    }

    public boolean lvlUp() {
        if (this.level < this.maxLvl) {
            this.level++;
            return true;
        } return false;
    }

    public boolean levelUp(int amount) {
        if (this.level+amount <= this.maxLvl) {
            this.level += amount;
            return true;
        } return false;
    }

    public boolean lvlDown() {
        if (this.level > 1) {
            this.level--;
            return true;
        } return false;
    }

    public boolean levelDown(int amount) {
        if (this.level-amount > 0) {
            this.level -= amount;
            return true;
        } return false;
    }

    public double getValue() {
        return value * level;
    }

    public int getPointsRequired() {
        return pointsRequired;
    }

    public int getPointsRequired(int number_of_lvls) {
        return pointsRequired * number_of_lvls;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public String getRarity() {
        return rarity;
    }

}
