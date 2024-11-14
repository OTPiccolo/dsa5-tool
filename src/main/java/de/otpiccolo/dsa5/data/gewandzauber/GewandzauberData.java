package de.otpiccolo.dsa5.data.gewandzauber;

/**
 * A record of Gewandzauber data.
 *
 * @param name
 *            Name of the Gewandzauber.
 * @param volume
 *            The volume the Gewandzauber takes up.
 * @param cost
 *            The cost of the Gewandzauber.
 * @param effect
 *            The effect of the Gewandzauber.
 */
public record GewandzauberData(String name, int volume, String cost, String effect) {

}
