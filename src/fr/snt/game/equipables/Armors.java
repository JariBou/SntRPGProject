package fr.snt.game.equipables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;


public class Armors extends Equipables {
    private int armorValue;
    private int dmgDim, atkBonus = 0;
    private float wallRatio = 1.0f;
    private float lsRatio = 0f;

    public Armors(String itemName) throws Exception {
        if (itemName.equals("null")){
            return;
        }
        this.itemName = itemName.replace(".properties", "");
        itemName += itemName.endsWith(".properties") ? "" : ".properties";
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
        this.imgsrc = properties.getProperty("imgsrc");
        if (Boolean.parseBoolean(properties.getProperty("spEffect"))) {
            this.spEffect = true;
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
                case "wall" -> {
                    this.spEffectType = "wall";
                    this.wallRatio = parseFloat(properties.getProperty("wallRatio"));
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

    public float getWallRatio(){
        return this.wallRatio;
    }

    public ArrayList<String> getSPs(){
        if (this.hasSpEffect()){
            ArrayList<String> array = new ArrayList<>();
            switch (this.spEffectType){
                case "dmgDim" -> array.add("dmg diminution: " + this.getDmgDim() + "dmg");
                case "atkBonus" -> array.add("atk bonus: " + this.getAtkBonus() + "dmg");
                case "lastStand" -> array.add("last stand ratio: " + this.getLsRatio());
                case "wall" -> array.add("wall ratio: " + this.getWallRatio());
            }
            return array;
        } else {
            return new ArrayList<>();
        }
    }

}
