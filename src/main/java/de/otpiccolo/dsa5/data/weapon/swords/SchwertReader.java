package de.otpiccolo.dsa5.data.weapon.swords;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.data.weapon.AWeaponReader;

/**
 * Class to read a Schwert from an Internet source.
 */
public class SchwertReader extends AWeaponReader<SchwertData> {

	/**
	 * Constructor.
	 */
	public SchwertReader() {
		super("Schwerter");
	}

	@Override
	public SchwertData readData(final String name) {
		getLog().info("Getting data for Schwert \"{}\".", name);
		String upside;
		String downside;

		try {
			final Document doc = loadDocument(getPage(name));
			upside = getAdditionalData(doc, "Waffenvorteil");
			downside = getAdditionalData(doc, "Waffennachteil");
		} catch (final IOException e) {
			getLog().error("Error reading Schwert \"" + name + "\". Error message: " + e.getMessage(), e);
			upside = e.getMessage();
			downside = null;
		}
		return new SchwertData(name, upside, downside);
	}

}
