package FantasyManager.model;

import java.io.Serializable;

public abstract class Creature implements Serializable, Ability {
    private static final long serialVersionUID = 1L;  // For serialization
    private String name;
    private int age;
    private String type;

    public Creature(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    // Getters and setters (encapsulation)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    // Abstract method to be implemented by subclasses if needed
    public abstract String getUniqueAttributeDescription();

    @Override
    public String toString() {
        return "Name: " + name + ", Type: " + type + ", Age: " + age;
    }
}
