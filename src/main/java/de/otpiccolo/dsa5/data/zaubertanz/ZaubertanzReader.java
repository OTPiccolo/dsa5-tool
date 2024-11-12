package de.otpiccolo.dsa5.data.zaubertanz;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Zaubertänze from an Internet source.
 */
public class ZaubertanzReader extends AUlissesReader<ZaubertanzData> {

	@Override
	public ZaubertanzData readData(final String name) {
		getLog().info("Getting data for Zaubertanz \"{}\".", name);
		String effect;
		String cost;
		String duration;

		try {
			final Document doc = loadDocument(getPage(name));
			effect = getDataInTable(doc, "Wirkung");
			duration = getDataInTable(doc, "Dauer");
			cost = getDataInTable(doc, "AsP-Kosten");
		} catch (final IOException e) {
			getLog().error("Error reading Zaubertanz \"" + name + "\". Error message: " + e.getMessage(), e);
			effect = e.getMessage();
			duration = null;
			cost = null;
		}
		return new ZaubertanzData(name, duration, cost, effect);
	}

	private String getPage(final String zaubertanzName) {
		return "/zaubertanz.html?zaubertanz=" + zaubertanzName;
	}

}
