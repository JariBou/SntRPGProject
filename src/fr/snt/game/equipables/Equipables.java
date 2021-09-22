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
    protected ArrayList<String> weaponFiles, armorFiles;
    private boolean listsInitialized;
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

    public void loadItemNames() throws Exception {
        File f = new File("src\\fr\\snt\\game\\equipables\\armors");
        File[] listFiles = f.listFiles();
        armorFiles = new ArrayList<>();
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
        f = new File("src\\fr\\snt\\game\\equipables\\weapons");
        listFiles = f.listFiles();
        weaponFiles = new ArrayList<>();
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
    }

    public static Equipables get(String itemname) {
        if (!this.listsInitialized) {this.loadItemNames();}

}
