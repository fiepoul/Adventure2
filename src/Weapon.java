public abstract class Weapon extends Item {
    public Weapon(String longName, String shortName) {
        super(longName, shortName);
    }

    public abstract void use();

    public abstract int getDamage();

}


