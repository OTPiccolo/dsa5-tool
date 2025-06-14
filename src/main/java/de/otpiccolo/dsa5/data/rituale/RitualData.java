package de.otpiccolo.dsa5.data.rituale;

/**
 * A record of Ritual data.
 *
 * @param name
 *            Name of the Ritual.
 * @param castTime
 *            The time it takes to cast the Ritual.
 * @param cost
 *            The cost of the Ritual.
 * @param effect
 *            The effect of the Ritual.
 */
public record RitualData(String name, String castTime, String cost, String effect) {

}
