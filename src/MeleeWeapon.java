public class MeleeWeapon extends Weapon {
    public MeleeWeapon(String longName, String shortName) {
        super(longName, shortName);
    }

    @Override
    public void use() {
        System.out.println("Swing!");
    }

    @Override
    public int getDamage() {
        return 10; // Returnerer 10 skade for melee-våbnet.
    }
}
