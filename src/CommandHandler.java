import java.util.ArrayList;

public class CommandHandler {

    private final Adventure adventure;
    private final Player player;

    public CommandHandler(Adventure adventure, Player player) {
        this.adventure = adventure;
        this.player = player;
    }

    public boolean look() {
        showCurrentroom();
        showItems();
        System.out.println("What would you like to do now? write 'help' for guidance: ");
        return true;
    }

    public boolean showCurrentroom() {
        System.out.println(adventure.getCurrentRoom().getName());
        System.out.println(adventure.getCurrentRoom().getDescription());
        showEnemiesInRoom();
        return true;
    }

    public boolean showInventory() {
        ArrayList<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item item : inventory) {
                System.out.println("- " + item.getLongName());
            }
        }
        return true;
    }

    public void showEnemiesInRoom() {
        ArrayList<Enemy> enemies = adventure.getCurrentRoom().getEnemies();
        if (enemies != null && !enemies.isEmpty()) {
            System.out.println("There is an enemy in the room: " + enemies.get(0).getName());
        } else {
            System.out.println("No enemies here.");
        }
    }

    private void showItemsList(ArrayList<? extends Item> items, String itemType) {
        if (!items.isEmpty()) {
            System.out.println(itemType + " available in this room:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getLongName());
            }
        } else {
            System.out.println("There are no " + itemType.toLowerCase() + " in this room.");
        }
    }

    public boolean showItems() {
        showItemsList(adventure.getCurrentRoom().getItems(), "Items");
        showItemsList(adventure.getCurrentRoom().getWeapons(), "Weapons");
        showItemsList(adventure.getCurrentRoom().getFoods(), "Food");
        return true;
    }

    public boolean move(String direction) {
        Room.Direction dir;
        try {
            dir = Room.Direction.valueOf(direction.toUpperCase());
            if (adventure.getCurrentRoom().isValidExit(dir)) {
                adventure.move(dir);
                showCurrentroom();
                if (adventure.getCurrentRoom().getName().equals("Room 5")) {
                    return winningGame();
                }
            } else {
                System.out.println("Can't go that direction. Try again: ");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid command, try north, south, east or west: ");
        }
        return true;
    }

    public boolean winningGame() {
        System.out.println("Farewell, dreamer! You've won the game by saying 'I do' to the bank account of your dreams.");
        return false;
    }

    public boolean printHelp() {
        System.out.println("Available commands:");
        System.out.println("- look: View the current room description.");
        System.out.println("- inventory: View your inventory.");
        System.out.println("- take [item]: Take an item from the room.");
        System.out.println("- drop [item]: Drop an item from your inventory.");
        System.out.println("- equip [weapon]: Equip a weapon from inventory.");
        System.out.println("- attack: attack your enemy");
        System.out.println("- eat [food]: eat the food in the room.");
        System.out.println("- go north/south/east/west: Move in the specified direction.");
        System.out.println("- help: Seek guidance on your quest.");
        System.out.println("- exit: Farewell and goodbye.");
        return true;
    }

    public boolean exitGame() {
        System.out.println("Farewell! Thank you for trying your luck in America.");
        return false;
    }
    public boolean takeItem(String itemName) {
        Item item = adventure.getCurrentRoom().findItem(itemName);
        Weapon weapon = adventure.getCurrentRoom().findWeapon(itemName);

        if (item != null) {
            player.addItemToInventory(item);
            System.out.println("You took the item: " + itemName + ".");
        } else if (weapon != null) {
            player.pickUpWeapon(weapon);
            player.addItemToInventory(weapon);
            System.out.println("You picked up a " + itemName + ".");
        } else {
            System.out.println("There is no item or weapon named " + itemName + " in this room.");
        }
        return true;
    }

    public boolean equipWeapon(String weaponName) {
        boolean equipped = player.equipWeapon(weaponName);
        if (equipped) {
            System.out.println("You have equipped " + weaponName);
        } else {
            System.out.println("Failed to equip the weapon. Please check your inventory.");
        }
        return true;
    }


    public boolean attackEnemy() {
        ArrayList<Enemy> enemies = adventure.getCurrentRoom().getEnemies();

        if (!enemies.isEmpty()) {
            Enemy enemy = enemies.get(0);
            Weapon playerWeapon = player.getEquippedWeapon();

            if (playerWeapon != null) {
                int damage = playerWeapon.getDamage();
                playerWeapon.use();
                enemy.decreaseHealth(damage);
                System.out.println("You attack " + enemy.getName() + " with " + playerWeapon.getLongName() + " and deal " + damage + " damage!");

                if (enemy.getHealth() <= 0) {
                    adventure.getCurrentRoom().removeEnemy(enemy);
                    System.out.println("You defeated " + enemy.getName() + "!");
                } else {
                    int enemyDamage = enemy.getWeapon().getDamage();
                    player.setHealth(player.getHealth() - enemyDamage);
                    System.out.println(enemy.getName() + " attacks you with " + enemy.getWeapon().getLongName() + " and deals " + enemyDamage + " damage!");
                    System.out.println("Your health: " + player.getHealth());

                    if (player.getHealth() <= 0) {
                        System.out.println("You have been defeated. Game over!");
                        exitGame();
                    }
                }
            } else {
                System.out.println("You have no equipped weapon to attack the enemy!");
            }
        } else {
            System.out.println("There are no enemies in this room.");
        }
        return true;
    }

    public boolean dropItem(String itemName) {
        boolean itemDropped = player.dropItem(itemName);
        if (itemDropped) {
            System.out.println("You dropped " + itemName + " in the room.");
        } else {
            System.out.println("You don't have " + itemName + " in your inventory.");
        }
        return true;
    }

    public boolean eatFood(String foodName) {
        Food food = adventure.getCurrentRoom().findFood(foodName);
        if (food != null) {
            int healthRestored = food.getHealthValue();
            player.eatFood(food);
            adventure.removeFood(food);
            System.out.println("You ate " + food.getLongName() + ". Health: " + player.getHealth());
        } else {
            System.out.println("There is no " + foodName + " in this room.");
        }
        return true;
    }

}
