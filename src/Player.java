import java.util.ArrayList;

public class Player {
    private Room currentRoom;
    private final ArrayList<Item> inventory;
    private int health;

    private Weapon equippedWeapon;

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

    public boolean dropItem(String itemName) {
        Item item = findItemInInventory(itemName);
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
            return true;
        }
        return false;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
        currentRoom.removeItem(item);
    }

    public Item findItemInInventory(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getInventory(){
            return inventory;
        }


    public void eatFood(Food food) {
        if (food != null) {
            int healthRestored = food.getHealthValue();
            health += healthRestored;
            health = Math.min(health, 100);
        }
    }

    public boolean pickUpWeapon(Weapon weapon) {
        if (currentRoom.getWeapons().contains(weapon)) {
            return true;
        }
        return false;
    }

    public boolean equipWeapon(String weaponName) {
        Weapon weaponToEquip = findWeaponInInventory(weaponName);
        if (weaponToEquip != null) {
            equippedWeapon = weaponToEquip;
            return true;
        }
        return false;
    }

    private Weapon findWeaponInInventory(String weaponName) {
        for (Item item : inventory) {
            if (item instanceof Weapon && item.getShortName().equalsIgnoreCase(weaponName)) {
                return (Weapon) item;
            }
        }
        return null;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
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







