package de.otpiccolo.dsa5.data.zauber;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Zauber from an Internet source.
 */
public class ZauberReader extends AUlissesReader<ZauberData> {

	@Override
	public ZauberData readData(final String name) {
		getLog().info("Getting data for \"{}\" Zauber.", name);
		String effect;
		String cost;
		String castTime;

		try {
			final Document doc = loadDocument(getPage(name));
			effect = getDataInTable(doc, "Wirkung");
			castTime = getDataInTable(doc, "Zauberdauer");
			cost = getDataInTable(doc, "AsP-Kosten");
		} catch (final IOException e) {
			getLog().error("Error reading Zauber \"" + name + "\". Error message: " + e.getMessage(), e);
			effect = e.getMessage();
			castTime = null;
			cost = null;
		}
		return new ZauberData(name, castTime, cost, effect);
	}

	private String getPage(final String zauberName) {
		return "/zauber.html?zauber=" + zauberName;
	}

}
