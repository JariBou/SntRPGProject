package fr.snt.game.equipables;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public abstract class Equipables {
    protected String itemName;
    protected String name, description, spEffectType;
    protected int cost;
    protected boolean spEffect = false;
    final Path currentRelativePath = Paths.get("");
    protected final String imPath = currentRelativePath.toAbsolutePath().toString();
    protected String imgsrc;

    public String getName() {
        return name;
    }

    public String getImgsrc() {
        return imgsrc != null ? imgsrc : "/src/assets/images/weapons/botrk.png";
    }

    public int getCost() {
        return cost;
    }

    public boolean hasSpEffect() {
        return spEffect;
    }

    public String getSpEffectType() {
        return spEffectType;
    }

    public String getDescription() {
        return description;
    }

    public String getItemName(){
        return this.itemName;
    }

    public static ArrayList<String> getArmorsList() throws Exception {
        File f = new File("src\\fr\\snt\\game\\equipables\\armors");
        File[] listFiles = f.listFiles();
        ArrayList<String> armorFiles = new ArrayList<>();
        try {
            assert listFiles != null;
            for (File s : listFiles) {
                if (!s.getName().endsWith("example.properties")) {
                    armorFiles.add(s.getName().replace(".properties", ""));
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while loading Armors: " + e);
        }
        return armorFiles;
    }

    public static ArrayList<String> getWeaponsList() throws Exception {
        File f = new File("src\\fr\\snt\\game\\equipables\\weapons");
        File[] listFiles = f.listFiles();
        ArrayList<String> weaponFiles = new ArrayList<>();
        try {
            assert listFiles != null;
            for (File s : listFiles) {
                if (!s.getName().endsWith("example.properties")) {
                    weaponFiles.add(s.getName().replace(".properties", ""));
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while loading Weapons: " + e);
        }
        return weaponFiles;
    }


    public static Equipables get(String itemname) throws Exception {
        if (getWeaponsList().contains(itemname)) {return new Weapons(itemname);}
        else if (getArmorsList().contains(itemname)) {return new Armors(itemname);}
        return null;
    }
}
