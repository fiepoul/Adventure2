import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {

    private final Adventure adventure;
    private final Pattern commandPattern;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
        commandPattern = Pattern.compile("^(go (north|south|east|west)|look|inventory|take\\s\\w+|drop\\s\\w+|help|exit)$");
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the quest for the American Dream! You stand at the threshold. You are almost there..");

        while (true) { // Fortsæt så længe brugeren vil spille
            Room currentRoom = adventure.getCurrentRoom();
            System.out.println("\n" + currentRoom.getName());
            System.out.println(currentRoom.getDescription());
            System.out.print("Where would you like to go? \nType 'help' for guidance: ");

            String input = scanner.nextLine().toLowerCase();
            if (validateCommand(input)) {
                command(input);
            } else {
                System.out.println("Invalid command. Type 'help' for available commands.");
            }
        }
    }
    private boolean validateCommand(String input) {
        Matcher matcher = commandPattern.matcher(input);
        return matcher.matches();
    }

    public void command(String input) {
        Room currentRoom = adventure.getCurrentRoom();

        switch (input) {
            case "look" -> {
                System.out.println("\nYou are in " + currentRoom.getName());
                System.out.println(currentRoom.getDescription());
                System.out.println(currentRoom.getFullDescription());

            }
            case "inventory" -> showInventory();
            case "go north", "go south", "go east", "go west" -> move(input.split(" ")[1]);
            case "help" -> printHelp();
            case "exit" -> exitGame();
            default -> {
                if (input.startsWith("take")) {
                    String itemName = input.split(" ", 2)[1];
                    if (adventure.getCurrentRoom().findItem(itemName) != null) {
                        takeItem(itemName); // Send det specifikke item navn med til metoden
                    } else {
                        System.out.println("There is nothing like '" + itemName + "' to take around here.");
                    }
                } else if (input.startsWith("drop")) {
                    String itemName = input.split(" ", 2)[1];
                    if (adventure.getPlayer().findItemInInventory(itemName) != null) {
                        dropItem(itemName); // Send det specifikke item navn med til metoden
                    } else {
                        System.out.println("You don't have anything like '" + itemName + "' in your inventory.");
                    }
                } else {
                    System.out.println("I don't understand that command.");
                }
            }
        }
    }

    private void showInventory() {
        ArrayList<Item> inventory = Player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item item : inventory) {
                System.out.println("- " + item.getLongName());
            }
        }
    }

    private void takeItem(String itemName) {
        Player player = adventure.getPlayer();
        Room currentRoom = player.getCurrentRoom();

        ArrayList<Item> roomItems = currentRoom.getItems();
        if (roomItems.isEmpty()) {
            System.out.println("There are no items to take in this room.");
        } else {
            System.out.println("Items available in this room:");
            for (int i = 0; i < roomItems.size(); i++) {
                System.out.println((i + 1) + ". " + roomItems.get(i).getLongName());
            }

            System.out.print("Enter the name of the item you want to take: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice >= 1 && choice <= roomItems.size()) {
                Item selectedItem = roomItems.get(choice - 1);
                if (player.takeItem(String.valueOf(selectedItem))) {
                    currentRoom.removeItem(selectedItem);
                    System.out.println("You took the " + selectedItem.getLongName() + ".");
                } else {
                    System.out.println("You can't carry more items.");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void dropItem(String itemName) {
        if (adventure.getPlayer().dropItem(itemName)) {
            System.out.println("You dropped the " + itemName + ".");
        } else {
            System.out.println("You don't have anything like '" + itemName + "' in your inventory.");
        }
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

    private void exitGame() {
        System.out.println("Farewell! Thank you for trying your luck in America.");
        System.exit(0);
    }

    private void move(String direction) {
        Room.Direction dir = Room.Direction.valueOf(direction.toUpperCase());
        Room currentRoom = adventure.getCurrentRoom();
        if (currentRoom.isValidExit(dir)) {
            adventure.move(String.valueOf(dir));
            System.out.println("You have moved " + direction + ".");
        } else {
            System.out.println("You cannot go " + direction + " from here.");
        }
    }
}
