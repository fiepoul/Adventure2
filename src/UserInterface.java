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
        this.commandPattern = Pattern.compile("^(go (north|south|east|west)|look|inventory|take\\s\\w+|drop\\s\\w+|equip\\s\\w+|attack|help|exit|health|eat\\s\\w+|)$");
    }

    public void start() {
        printWelcomeMessage();
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            String input = scanner.nextLine().toLowerCase();
            if (validateCommand(input)) {
                gameRunning = handleCommand(input);
            } else {
                System.out.println("Invalid command. Type 'help' for available commands.");
            }
        }
        System.out.println("Thank you for playing. Goodbye!");
        scanner.close();
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to the quest for the American Dream! You stand at the threshold. You are almost there..");
        System.out.println("You find yourself at the beginning of a journey. The path diverges before you.");
        player.getHealthStatus();
        System.out.println("What would you like to do? Type 'help' to see your options: ");
    }

    private boolean validateCommand(String input) {
        Matcher matcher = commandPattern.matcher(input);
        return matcher.matches();
    }

    private boolean handleCommand(String input) {
        String[] commandParts = input.split(" ", 2);
        String command = commandParts[0];
        String argument = commandParts.length > 1 ? commandParts[1] : "";

        CommandHandler commandHandler = new CommandHandler(adventure, player);
        switch (command) {
            case "look" -> commandHandler.look();
            case "inventory" -> commandHandler.showInventory();
            case "items" -> commandHandler.showItems();
            case "go" -> commandHandler.move(argument);
            case "equip" -> commandHandler.equipWeapon(argument);
            case "attack" -> commandHandler.attackEnemy();
            case "help" -> commandHandler.printHelp();
            case "exit" -> commandHandler.exitGame();
            case "take" -> commandHandler.takeItem(argument);
            case "drop" -> commandHandler.dropItem(argument);
            case "eat" -> commandHandler.eatFood(argument);
            default -> System.out.println("Invalid command: " + input + ". Type 'help' for available commands.");
        }
        return true;
    }
}
