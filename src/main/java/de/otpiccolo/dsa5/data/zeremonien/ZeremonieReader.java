package de.otpiccolo.dsa5.data.zeremonien;

import java.io.IOException;

import org.jsoup.nodes.Document;

import de.otpiccolo.dsa5.ulisses.AUlissesReader;

/**
 * Class to read a Zeremonie from an Internet source.
 */
public class ZeremonieReader extends AUlissesReader<ZeremonieData> {

	@Override
	public ZeremonieData readData(final String name) {
		getLog().info("Getting data for \"{}\" Zeremonie.", name);
		String effect;
		String cost;
		String castTime;

		try {
			final Document doc = loadDocument(getPage(name));
			effect = getDataInTable(doc, "Wirkung");
			castTime = getDataInTable(doc, "Zeremoniedauer");
			cost = getDataInTable(doc, "KaP-Kosten");
		} catch (final IOException e) {
			getLog().error("Error reading Zeremonie \"" + name + "\". Error message: " + e.getMessage(), e);
			effect = e.getMessage();
			castTime = null;
			cost = null;
		}
		return new ZeremonieData(name, castTime, cost, effect);
	}

	private String getPage(final String zeremonieName) {
		return "/zeremonie.html?zeremonie=" + zeremonieName;
	}

}
