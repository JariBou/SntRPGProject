package fr.snt.game.equipables;

public abstract class Equipables {
    protected String name, spEffectDescription, spEffectType;
    protected int cost;
    protected boolean spEffect = false;

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public boolean hasSpEffect(){
        return spEffect;
    }

    public String getSpEffectType(){
        return spEffectType;
    }

    public void setSpEffectDescription(String description){
        this.spEffectDescription = description;
    }

    public String getSpEffectDescription() {
        return spEffectDescription;
    }

}
