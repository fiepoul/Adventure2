public class Food extends Item {
    private int healthValue;

    public Food(String longName, String shortName, int healthValue) {
        super(longName, shortName);
        this.healthValue = healthValue;
    }

    public int getHealthValue() {
        return healthValue;
    }
}
