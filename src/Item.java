public class Item {
    private String longName; // Det fulde navn på genstanden.
    private String shortName; // Et kortere navn eller alias til genstanden.

    public Item(String longName, String shortName) {
        this.longName = longName;
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }
}