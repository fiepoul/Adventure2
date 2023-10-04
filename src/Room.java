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

    public Room(String name, String description) {
            this.name = name;
            this.description = description;
            this.exits = new HashMap<>();
            items = new ArrayList<>();
            foods = new ArrayList<>();
        }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean isValidExit(Direction direction) {
        return exits.containsKey(direction);
    }

    public Item findItem(String itemName) {
        for (Item item : items) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

        public void addExit(Direction direction, Room room) {
            exits.put(direction, room);
        }

    public String getFullDescription() {// måske slet
        StringBuilder fullDescription = new StringBuilder(description);
        if (!items.isEmpty()) {
            fullDescription.append("\nItems in the room:");
            for (Item item : items) {
                fullDescription.append("\n- ").append(item.getLongName());
            }
        }
        return fullDescription.toString();
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
}


