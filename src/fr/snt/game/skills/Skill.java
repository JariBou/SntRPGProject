package fr.snt.game.skills;


public class Skill {

    private final String name, parameter, description, rarity;
    private final int value;
    private int level;

    public Skill(String name, String parameter, int lvl, int value, String description, String rarity) {
        this.name = name;
        this.parameter = parameter;
        this.value = value;
        this.level = lvl;
        this.description = description;
        this.rarity = rarity;
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

    public int getValue() {
        return value * level;
    }

    public String getParameter() {
        return parameter;
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
