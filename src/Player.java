import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private final ArrayList<Item> inventory;
    private int health;

    public Player(Room startingRoom, int initialHealth) {
        currentRoom = startingRoom;
        inventory = new ArrayList<>();
        health = initialHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public boolean takeItem(String itemName) {
        Item item = currentRoom.findItem(itemName);
        if (item != null) {
            inventory.add(item);
            currentRoom.removeItem(item);
            return true;
        }
        return false;
    }

    public boolean dropItem(String itemName) {
        Item item = findItemInInventory(itemName);
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
            return true;
        }
        return false;
    }

    public Item findItemInInventory(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void eatFood(String foodName) {
        Food food = currentRoom.findFood(foodName);
        if (food != null) {
            int healthRestored = food.getHealthValue();
            health += healthRestored;
            health = Math.min(health, 100); // spillerens helbred kan ikke være over 100
            currentRoom.removeFood(food);
            System.out.println("You ate " + food.getLongName() + ". Health: " + health);
        } else {
            System.out.println("There is no " + foodName + " in this room.");
        }
    }

    public String getHealthStatus() {
        if (health >= 80) {
            return "You are in excellent health.";
        } else if (health >= 60) {
            return "You are in good health.";
        } else if (health >= 40) {
            return "You are in fair health.";
        } else if (health >= 20) {
            return "Your health is low. Be careful!";
        } else {
            return "Your health is critical. Find medical help!";
        }
    }
}







