package FantasyManager.manager;

import FantasyManager.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class CreatureManager {
    private List<Creature> creatures;

    public CreatureManager() {
        creatures = new ArrayList<>();
    }

    public void addCreature(Creature c) {
        creatures.add(c);
    }

    public boolean removeCreature(String name) {
        return creatures.removeIf(c -> c.getName().equalsIgnoreCase(name));
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public Creature findCreatureByName(String name) {
        for (Creature c : creatures) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public List<Creature> filterByType(String type) {
        return creatures.stream()
                .filter(c -> c.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public double averageAge() {
        return creatures.stream()
                .mapToInt(Creature::getAge)
                .average()
                .orElse(0.0);
    }

    public Map<String, Long> typeDistribution() {
        return creatures.stream()
                .collect(Collectors.groupingBy(Creature::getType, Collectors.counting()));
    }

    public void sortByAge() {
        creatures.sort(Comparator.comparingInt(Creature::getAge));
    }

    public void editCreatureAttributes(String name, Scanner scanner) {
        Creature c = findCreatureByName(name);
        if (c == null) {
            System.out.println("Creature not found.");
            return;
        }

        System.out.println("Current: " + c);
        System.out.println("Editing attributes. Press Enter to skip a field.");

        System.out.print("New name (currently " + c.getName() + "): ");
        String newName = scanner.nextLine().trim();
        if(!newName.isEmpty()) {
            c.setName(newName);
        }

        System.out.print("New age (currently " + c.getAge() + "): ");
        String newAgeStr = scanner.nextLine().trim();
        if(!newAgeStr.isEmpty()) {
            try {
                int newAge = Integer.parseInt(newAgeStr);
                c.setAge(newAge);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Not changed.");
            }
        }

        // For unique attributes:
        if (c instanceof Dragon) {
            Dragon d = (Dragon)c;
            System.out.print("New fire power (currently " + d.getFirePower() + "): ");
            String newPower = scanner.nextLine().trim();
            if(!newPower.isEmpty()) {
                try {
                    d.setFirePower(Integer.parseInt(newPower));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid fire power. Not changed.");
                }
            }
        } else if (c instanceof Unicorn) {
            Unicorn u = (Unicorn)c;
            System.out.print("New horn color (currently " + u.getHornColor() + "): ");
            String newHorn = scanner.nextLine().trim();
            if(!newHorn.isEmpty()) {
                u.setHornColor(newHorn);
            }
        } else if (c instanceof Phoenix) {
            Phoenix p = (Phoenix)c;
            System.out.print("New rebirth count (currently " + p.getRebirthCount() + "): ");
            String newCount = scanner.nextLine().trim();
            if(!newCount.isEmpty()) {
                try {
                    p.setRebirthCount(Integer.parseInt(newCount));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid rebirth count. Not changed.");
                }
            }
        }

        System.out.println("Creature updated: " + c);
    }

    public void performAllAbilitiesConcurrently() {
        List<Thread> threads = new ArrayList<>();
        for (Creature c : creatures) {
            Thread t = new Thread(c::performAbility);
            threads.add(t);
            t.start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
        System.out.println("All abilities performed.");
    }
}
