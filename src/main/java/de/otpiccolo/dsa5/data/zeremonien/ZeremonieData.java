package de.otpiccolo.dsa5.data.zeremonien;

/**
 * A record of Zeremonie data.
 *
 * @param name
 *            Name of the Zeremonie.
 * @param castTime
 *            The time it takes to cast the Zeremonie.
 * @param cost
 *            The cost of the Zeremonie.
 * @param effect
 *            The effect of the Zeremonie.
 */
public record ZeremonieData(String name, String castTime, String cost, String effect) {

}
