package fr.snt.game.equipables;

import java.nio.file.Path;
import java.nio.file.Paths;


public abstract class Equipables {
    protected String name, description, spEffectType;
    protected int cost;
    protected boolean spEffect = false;
    Path currentRelativePath = Paths.get("");
    protected String imPath = currentRelativePath.toAbsolutePath().toString();

    //TODO: maybe keep equipables stats in a .properties file? Yeah god idea, too much different initializers

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

}
