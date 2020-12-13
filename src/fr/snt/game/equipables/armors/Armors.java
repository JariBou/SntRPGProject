package fr.snt.game.equipables.armors;

import fr.snt.game.equipables.Equipables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;


public class Armors extends Equipables {
    private final int armorValue;
    private int dmgDim, atkBonus = 0;
    private float lsRatio = 0f;

    public Armors(String itemName) throws Exception {
        itemName += ".properties";
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(imPath + "\\src\\fr\\snt\\game\\equipables\\armors\\" + itemName));
        } catch (IOException e) {
            throw new Exception("Unexpected Error \nFile " + itemName + " not found \nCannot load object: "
                    + itemName.replace(".properties", "") + "\n" + e);
        }
        this.name = properties.getProperty("name");
        this.armorValue = parseInt(properties.getProperty("armorValue"));
        this.cost = parseInt(properties.getProperty("cost"));
        this.description = properties.getProperty("description");
        if (Boolean.getBoolean(properties.getProperty("spEffect"))) {
            // TODO:Add other effects and loaders
            switch (properties.getProperty("spEffectType")) {
                case "dmgDim" -> {
                    this.spEffectType = "dmgDim";
                    this.dmgDim = parseInt(properties.getProperty("dmgDim"));
                }
                case "atkBonus" -> {
                    this.spEffectType = "atkBonus";
                    this.atkBonus = parseInt(properties.getProperty("atkBonus"));
                }
                case "lastStand" -> {
                    this.spEffectType = "lastStand";
                    this.lsRatio = parseFloat(properties.getProperty("lsRatio"));
                }
            }
        }
    }

    public int getArmorValue() {
        return armorValue;
    }

    public int getAtkBonus() {
        return atkBonus;
    }

    public int getDmgDim() {
        return dmgDim;
    }

    public float getLsRatio(){
        return lsRatio;
    }

}
