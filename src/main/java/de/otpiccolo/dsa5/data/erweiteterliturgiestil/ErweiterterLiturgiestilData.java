package de.otpiccolo.dsa5.data.erweiteterliturgiestil;

/**
 * A record of Erweiteter Liturgiestil data.
 *
 * @param name
 *            Name of the Erweiteter Liturgiestil.
 * @param rule
 *            The rule of the Erweiteter Liturgiestil.
 * @param matchingLiturgiestil
 *            Matching Liturgiestile of this Erweiteter Liturgiestil.
 */
public record ErweiterterLiturgiestilData(String name, String rule, String matchingLiturgiestil) {

}
