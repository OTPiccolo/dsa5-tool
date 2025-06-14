package de.otpiccolo.dsa5.data.rituale;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Ritual from an Internet source.
 */
public class RitualReader extends AUlissesReader<RitualData> {

	@Override
	public RitualData readData(final String name) {
		getLog().info("Getting data for \"{}\" Ritual.", name);
		String effect;
		String cost;
		String castTime;

		try {
			final Document doc = loadDocument(getPage(name));
			effect = getDataInTable(doc, "Wirkung");
			castTime = getDataInTable(doc, "Ritualdauer");
			cost = getDataInTable(doc, "AsP-Kosten");
		} catch (final IOException e) {
			getLog().error("Error reading Ritual \"" + name + "\". Error message: " + e.getMessage(), e);
			effect = e.getMessage();
			castTime = null;
			cost = null;
		}
		return new RitualData(name, castTime, cost, effect);
	}

	private String getPage(final String ritualName) {
		return "/ritual.html?ritual=" + ritualName;
	}

}
