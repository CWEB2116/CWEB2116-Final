package FantasyManager.model;

public class Phoenix extends Creature {
    private static final long serialVersionUID = 1L;
    private int rebirthCount; // unique attribute

    public Phoenix(String name, int age, int rebirthCount) {
        super(name, age, "Phoenix");
        this.rebirthCount = rebirthCount;
    }

    public int getRebirthCount() {
        return rebirthCount;
    }

    public void setRebirthCount(int rebirthCount) {
        this.rebirthCount = rebirthCount;
    }

    @Override
    public void performAbility() {
        System.out.println(getName() + " resurrects from its ashes! (Rebirth count: " + rebirthCount + ")");
    }

    @Override
    public String getUniqueAttributeDescription() {
        return "Rebirth Count: " + rebirthCount;
    }

    @Override
    public String toString() {
        return super.toString() + ", Rebirth Count: " + rebirthCount;
    }
}