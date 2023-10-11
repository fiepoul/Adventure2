public class RangedWeapon extends Weapon {
    private int remainingAmmo;

    public RangedWeapon(String longName, String shortName, int ammo) {
        super(longName, shortName);
        this.remainingAmmo = ammo;
    }

    @Override
    public void use() {
        if (remainingAmmo > 0) {
            System.out.println("Bang! Remaining ammo: " + --remainingAmmo);
        } else {
            System.out.println("Out of ammo!");
        }
    }

    public int getDamage() {
        return 20; // Returnerer 20 skade for melee-våbnet.
    }
}
