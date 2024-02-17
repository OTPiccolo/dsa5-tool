package de.otpiccolo.dsa5.data.liturgien;

/**
 * A record of Liturgie data.
 *
 * @param name
 *            Name of the Liturgie.
 * @param castTime
 *            The time it takes to cast the Liturgie.
 * @param cost
 *            The cost of the Liturgie.
 * @param effect
 *            The effect of the Liturgie.
 */
public record LiturgieData(String name, String castTime, String cost, String effect) {

}
