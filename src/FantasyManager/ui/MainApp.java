package FantasyManager.ui;

import FantasyManager.manager.CreatureManager;
import FantasyManager.manager.FileHandler;
import FantasyManager.model.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainApp {
    private static final String DATA_FILE = "creatures.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CreatureManager manager = new CreatureManager();

        // Attempt to load existing data
        List<Creature> loaded = FileHandler.loadData(DATA_FILE);
        if (loaded != null) {
            loaded.forEach(manager::addCreature);
        }

        boolean exit = false;
        while (!exit) {
            printMenu();
            int option = readInt(scanner, "Choose an option: ");
            switch (option) {
                case 1:
                    addCreatureUI(manager, scanner);
                    break;
                case 2:
                    removeCreatureUI(manager, scanner);
                    break;
                case 3:
                    displayCreatures(manager);
                    break;
                case 4:
                    filterByTypeUI(manager, scanner);
                    break;
                case 5:
                    showStatistics(manager);
                    break;
                case 6:
                    FileHandler.saveData(manager.getCreatures(), DATA_FILE);
                    break;
                case 7:
                    List<Creature> freshLoad = FileHandler.loadData(DATA_FILE);
                    if (freshLoad != null) {
                        manager.getCreatures().clear();
                        manager.getCreatures().addAll(freshLoad);
                    }
                    break;
                case 8:
                    exit = true;
                    break;
                case 9:
                    // Edit creature
                    editCreatureUI(manager, scanner);
                    break;
                case 10:
                    // Perform abilities concurrently
                    manager.performAllAbilitiesConcurrently();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
        System.out.println("Exiting system. Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n--- Creature Management ---");
        System.out.println("1. Add Creature");
        System.out.println("2. Remove Creature");
        System.out.println("3. Display Creatures");
        System.out.println("4. Filter by Type");
        System.out.println("5. Show Statistics");
        System.out.println("6. Save Data");
        System.out.println("7. Load Data");
        System.out.println("8. Exit");
        System.out.println("9. Edit Creature");
        System.out.println("10. Perform Abilities (Multithreaded)");
    }

    private static void addCreatureUI(CreatureManager manager, Scanner scanner) {
        System.out.print("Enter type (Dragon/Unicorn/Phoenix): ");
        String type = scanner.nextLine().trim();
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        int age = readInt(scanner, "Enter age: ");

        switch (type.toLowerCase()) {
            case "dragon":
                int fp = readInt(scanner, "Enter fire power: ");
                manager.addCreature(new Dragon(name, age, fp));
                System.out.println("Creature added successfully.");
                break;
            case "unicorn":
                System.out.print("Enter horn color: ");
                String hc = scanner.nextLine().trim();
                manager.addCreature(new Unicorn(name, age, hc));
                System.out.println("Creature added successfully.");
                break;
            case "phoenix":
                int rc = readInt(scanner, "Enter rebirth count: ");
                manager.addCreature(new Phoenix(name, age, rc));
                System.out.println("Creature added successfully.");
                break;
            default:
                System.out.println("Invalid type.");
        }
    }

    private static void removeCreatureUI(CreatureManager manager, Scanner scanner) {
        System.out.print("Enter creature name to remove: ");
        String name = scanner.nextLine().trim();
        boolean removed = manager.removeCreature(name);
        if (removed) {
            System.out.println("Creature removed successfully.");
        } else {
            System.out.println("Creature not found.");
        }
    }

    private static void displayCreatures(CreatureManager manager) {
        List<Creature> all = manager.getCreatures();
        if (all.isEmpty()) {
            System.out.println("No creatures available.");
        } else {
            manager.sortByAge();
            for (Creature c : all) {
                System.out.println(c);
            }
        }
    }

    private static void filterByTypeUI(CreatureManager manager, Scanner scanner) {
        System.out.print("Enter type to filter (Dragon/Unicorn/Phoenix): ");
        String t = scanner.nextLine().trim();
        List<Creature> filtered = manager.filterByType(t);
        if (filtered.isEmpty()) {
            System.out.println("No creatures of that type found.");
        } else {
            for (Creature c : filtered) {
                System.out.println(c);
            }
        }
    }

    private static void showStatistics(CreatureManager manager) {
        double avgAge = manager.averageAge();
        Map<String, Long> dist = manager.typeDistribution();
        System.out.println("Average Age: " + avgAge);
        System.out.println("Type Distribution: ");
        for (Map.Entry<String, Long> e : dist.entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue());
        }
    }

    private static void editCreatureUI(CreatureManager manager, Scanner scanner) {
        System.out.print("Enter the creature name to edit: ");
        String name = scanner.nextLine().trim();
        manager.editCreatureAttributes(name, scanner);
    }

    private static int readInt(Scanner scanner, String prompt) {
        int val = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                val = Integer.parseInt(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
        return val;
    }
}