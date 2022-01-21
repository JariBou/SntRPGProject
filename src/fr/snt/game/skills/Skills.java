package fr.snt.game.skills;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class Skills {

    // Commons
        //if lvl >= 5 also upgrades for 2 percent of base atk
    private final Skill ATTACK_UP = new Skill("ATTACK_UP", 1, 2,
            "Upgrades attack", "common", 1);
        //if lvl >= 5 also upgrades for 3 percent of base armor
    private final Skill ARMOR_UP = new Skill("ARMOR_UP", 1, 3,
            "Upgrades base armor", "common", 1);;;;;;;;;;;

    // Rare
    private final Skill MIDAS = new Skill("MIDAS", 1, 0.15,
            "Increases the amount of gold dained by defeating ennemies", "rare", 2, 5) {
        @Override
        public double getValue() {
            return 1 + value * level;
        }
    };


    // Epic
        //if lvl >= 3 also dmgs for the weapon damage
    private final Skill MULTI_ATK = new Skill("MULTI_ATK", 1, 1,
            "Attacks once more for your base dmg (including skills)", "epic", 3, 3);


    // Legendary
    private final Skill ATK_MULT = new Skill("ATK_MULT", 1, 0.1,
            "Multiplies your damage by 1 + skillValue * skillLvl", "legendary", 4, 7) {
        @Override
        public double getValue() {
            return 1 + value * level;
        }
    };
    private final Skill VAMPIRIC = new Skill("VAMPIRIC", 1, 0.07,
            "Heals you for skillValue*skillLvl*totalDamage", "legendary", 4, 5);


    // Utilities
    public static Map<String, Skill> skillList;
    public static Map<String, Skill> getSkillList(){
        if (skillList != null) {
            return skillList;
        }
        Skills sk = new Skills();
        skillList = new HashMap<>();
        for (Field field : Skills.class.getDeclaredFields()) {
            if (field.getName().equals("skillList")) {
                continue;
            }
            try {
                skillList.put(field.getName(), (Skill) field.get(sk));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return skillList;
    }

    public static Map<String, Skill> getSkillList2(){
        if (skillList != null) {
            return skillList;
        }
        skillList = new HashMap<>();


        // Commons
        //if lvl >= 5 also upgrades for 2 percent of base atk
        skillList.put("ATTACK_UP", new Skill("ATTACK_UP", 1, 2,
                "Upgrades attack", "common", 1));
        //if lvl >= 5 also upgrades for 3 percent of base armor
        skillList.put("ARMOR_UP", new Skill("ARMOR_UP", 1, 3,
                "Upgrades base armor", "common", 1));

        // Rare


        // Epic
        //if lvl >= 3 also dmgs for the weapon damage
        skillList.put("MULTI_ATK", new Skill("MULTI_ATK", 1, 1,
                "Attacks once more for your base dmg (including skills)", "epic", 3));


        // Legendary
        skillList.put("ATK_MULT", new Skill("ATK_MULT", 1, 0.1,
                "Multiplies your damage by 1 + skillValue * skillLvl", "legendary", 4) {
            @Override
            public double getValue() {
                return 1 + value * level;
            }
        });
        skillList.put("VAMPIRIC", new Skill("VAMPIRIC", 1, 0.07,
                "Heals you for skillValue*skillLvl*totalDamage", "legendary", 4));


        return skillList;
    }

    public Skills() {
    }

    public static Skill get(String name) {
        if (skillList == null) {
            return getSkillList().get(name);
        }
        return skillList.get(name);
    }

}
