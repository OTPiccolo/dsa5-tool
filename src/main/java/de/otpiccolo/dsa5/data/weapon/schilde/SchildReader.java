package de.otpiccolo.dsa5.data.weapon.schilde;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.data.weapon.AWeaponReader;

/**
 * Class to read a Schild from an Internet source.
 */
public class SchildReader extends AWeaponReader<SchildData> {

	/**
	 * Constructor.
	 */
	public SchildReader() {
		super("Schilde");
	}

	@Override
	public SchildData readData(final String name) {
		getLog().info("Getting data for Schild \"{}\".", name);
		String upside;
		String downside;

		try {
			final Document doc = loadDocument(getPage(name));
			upside = getAdditionalData(doc, "Waffenvorteil");
			downside = getAdditionalData(doc, "Waffennachteil");
		} catch (final IOException e) {
			getLog().error("Error reading Schild \"" + name + "\". Error message: " + e.getMessage(), e);
			upside = e.getMessage();
			downside = null;
		}
		return new SchildData(name, upside, downside);
	}

}
