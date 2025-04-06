package de.otpiccolo.dsa5.data.weapon;

/**
 * A record of weapon data.
 *
 * @param name
 *            Name of the weapon.
 * @param category
 *            Category of the weapon.
 * @param upside
 *            The upside this weapon has when wielding it.
 * @param downside
 *            The downside this weapon has when wielding it.
 */
public record WeaponData(String name, String category, String upside, String downside) {

}
