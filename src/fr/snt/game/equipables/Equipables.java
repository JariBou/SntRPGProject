package fr.snt.game.equipables;

public abstract class Equipables {
    String name, spEffectDescription, spEffectType;
    int cost;
    boolean spEffect = false;

    //TODO: maybe keep equipables stats in a .properties file?

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

    public String getSpEffectDescription() {
        return spEffectDescription;
    }

    public void setSpEffectDescription(String description) {
        this.spEffectDescription = description;
    }

}
