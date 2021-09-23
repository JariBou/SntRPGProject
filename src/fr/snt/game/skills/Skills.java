package fr.snt.game.skills;

public class Skills {

    // Commons
    public static Skill ATTACK_UP = new Skill("ATTACK_UP", 1, 2,
            "Upgrades attack", "common", 1);
    public static Skill ARMOR_UP = new Skill("ARMOR_UP", 1, 3,
            "Upgrades base armor", "common", 1);


    // Rare


    // Epic
    public static Skill MULTI_ATK = new Skill("MULTI_ATK", 1, 1,
            "Attacks once more for your base dmg (including skills)", "epic", 3);


    // Legendary
    public static Skill ATK_MULT = new Skill("ATK_MULT", 1, 1.1,
            "Multiplies your damage by 1 + value/100", "legendary", 4) {
        @Override
        public double getValue() {
            return this.value + 0.1 * (level - 1);
        }
    };

}
