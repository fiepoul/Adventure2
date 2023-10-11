import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {

    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }
        private final String name;
        private final String description;
        private final Map<Direction, Room> exits;
        private final ArrayList<Item> items;

        private final ArrayList<Food> foods;
        private final ArrayList<Weapon> weapons;
        private ArrayList<Enemy> enemies;

    public Room(String name, String description) {
            this.name = name;
            this.description = description;
            exits = new HashMap<>();
            items = new ArrayList<>();
            foods = new ArrayList<>();
            weapons = new ArrayList<>();
            enemies = new ArrayList<>();
        }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item findItem(String itemName) {
        for (Item item : items) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public boolean isValidExit(Direction direction) {
        return exits.containsKey(direction);
    }
    public void addExit(Direction direction, Room room) {
            exits.put(direction, room);
        }
    public Room getExit(Direction direction) {
            return exits.get(direction);
        }

    public String getName() {
            return name;
        }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getDescription() {
        return description;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void removeFood(Food food) {
        foods.remove(food);
    }

    public Food findFood(String foodName) {
        for (Food food : foods) {
            if (food.getShortName().equalsIgnoreCase(foodName)) {
                return food;
            }
        }
        return null;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    public void removeWeapon(Weapon weapon) {
        weapons.remove(weapon);
    }

    public Weapon findWeapon(String weaponName) {
        for (Weapon weapon : weapons) {
            if (weapon.getShortName().equalsIgnoreCase(weaponName)) {
                return weapon;
            }
        }
        return null;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}




