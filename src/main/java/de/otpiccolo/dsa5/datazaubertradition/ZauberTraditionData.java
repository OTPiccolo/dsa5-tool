package de.otpiccolo.dsa5.datazaubertradition;

import java.util.List;

/**
 * A record of Zauber Tradition data.
 *
 * @param name
 *            Name of the Tradition.
 * @param mainAttribute
 *            The main attribute of the Tradition.
 * @param rules
 *            A list of rules of the Tradition.
 */
public record ZauberTraditionData(String name, String mainAttribute, List<String> rules) {

}
