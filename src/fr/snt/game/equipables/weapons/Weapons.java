package fr.snt.game.equipables.weapons;

import fr.snt.game.equipables.Equipables;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;
import static java.lang.Integer.parseInt;


public class Weapons extends Equipables {
    private final int attack;
    private int burnLvl = 0;
    private int burn = 0;
    private float dmgMultiplier = 1.0f;

    public Weapons(String name) throws IOException {
        name+=".properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(imPath+"\\src\\fr\\snt\\game\\equipables\\weapons\\"+name));
        this.name = properties.getProperty("name");
        this.attack = parseInt(properties.getProperty("attack"));
        this.cost = parseInt(properties.getProperty("cost"));
        if (Boolean.parseBoolean(properties.getProperty("spEffect"))){
            this.spEffect = true;
            switch (properties.getProperty("spEffectType")){
                case "burn":
                    this.spEffectType = "burn";
                    this.burn = parseInt(properties.getProperty("burn"));
                    this.burnLvl = parseInt(properties.getProperty("burnLvl"));
                    break;

                case "percent":
                    this.spEffectType = "percent";
                    this.dmgMultiplier = parseInt(properties.getProperty("dmgMultiplier"));
                    break;
                    // Add other effects and loaders
            }
        }
    }

    public Weapons(String name, int attack, int cost) {
        this.name = name;
        this.attack = attack;
        this.cost = cost;
    }

    public Weapons(String name, int attack, int cost, boolean spEffect, String spEffectType) {
        this.name = name;
        this.attack = attack;
        this.cost = cost;
        this.spEffect = spEffect;
        this.spEffectType = spEffectType;
    }

    public Weapons(String name, int attack, int cost, boolean spEffect, String spEffectType, int burn, int burnLvl) {
        this.name = name;
        this.attack = attack;
        this.cost = cost;
        this.spEffect = spEffect;
        this.spEffectType = spEffectType;
        this.burnLvl = burnLvl;
        this.burn = burn;
    }

    public Weapons(String name, int attack, int cost, boolean spEffect, String spEffectType, float dmgMultiplier) {
        this.name = name;
        this.attack = attack;
        this.cost = cost;
        this.spEffect = spEffect;
        this.spEffectType = spEffectType;
        this.dmgMultiplier = dmgMultiplier;
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

    public float getDmgMultiplier(){
        return dmgMultiplier;
    }

}
