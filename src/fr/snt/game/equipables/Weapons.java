package fr.snt.game.equipables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.lang.Float.parseFloat;


public class Weapons extends Equipables {
    private int attack;
    private int burnLvl = 0;
    private int burn = 0;
    private float percentRatio = 0f;
    private int freezeTurns = 0;
    private float freezeChance = 0f;
    private float dmgMultiplier = 1.0f;
    private float vampRatio = 0.0f;
    private int lStealLvl = 0;
    private int paraTurns = 0;
    private float paraChance = 0.0f;

    public Weapons(String itemName) throws Exception {
        if (itemName.equals("null")){return;}
        this.itemName = itemName.replace(".properties", "");
        itemName += itemName.endsWith(".properties") ? "" : ".properties";
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
        this.imgsrc = properties.getProperty("imgsrc");
        if (Boolean.parseBoolean(properties.getProperty("spEffect"))) {
            this.spEffect = true;
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
                    this.freezeTurns = parseInt(properties.getProperty("freezeTurns"));
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
                case "thunder" -> {
                    this.spEffectType = "thunder";
                    this.paraTurns = parseInt(properties.getProperty("paraTurns"));
                    this.paraChance = parseFloat(properties.getProperty("paraChance"));
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

    public int getFreezeTurns() {
        return freezeTurns;
    }

    public int getThunderTurns() {
        return paraTurns;
    }

    public float getThunderChance() {
        return paraChance;
    }

    public ArrayList<String> getSPs(){
        if (this.hasSpEffect()){
            ArrayList<String> array = new ArrayList<>();
            switch (this.spEffectType){
                case "burn" -> {
                    array.add("burn lvl: " + this.getBurnLvl());
                    array.add("burn turns: " + this.getBurn());
                }case "freeze" -> {
                    array.add("freeze chance: " + this.getFreezeChance()*100 +"%");
                    array.add("freeze turns: " + this.getFreezeTurns());
                }case "thunder" -> {
                    array.add("para chance: " + this.getThunderChance()*100 + "%");
                    array.add("para turns: " + this.getThunderTurns());
                }
                case "percent" -> array.add("dmg multiplier: " + this.getDmgMultiplier());
                case "percentDmg" -> array.add("percentHealthRatio: " + this.getPercentRatio());
                case "vampiric" -> array.add("vampiric ratio: " + this.getVampRatio());
                case "lSteal" -> array.add("life steal: " + this.getLStealLvl() + " hp");
            }
            return array;
        } else {
            return new ArrayList<>();
        }
    }

}
