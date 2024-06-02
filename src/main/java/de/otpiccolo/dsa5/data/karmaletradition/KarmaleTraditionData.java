package de.otpiccolo.dsa5.data.karmaletradition;

import java.util.List;

/**
 * A record of Karmale Tradition data.
 *
 * @param name
 *            Name of the Tradition.
 * @param mainAttribute
 *            The main attribute of the Geweihten.
 * @param preferredTalents
 *            the preferred talents of the Geweihten.
 * @param rules
 *            A list of rules of the Tradition.
 */
public record KarmaleTraditionData(String name, String mainAttribute, String preferredTalents, List<String> rules) {

}
