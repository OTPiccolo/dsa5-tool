package de.otpiccolo.dsa5.data.zeremonialgegenstand;

/**
 * A record of Zeremonialgegenstandssonderfertigkeit data.
 *
 * @param name
 *            Name of the Sonderfertigkeit.
 * @param tradition
 *            Tradtion this Sonderfertigkeit belongs to.
 * @param rule
 *            The rule of the Sonderfertigkeit.
 */
public record ZeremonialgegenstandData(String name, String tradition, String rule) {

}
