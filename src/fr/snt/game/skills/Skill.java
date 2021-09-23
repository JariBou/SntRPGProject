package fr.snt.game.skills;


public class Skill {

    private final String name, description, rarity;
    protected final double value;
    protected int level;
    private final int pointsRequired;

    public Skill(String name, int lvl, double value, String description, String rarity, int pointsRequired) {
        this.name = name;
        this.value = value;
        this.level = lvl;
        this.description = description;
        this.rarity = rarity;
        this.pointsRequired = pointsRequired;
    }

    public void lvlUp() {
        this.level++;
    }

    public void levelUp(int amount) {
        this.level += amount;
    }

    public void lvlDown() {
        this.level--;
    }

    public void levelDown(int amount) {
        this.level -= amount;
    }

    public double getValue() {
        return value * level;
    }

    public int getPointsRequired() {
        return pointsRequired;
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
