package de.otpiccolo.dsa5.data.zaubererweiterung;

/**
 * A record of Zaubererweiterung data.
 *
 * @param name
 *            Name of the Zaubererweiterung.
 * @param zauberName
 *            Name of the corresponding Zauber.
 * @param effect
 *            The effect of the Zaubererweiterung.
 */
public record ZaubererweiterungData(String name, String zauberName, String effect) {

}
