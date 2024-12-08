package FantasyManager.model;

public class Unicorn extends Creature {
    private static final long serialVersionUID = 1L;
    private String hornColor; // unique attribute

    public Unicorn(String name, int age, String hornColor) {
        super(name, age, "Unicorn");
        this.hornColor = hornColor;
    }

    public String getHornColor() {
        return hornColor;
    }

    public void setHornColor(String hornColor) {
        this.hornColor = hornColor;
    }

    @Override
    public void performAbility() {
        System.out.println(getName() + " heals the forest with its " + hornColor + " horn!");
    }

    @Override
    public String getUniqueAttributeDescription() {
        return "Horn Color: " + hornColor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Horn Color: " + hornColor;
    }
}
