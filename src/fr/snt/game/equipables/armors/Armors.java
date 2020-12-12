package fr.snt.game.equipables.armors;

import fr.snt.game.equipables.Equipables;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static java.lang.Integer.parseInt;


public class Armors extends Equipables {
    private final int armorValue;
    private int dmgDim, atkBonus = 0;

    public Armors(String name){
        name+=".properties";
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(imPath + "\\src\\fr\\snt\\game\\equipables\\armors\\" + name));
        }catch (IOException e){
            System.out.println("Unexpected Error: " + e);
        }
        this.name = properties.getProperty("name");
        this.armorValue = parseInt(properties.getProperty("armorValue"));
        this.cost = parseInt(properties.getProperty("cost"));
        if (Boolean.getBoolean(properties.getProperty("spEffect"))){
            switch (properties.getProperty("spEffectType")) {
                case "dmgDim" -> {
                    this.spEffectType = "dmgDim";
                    this.dmgDim = parseInt(properties.getProperty("dmgDim"));
                }
                case "atkBonus" -> {
                    this.spEffectType = "atkBonus";
                    this.atkBonus = parseInt(properties.getProperty("atkBonus"));
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

}
