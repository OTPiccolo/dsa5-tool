package de.otpiccolo.dsa5.data.BannSchutzkreis;

/**
 * A record of Bann-/Schutzkreis data.
 *
 * @param name
 *            Name of the Bann-/Schutzkreis.
 * @param cost
 *            The cost of the Bann-/Schutzkreis.
 * @param bannEffect
 *            The effect of the Bannkreis.
 * @param schutzEffect
 *            The effect of the Schutzkreis.
 */
public record BannSchutzkreisData(String name, String cost, String bannEffect, String schutzEffect) {

}
