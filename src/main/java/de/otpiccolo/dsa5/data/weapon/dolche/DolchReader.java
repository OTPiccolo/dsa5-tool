package de.otpiccolo.dsa5.data.weapon.dolche;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.data.weapon.AWeaponReader;

/**
 * Class to read a Dolch from an Internet source.
 */
public class DolchReader extends AWeaponReader<DolchData> {

	/**
	 * Constructor.
	 */
	public DolchReader() {
		super("Dolche");
	}

	@Override
	public DolchData readData(final String name) {
		getLog().info("Getting data for Dolch \"{}\".", name);
		String upside;
		String downside;

		try {
			final Document doc = loadDocument(getPage(name));
			upside = getAdditionalData(doc, "Waffenvorteil");
			downside = getAdditionalData(doc, "Waffennachteil");
		} catch (final IOException e) {
			getLog().error("Error reading Dolch \"" + name + "\". Error message: " + e.getMessage(), e);
			upside = e.getMessage();
			downside = null;
		}
		return new DolchData(name, upside, downside);
	}

}
