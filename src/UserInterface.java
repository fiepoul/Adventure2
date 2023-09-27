import java.util.Scanner;

public class UserInterface {

    private final Adventure adventure;

    public UserInterface(Adventure adventure) {
        this.adventure = adventure;
    }

    public void start() {
    Scanner scanner = new Scanner(System.in);
    String input;

        System.out.println("Welcome to the quest for the American Dream! You stand at the threshold. You are almost there..");
    playGame(scanner);
}

    private void playGame(Scanner scanner) {
        while (true) {
            Room currentRoom = adventure.getCurrentRoom();
            System.out.println("\n" + currentRoom.getName());
            System.out.println(currentRoom.getDescription());
            System.out.print("Where would you like to go? (Type 'look' for a closer look): ");

            try {
                String input = scanner.nextLine().toLowerCase();
                command(input);
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public void command(String input) {
        Room currentRoom = adventure.getCurrentRoom();

        switch (input) {
            case "look" -> {
                System.out.println("\nYou are in " + currentRoom.getName());
                System.out.println(currentRoom.getDescription());
            }
            case "go north", "go south", "go east", "go west" -> move(input.split(" ")[1]);
            case "help" -> printHelp();
            case "exit" -> exitGame();
            default -> System.out.println("I don't understand that command.");
        }
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("- look: View the current room description.");
        System.out.println("- go north/south/east/west: Move in the specified direction.");
        System.out.println("- help: Seek guidance on your quest.");
        System.out.println("- exit: Farewell and goodbye.");
    }

    private void exitGame() {
        System.out.println("Farewell! Thank you for trying your luck in America.");
        System.exit(0);
    }

    private void move(String direction) {
        Room currentRoom = adventure.getCurrentRoom();
        Room newRoom = currentRoom.getExit(direction);
        if (newRoom != null) {
            adventure.setCurrentRoom(newRoom);
            System.out.println("You have moved " + direction + ".");
        } else {
            System.out.println("You cannot go " + direction + " from here.");
        }
    }
}
