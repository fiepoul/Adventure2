import java.util.ArrayList;

public class Player {
    private static Room currentRoom; // Det nuværende rum, hvor spilleren befinder sig.
    private static ArrayList<Item> inventory; // En liste til at opbevare de genstande, som spilleren bærer.

    public Player(Room startingRoom) {
        currentRoom = startingRoom;
        inventory = new ArrayList<>();
    }

    // Metode til at tage en genstand fra det aktuelle rum og tilføje den til spillerens inventory.
    public static boolean takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);
        if (item != null) {
            inventory.add(item);
            currentRoom.removeItem(item);
            return true; // Returner sandt, hvis genstanden blev taget med succes.
        }
        return false; // Returner falsk, hvis genstanden ikke kunne findes i rummet.
    }

    // Metode til at efterlade en genstand fra spillerens inventory i det aktuelle rum.
    public boolean dropItem(String itemName) {
        Item item = findItemInInventory(itemName);
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
            return true; // Returner sandt, hvis genstanden blev efterladt med succes.
        }
        return false; // Returner falsk, hvis genstanden ikke kunne findes i spillerens inventory.
    }

    // Metode til at finde en genstand i spillerens inventory ved hjælp af dens navn.
    public Item findItemInInventory(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item; // Returner genstanden, hvis den findes i inventory.
            }
        }
        return null; // Returner null, hvis genstanden ikke kunne findes i inventory.
    }

    // Metode til at få en liste over genstande i spillerens inventory.
    public static ArrayList<Item> getInventory() {
        return inventory;
    }

    // Metode til at ændre det aktuelle rum, hvor spilleren befinder sig.
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    // Metode til at få det aktuelle rum, hvor spilleren befinder sig.
    public Room getCurrentRoom() {
        return currentRoom;
    }
}
