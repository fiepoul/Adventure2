import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
        private String name;
        private final String description;
        private Map<Direction, Room> exits;
        private ArrayList<Item> items;

    public Room(String name, String description) {
            this.name = name;
            this.description = description;
            this.exits = new HashMap<>();
            items = new ArrayList<>();
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

    public void removeItemByIndex(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public Item findItem(String itemName) {
        for (Item item : items) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item; // Returner genstanden, hvis den findes i rummet.
            }
        }
        return null; // Returner null, hvis genstanden ikke kunne findes i rummet.
    }

        public void addExit(Direction direction, Room room) {
            exits.put(direction, room);
        }

        public String getDescription() {
            return description;
        }

    public String getFullDescription() {
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

    public enum Direction {
        NORTH, SOUTH, EAST, WEST;
        }

    public Room getNorth() {
        return exits.get("north");
    }

    public Room getSouth() {
        return exits.get("south");
    }

    public Room getEast() {
        return exits.get("east");
    }

    public Room getWest() {
        return exits.get("west");
    }
    }
