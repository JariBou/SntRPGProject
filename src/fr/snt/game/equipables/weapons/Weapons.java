package fr.snt.game.equipables.weapons;

import fr.snt.game.equipables.Equipables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;


public class Weapons extends Equipables {
    private final int attack;
    private int burnLvl = 0;
    private int burn = 0;
    private double freezeChance = 0f;
    private float dmgMultiplier = 1.0f;

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
                    this.freezeChance = parseDouble(properties.getProperty("freezeChance"));
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

    public double getFreezeChance() {
        return freezeChance;
    }

}
