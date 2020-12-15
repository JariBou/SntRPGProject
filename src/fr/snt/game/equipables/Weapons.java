package fr.snt.game.equipables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.lang.Float.parseFloat;


public class Weapons extends Equipables {
    private final int attack;
    private int burnLvl = 0;
    private int burn = 0;
    private float percentRatio = 0f;
    private float freezeChance = 0f;
    private float dmgMultiplier = 1.0f;
    private float vampRatio = 0.0f;
    private int lStealLvl = 0;

    public Weapons(String itemName) throws Exception {
        itemName += ".properties";
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(imPath + "\\src\\fr\\snt\\game\\equipables\\weapons\\" + itemName));
        } catch (IOException e) {
            throw new Exception("Unexpected Error \nFile " + itemName + " not found \nCannot load object: "
                    + itemName.replace(".properties", "") + "\n" + e);
        }
        this.name = properties.getProperty("name");
        this.attack = parseInt(properties.getProperty("attack"));
        this.cost = parseInt(properties.getProperty("cost"));
        this.description = properties.getProperty("description");
        if (Boolean.parseBoolean(properties.getProperty("spEffect"))) {
            this.spEffect = true;
            // TODO:Add other effects and loaders
            switch (properties.getProperty("spEffectType")) {
                case "burn" -> {
                    this.spEffectType = "burn";
                    this.burn = parseInt(properties.getProperty("burn"));
                    this.burnLvl = parseInt(properties.getProperty("burnLvl"));
                }
                case "percent" -> {
                    this.spEffectType = "percent";
                    this.dmgMultiplier = parseInt(properties.getProperty("dmgMultiplier"));
                }
                case "freeze" -> {
                    this.spEffectType = "freeze";
                    this.freezeChance = parseFloat(properties.getProperty("freezeChance"));
                }
                case "percentDmg" -> {
                    this.spEffectType = "percentDmg";
                    this.percentRatio = parseFloat(properties.getProperty("percentRatio"));
                }
                case "vampiric" -> {
                    this.spEffectType = "vampiric";
                    this.vampRatio = parseFloat(properties.getProperty("vampRatio"));
                }
                case "lSteal" -> {
                    this.spEffectType = "lSteal";
                    this.lStealLvl = parseInt(properties.getProperty("lStealLvl"));

                }
            }
        }
    }


    public int getAttack() {
        return attack;
    }

    public int getBurnLvl() {
        return burnLvl;
    }

    public int getBurn() {
        return burn;
    }

    public float getDmgMultiplier() {
        return dmgMultiplier;
    }

    public float getFreezeChance() {
        return freezeChance;
    }

    public float getPercentRatio() {
        return percentRatio;
    }

    public float getVampRatio(){
        return vampRatio;
    }

    public int getLStealLvl() {
        return lStealLvl;
    }

}
