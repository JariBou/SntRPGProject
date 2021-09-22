package fr.snt.game.skills;

public class Skills {

    // Commons
    public static Skill ATTACK_UP = new Skill("ATTACK_UP", "attack", 1, 3,
            "Upgrades attack", "common");
    public static Skill ARMOR_UP = new Skill("ARMOR_UP", "baseArmor", 1, 4,
            "Upgrades base armor", "common");


    // Rare


    // Epic
    public static Skill MULTI_ATK = new Skill("MULTI_ATK", "null", 1, 1,
            "Attacks once more for your base dmg (including skills)", "epic");


    // Legendary
    public static Skill ATK_MULT = new Skill("ATK_MULT", "null", 1, 10,
            "Multiplies your damage by 1 + value/100", "legendary");

}
