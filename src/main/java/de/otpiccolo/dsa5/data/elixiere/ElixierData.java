package de.otpiccolo.dsa5.data.elixiere;

import java.util.List;

/**
 * A record of Elixier data.
 *
 * @param name
 *            Name of the Elixier.
 * @param cost
 *            The cost of the Elixier (at first QS).
 * @param qs
 *            A list of all QS.
 */
public record ElixierData(String name, int cost, List<String> qs) {

}
