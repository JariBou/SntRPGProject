package fr.snt.game.equipables;

import java.nio.file.Path;
import java.nio.file.Paths;


public abstract class Equipables {
    protected String itemName;
    protected String name, description, spEffectType;
    protected int cost;
    protected boolean spEffect = false;
    final Path currentRelativePath = Paths.get("");
    protected final String imPath = currentRelativePath.toAbsolutePath().toString();

    public String getName() {
        return name;
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

}
