package de.otpiccolo.dsa5.data.zauber;

/**
 * A record of Zauber data.
 *
 * @param name
 *            Name of the Zauber.
 * @param castTime
 *            The time it takes to cast the Zauber.
 * @param cost
 *            The cost of the Zauber.
 * @param effect
 *            The effect of the Zauber.
 */
public record ZauberData(String name, String castTime, String cost, String effect) {

}
