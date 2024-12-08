package FantasyManager.model;

public class Dragon extends Creature {
    private static final long serialVersionUID = 1L;
    private int firePower;  // unique attribute

    public Dragon(String name, int age, int firePower) {
        super(name, age, "Dragon");
        this.firePower = firePower;
    }

    public int getFirePower() {
        return firePower;
    }

    public void setFirePower(int firePower) {
        this.firePower = firePower;
    }

    @Override
    public void performAbility() {
        System.out.println(getName() + " breathes fire with power " + firePower + "!");
    }

    @Override
    public String getUniqueAttributeDescription() {
        return "Fire Power: " + firePower;
    }

    @Override
    public String toString() {
        return super.toString() + ", Fire Power: " + firePower;
    }
}