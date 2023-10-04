import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {

    private final Adventure adventure;
    private final Pattern commandPattern;
    private final Player player;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        this.player = adventure.getPlayer();
        this.commandPattern = Pattern.compile("^(go (north|south|east|west)|look|inventory|take\\s\\w+|drop\\s\\w+|help|exit|health|eat\\s\\w+)$");
    }

    public void start() {
        printWelcomeMessage();
        printInitialInstructions();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().toLowerCase();

            if (validateCommand(input)) {
                handleCommand(input);
            } else {
                System.out.println("Invalid command. Type 'help' for available commands.");
            }
        }

    }

    private void printInitialInstructions() {
        System.out.println("You find yourself at the beginning of a journey. The path diverges before you.");
        System.out.println("What would you like to do? Type 'help' to see your options: ");
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("- look: View the current room description.");
        System.out.println("- inventory: View your inventory.");
        System.out.println("- take [item]: Take an item from the room.");
        System.out.println("- drop [item]: Drop an item from your inventory.");
        System.out.println("- go north/south/east/west: Move in the specified direction.");
        System.out.println("- help: Seek guidance on your quest.");
        System.out.println("- exit: Farewell and goodbye.");
    }

    private boolean validateCommand(String input) {
        Matcher matcher = commandPattern.matcher(input);
        return matcher.matches();
    }

    public void handleCommand(String input) {
        Room currentRoom = adventure.getCurrentRoom();
        String[] commandParts = input.split(" ", 2);
        String command = commandParts[0];
        String argument = commandParts.length > 1 ? commandParts[1] : "";

        switch (command) {
            case "look" -> {
                System.out.println("\nYou are in " + currentRoom.getName());
                System.out.println(currentRoom.getFullDescription());
            }
            case "inventory" -> showInventory();
            case "items" -> showItems();
            case "go" -> move(argument);
            case "help" -> printHelp();
            case "exit" -> exitGame();
            case "take" -> {
                if (player.takeItem(argument)) {
                    System.out.println("You took " + argument + ".");
                } else {
                    System.out.println("There is no " + argument + " in this room.");
                }
            }
            case "drop" -> {
                if (player.dropItem(argument)) {
                    System.out.println("You dropped " + argument + ".");
                } else {
                    System.out.println("You don't have " + argument + " in your inventory.");
                }
            }
            case "eat" -> player.eatFood(argument);
            default -> System.out.println("Invalid command. Type 'help' for available commands.");
        }
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to the quest for the American Dream! You stand at the threshold. You are almost there..");
        printCurrentRoomDescription();
    }

    private void printCurrentRoomDescription() {
        Room currentRoom = adventure.getCurrentRoom();
        System.out.println("\n" + currentRoom.getName());
        System.out.println(currentRoom.getFullDescription());

        Player player = adventure.getPlayer();
        System.out.println("Health: " + player.getHealth() + " - " + player.getHealthStatus());
        showFood();
        showItems();
    }

    private void showFood() {
        ArrayList<Food> foods = adventure.getCurrentRoom().getFoods();
        if (!foods.isEmpty()) {
            System.out.println("Food available in this room:");
            for (int i = 0; i < foods.size(); i++) {
                System.out.println((i + 1) + ". " + foods.get(i).getLongName() + " (Restores " + foods.get(i).getHealthValue() + " health)");
            }
        } else {
            System.out.println("There is no food in this room.");
        }
    }

        private void showInventory () {
            ArrayList<Item> inventory = player.getInventory();
            if (inventory.isEmpty()) {
                System.out.println("Your inventory is empty.");
            } else {
                System.out.println("Inventory:");
                for (Item item : inventory) {
                    System.out.println("- " + item.getLongName());
                }
            }
        }

        private void exitGame () {
            System.out.println("Farewell! Thank you for trying your luck in America.");
            System.exit(0);
        }

    private void move(String direction) {
        Room.Direction dir = Room.Direction.valueOf(direction.toUpperCase());
        adventure.move(dir);
        printCurrentRoomDescription();
    }

    private void showItems() {
        ArrayList<Item> roomItems = adventure.getCurrentRoom().getItems();
        if (roomItems.isEmpty()) {
            System.out.println("There are no items in this room.");
        } else {
            System.out.println("Items available in this room:");
            for (int i = 0; i < roomItems.size(); i++) {
                System.out.println((i + 1) + ". " + roomItems.get(i).getLongName());
            }
        }
    }

    }
